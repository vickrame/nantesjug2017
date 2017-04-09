package com.clea.nantes.jug.netflix.cuves.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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

import com.clea.nantes.jug.netflix.cuves.services.CuvesServices;
import com.clea.nantesjug.referentiels.domain.ReacteurModel;
import com.clea.nantesjug.referentiels.dto.CuveDTO;

@EnableAutoConfiguration
@RestController
@RequestMapping(value ="/rest")
//@EnableDiscoveryClient
public class CuveControleur {
	public CuveControleur(){
	}
//	private static Logger log = Logger.getLogger(PersonnageControleur.class.getName());
	
	@Autowired
	private CuvesServices cuvesServices;

	@RequestMapping( method = RequestMethod.GET, value="/cuves" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CuveDTO>> getAll(){
		System.out.println("Apel à la méthode getALL");
		//log.info("id " + id);
		List<CuveDTO> p = cuvesServices.getAll();
		if(p!=null){
			//log.fine(p.toString());
			return new ResponseEntity<List<CuveDTO>>(p,HttpStatus.OK);
		}else{
			//log.log(Level.SEVERE, "Identifiant " + id + " introuvable");
			return new ResponseEntity<List<CuveDTO>>(HttpStatus.BAD_REQUEST);
		}
		//return p;
		//return "ok";
	}
	
	@RequestMapping( method = RequestMethod.GET, value="/cuves/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CuveDTO> getReacteurByID(@PathVariable String id){
		System.out.println("id " + id );
		//log.info("id " + id);
		CuveDTO p = cuvesServices.getByID(id);
		if(p!=null){
			//log.fine(p.toString());
			return new ResponseEntity<CuveDTO>(p,HttpStatus.OK);
		}else{
			//log.log(Level.SEVERE, "Identifiant " + id + " introuvable");
			return new ResponseEntity<CuveDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping( method = RequestMethod.POST, value="/cuves", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<CuveDTO> create(@RequestBody String json){
		
//		log.entering(this.getClass().getName(), "create" );
		
		CuveDTO dto = cuvesServices.create(json);
//		dc.getNextServerFromEureka(virtualHostname, secure)
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getIdCuve()).toUri());
//			log.exiting(this.getClass().getName(), "create" );
		return new ResponseEntity<CuveDTO>(null, httpHeaders, HttpStatus.CREATED);
		
	}
}
