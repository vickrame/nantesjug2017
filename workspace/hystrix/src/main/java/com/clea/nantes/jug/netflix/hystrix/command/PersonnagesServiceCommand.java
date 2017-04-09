package com.clea.nantes.jug.netflix.hystrix.command;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PersonnagesServiceCommand {

	private RestTemplate rt;


	@HystrixCommand(fallbackMethod = "annuairePersonnagesIndisponible")
	public String statusAnnuairePersonne() {
		String url = "http://localhost:8061";
		String ret = "";
		rt = new RestTemplate();
		ret = rt.getForObject(url+"/info", String.class);
		return ret;

	}

	private String annuairePersonnagesIndisponible() {
		return "Service indisponible => KO";
	}
}