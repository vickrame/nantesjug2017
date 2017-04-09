package com.clea.nantesjug.referentiels.personnages.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clea.nantesjug.referentiels.dto.PersonnageDTO;
import com.clea.nantesjug.referentiels.personnages.services.PersonneService;

@EnableAutoConfiguration
@RequestMapping(value="/rest")
@RestController
public class PersonnageControleur {

	
	public PersonnageControleur(){
	}
	
	@Autowired
	private PersonneService personneService;
	
	@RequestMapping( method = RequestMethod.GET, value="/personnages" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<PersonnageDTO>> getAll(){
		List<PersonnageDTO> p =personneService.getAll();
		if(p!=null){
			return new ResponseEntity<List<PersonnageDTO>>(p,HttpStatus.OK);
		}else{
			return new ResponseEntity<List<PersonnageDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping( method = RequestMethod.GET, value="/personnages/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PersonnageDTO> getPersonneByID(@PathVariable String id){
		System.out.println("id " + id );
		PersonnageDTO p =personneService.getByID(id);
		if(p!=null){
			//log.fine(p.toString());
			return new ResponseEntity<PersonnageDTO>(p,HttpStatus.OK);
		}else{
			//log.log(Level.SEVERE, "Identifiant " + id + " introuvable");
			return new ResponseEntity<PersonnageDTO>(HttpStatus.BAD_REQUEST);
		}
		//return p;
		//return "ok";
	}
	
	@RequestMapping( method = RequestMethod.POST, value="/personnages", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<PersonnageDTO> create(@RequestBody String json){
		
//		log.entering(this.getClass().getName(), "create" );
		
		PersonnageDTO dto = personneService.create(json);
//		dc.getNextServerFromEureka(virtualHostname, secure)
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri());
//			log.exiting(this.getClass().getName(), "create" );
		return new ResponseEntity<PersonnageDTO>(null, httpHeaders, HttpStatus.CREATED);
		
	}
	
}
