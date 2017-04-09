/**
 * 
 */
package com.clea.nantes.jug.netflix.cuves.services;

import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.springframework.stereotype.Service;

import com.clea.nantesjug.referentiels.common.utils.generic.AbstractService;
import com.clea.nantesjug.referentiels.domain.ReacteurModel;
import com.clea.nantesjug.referentiels.dto.CuveDTO;

/**
 * @author vickrame
 *
 */
@Service
public class CuvesServices extends AbstractService<CuvesServices, CuveDTO> {

	
	private static final String reacteur="cuves";
	public CuvesServices(){
		super( reacteur,CuvesServices.class, CuveDTO.class);
	}
	
	@Override
	public List<CuveDTO> getAll() {
		info("requete toutes les données");
		// TODO Auto-generated method stub
		return super.getAll();
	}
	
	/* (non-Javadoc)
	 * @see com.ujoodha.springboot.web.main.services.AbstractService#getByID(java.lang.String)
	 */
	@Override
	public CuveDTO getByID(String id) {
		info("requete avec l'id suivant : "+ id, null);
		// TODO Auto-generated method stub
		return super.getByID(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ujoodha.springboot.web.main.services.AbstractService#create(java.lang.String)
	 */
	@Override
	public CuveDTO create(String json) {
		// TODO Auto-generated method stub
		return super.create(json);
	}
	
	
	@Override
	protected CuveDTO convertoEntityToDTO(IndexResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
