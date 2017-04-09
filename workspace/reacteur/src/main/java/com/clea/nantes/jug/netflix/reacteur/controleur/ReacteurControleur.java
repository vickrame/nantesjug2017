package com.clea.nantes.jug.netflix.reacteur.controleur;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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

import com.clea.nantes.jug.netflix.reacteur.services.ReacteurServices;
import com.clea.nantesjug.referentiels.dto.ReacteurDTO;

@RestController
@RequestMapping(value ="/rest")
@EnableDiscoveryClient
public class ReacteurControleur {
	public ReacteurControleur(){
	}
	
	@Autowired
	private ReacteurServices reacteurService;

	@RequestMapping( method = RequestMethod.GET, value="/reacteurs" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ReacteurDTO>> getAll(){
		System.out.println("Apel à la méthode getALL");
		//log.info("id " + id);
		List<ReacteurDTO> p = reacteurService.getAll();
		if(p!=null){
			//log.fine(p.toString());
			return new ResponseEntity<List<ReacteurDTO>>(p,HttpStatus.OK);
		}else{
			//log.log(Level.SEVERE, "Identifiant " + id + " introuvable");
			return new ResponseEntity<List<ReacteurDTO>>(HttpStatus.BAD_REQUEST);
		}
		//return p;
		//return "ok";
	}
	
	@RequestMapping( method = RequestMethod.GET, value="/reacteurs/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReacteurDTO> getReacteurByID(@PathVariable String id){
		System.out.println("id " + id );
		//log.info("id " + id);
		ReacteurDTO p = reacteurService.getByID(id);
		if(p!=null){
			//log.fine(p.toString());
			return new ResponseEntity<ReacteurDTO>(p,HttpStatus.OK);
		}else{
			//log.log(Level.SEVERE, "Identifiant " + id + " introuvable");
			return new ResponseEntity<ReacteurDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping( method = RequestMethod.POST, value="/reacteurs", consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<ReacteurDTO> create(@RequestBody String json){
		
//		log.entering(this.getClass().getName(), "create" );
		
		ReacteurDTO dto = reacteurService.create(json);
//		dc.getNextServerFromEureka(virtualHostname, secure)
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri());
//			log.exiting(this.getClass().getName(), "create" );
		return new ResponseEntity<ReacteurDTO>(null, httpHeaders, HttpStatus.CREATED);
		
	}
	
	@RequestMapping( method = RequestMethod.GET, value="/reacteurs/{id}/detail" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReacteurDTO> getDetailFromId(@PathVariable String id){
		System.out.println("id " + id );
		//log.info("id " + id);
		ReacteurDTO p = reacteurService.getDetail(id);
		if(p!=null){
			//log.fine(p.toString());
			return new ResponseEntity<ReacteurDTO>(p,HttpStatus.OK);
		}else{
			//log.log(Level.SEVERE, "Identifiant " + id + " introuvable");
			return new ResponseEntity<ReacteurDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping( method = RequestMethod.GET, value="/reacteurs/eureka" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,String>> getAllInstancesInEureka(){
		//log.info("id " + id);
		Map<String,String> allInstance = reacteurService.getAllApplicationInEureka();
		if(allInstance!=null){
			//log.fine(p.toString());
			return new ResponseEntity<Map<String,String>>(allInstance,HttpStatus.OK);
		}else{
			//log.log(Level.SEVERE, "Identifiant " + id + " introuvable");
			return new ResponseEntity<Map<String,String>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@RequestMapping( method = RequestMethod.GET, value="/reacteurs/{id}/v1/detail" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReacteurDTO> getDetailIdFromEureka(@PathVariable String id){
		System.out.println("id " + id );
		//log.info("id " + id);
		ReacteurDTO p = reacteurService.getDetailIdFromEureka(id);
		if(p!=null){
			//log.fine(p.toString());
			return new ResponseEntity<ReacteurDTO>(p,HttpStatus.OK);
		}else{
			//log.log(Level.SEVERE, "Identifiant " + id + " introuvable");
			return new ResponseEntity<ReacteurDTO>(HttpStatus.BAD_REQUEST);
		}
	}
}
