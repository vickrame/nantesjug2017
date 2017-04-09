/**
 * 
 */
package com.clea.nantesjug.referentiels.common.utils.generic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vickrame
 *
 */
public abstract class AbstractService<T, Y> {

	@Autowired
	private Client es;
	
	private String typeIndex;
	protected String index = "api";
	
	private Class<T> type ;
	private Class<Y> typeY ;
	
	protected Logger log=null;// = Logger.

	private int scrollSize=100;
	
	public AbstractService( final String typeIndex, Class<T> t, Class<Y> y) {
		this.typeIndex = typeIndex;
		type = t;
		typeY = y;
		
		log = Logger.getLogger(t.getName());
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	protected List<Y> getAll(){
		List<Y> all = new ArrayList<Y>();
		SearchResponse response = es.prepareSearch(index).setTypes(typeIndex).setQuery(QueryBuilders.matchAllQuery())
                .setSize(scrollSize).execute().actionGet();
		
		if(response!=null){
			Iterator<SearchHit> it = response.getHits().iterator();
		
			while (it.hasNext()) {
				SearchHit searchHit = (SearchHit) it.next();
				String txt = searchHit.getSourceAsString();
				ObjectMapper mapper = new ObjectMapper();
				Y o;
				try {
					o = mapper.readValue(txt, typeY);
					all.add(o);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		
		}
		
		return all;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	protected Y getByID(final String id){
		GetResponse o = es.prepareGet(index, typeIndex, id).execute().actionGet();
		Y ret = null;
		if(o!=null){
			info("stream " + o.getSourceAsString());
			if(!o.isSourceEmpty()){
				String sourceJson = o.getSourceAsString();
				
//				convertoEntityToDTO(o);
				log.log(Level.FINEST, "json " + sourceJson );
				ObjectMapper mapper =new ObjectMapper();
				try {
					ret = mapper.readValue(sourceJson, typeY);
					log.log(Level.INFO, ret.toString() );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.log(Level.SEVERE, "erreur de requete ", e);
				}
			}else{
				log.log(Level.SEVERE, "Aucune donnée trouvée pour cet identifiant");
				ret = null;
			}

		}else{
			info("Aucune données remonté avec l'id suivant : " +id);
		}
		return ret;
	}
	
	/**
	 * 
	 * @param id
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	protected Y updateByID(final String id, Map<String, Object> map) throws IOException{
		UpdateRequest updateRequest = new UpdateRequest();

		updateRequest.doc(XContentFactory.jsonBuilder().startObject().map(map)
		        .endObject());
		
		return (Y)es.update(updateRequest).actionGet();
	}	
	
	
	protected void deletedByID(final String id){
		DeleteResponse response = es.prepareDelete(index, typeIndex, id).get();
//		response.
//		        .setOperationThreaded(false)
//		        .get();
	}
	
	protected Y create(final String json)
	{
		System.out.println("index " + index);
		System.out.println("Typeindex " + typeIndex);
		IndexResponse response = es.prepareIndex(index, typeIndex)
		        .setSource(json)
		        .get();
		
		System.out.println("convertion ");

		Y dto = convertoEntityToDTO(response);
		
		return dto;
	}
	/**
	 * 
	 * @return
	 */
	protected abstract Y convertoEntityToDTO(IndexResponse response);

	protected void info(String message){
		info(message, null);
	}
	
	protected void info(String message, Throwable th){
		log.info(message);
	}
	
}
