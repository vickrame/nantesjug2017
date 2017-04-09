/**
 * 
 */
package com.clea.nantesjug.referentiels.personnages.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.springframework.stereotype.Service;

import com.clea.nantesjug.referentiels.common.utils.generic.AbstractService;
import com.clea.nantesjug.referentiels.dto.PersonnageDTO;

/**
 * @author vickrame
 *
 */
@Service
public class PersonneService extends AbstractService<PersonneService, PersonnageDTO> {

	private static final String personne="personnages";
	
	public PersonneService() {
		super(personne,PersonneService.class, PersonnageDTO.class);
	}

	/* (non-Javadoc)
	 * @see com.ujoodha.springboot.web.main.services.AbstractService#getByID(java.lang.String)
	 */
	@Override
	public List<PersonnageDTO> getAll() {
		return super.getAll();
	}
	
	/* (non-Javadoc)
	 * @see com.ujoodha.springboot.web.main.services.AbstractService#getByID(java.lang.String)
	 */
	@Override
	public PersonnageDTO getByID(String id) {
		info("requete avec l'id suivant : "+ id, null);
		// TODO Auto-generated method stub
		return super.getByID(id);
	}

	/* (non-Javadoc)
	 * @see com.ujoodha.springboot.web.main.services.AbstractService#create(java.lang.String)
	 */
	@Override
	public PersonnageDTO create(String json) {
		// TODO Auto-generated method stub
		return super.create(json);
	}

	/* (non-Javadoc)
	 * @see com.ujoodha.springboot.web.main.services.AbstractService#updateByID(java.lang.String, java.util.Map)
	 */
	@Override
	public PersonnageDTO updateByID(String id, Map<String, Object> map) throws IOException {
		// TODO Auto-generated method stub
		return super.updateByID(id, map);
	}

	/* (non-Javadoc)
	 * @see com.ujoodha.springboot.web.main.services.AbstractService#deletedByID(java.lang.String)
	 */
	@Override
	public void deletedByID(String id) {
		// TODO Auto-generated method stub
		super.deletedByID(id);
	}

	@Override
	protected PersonnageDTO convertoEntityToDTO(IndexResponse response) {
		// TODO Auto-generated method stub
	
		//System.out.println("conversion");
		log.info("conversion du model en DTO");
		PersonnageDTO dto = new PersonnageDTO();
		System.out.println("avant parsing reponse");
		dto.setId(response.getId());
//		dto.setNom(response.);
		return dto;
	}

}
