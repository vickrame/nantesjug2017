package com.clea.nantes.jug.netflix.hystrix.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clea.nantes.jug.netflix.hystrix.command.PersonnagesServiceCommand;

@RestController
public class HystrixControleur {

	@Autowired
	private PersonnagesServiceCommand personneServices;
	
	@RequestMapping("/")
	public String home() {
		return "forward:/hystrix";
	}
	
	@RequestMapping("/circuitBreaker")
	public ResponseEntity<String> circuitBreaker() {
		return new ResponseEntity<String>(personneServices.statusAnnuairePersonne(),HttpStatus.OK);
	}
}
