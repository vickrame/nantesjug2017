/**
 * 
 */
package com.clea.nantes.jug.netflix.reacteur.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.clea.nantesjug.referentiels.common.utils.generic.AbstractService;
import com.clea.nantesjug.referentiels.dto.CuveDTO;
import com.clea.nantesjug.referentiels.dto.ReacteurDTO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

/**
 * @author vickrame
 *
 */
@Service
public class ReacteurServices extends AbstractService<ReacteurServices, ReacteurDTO> {

	
	private static final String reacteur="reacteurs";
	
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient dcSpring;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	
	private static final String urlCuve ="http://127.0.0.1:8081/rest/cuves/";
	
	public ReacteurServices(){
		super( reacteur,ReacteurServices.class, ReacteurDTO.class);
	}
	
	@Override
	public List<ReacteurDTO> getAll() {
		info("requete toutes les données");
		return super.getAll();
	}
	
	/* (non-Javadoc)
	 * @see com.ujoodha.springboot.web.main.services.AbstractService#getByID(java.lang.String)
	 */
	@Override
	public ReacteurDTO getByID(String id) {
		info("requete avec l'id suivant : "+ id, null);
		// TODO Auto-generated method stub
		return super.getByID(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ujoodha.springboot.web.main.services.AbstractService#create(java.lang.String)
	 */
	@Override
	public ReacteurDTO create(String json) {
		return super.create(json);
	}
	
	
	
	public ReacteurDTO getDetail(final String id){
		ReacteurDTO r = getByID(id);
		restTemplate = new RestTemplate();
		ResponseEntity<CuveDTO> responseHttp = restTemplate.getForEntity(urlCuve+r.getIdCuve(), CuveDTO.class);
		
		if(responseHttp.getStatusCode().is2xxSuccessful()){
			r.setCuve(responseHttp.getBody());
		}
		return r;
				
	}
	
	
	@Override
	protected ReacteurDTO convertoEntityToDTO(IndexResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * retourne la liste des applications enregistres par Eureka.
	 * @return
	 */
	public Map<String, String> getAllApplicationInEureka(){
		Map<String,String> ret = new HashMap<String,String>();
		
		Applications applications = eurekaClient.getApplications();

		if(applications!=null){
			List<Application> alls =applications.getRegisteredApplications();
			if(alls!=null && !alls.isEmpty()){
					for ( Application info : alls) {
						String applicationName = info.getName();
						if(info.getInstances() != null && !info.getInstances().isEmpty()){
							for( InstanceInfo item : info.getInstances()){
								System.out.println("Info " + info);
								ret.put(applicationName, item.getHomePageUrl());
						}
						
					}
				}
			}

		}
		return ret;
	}

	/**
	 * donne le detail d un reacteur.
	 * @param id
	 * @return
	 */
	public ReacteurDTO getDetailIdFromEureka(String id) {
		ReacteurDTO r = getByID(id);		
		InstanceInfo  instanceInfo = eurekaClient.getNextServerFromEureka("annuaire-cuves", false);
		
		restTemplate = new RestTemplate();
		ResponseEntity<CuveDTO> responseHttp = restTemplate.getForEntity(instanceInfo.getHomePageUrl()+"/rest/cuves/"+r.getIdCuve(), CuveDTO.class);
		
		if(responseHttp.getStatusCode().is2xxSuccessful()){
			r.setCuve(responseHttp.getBody());
		}
		
		return r;
	}
	
}
