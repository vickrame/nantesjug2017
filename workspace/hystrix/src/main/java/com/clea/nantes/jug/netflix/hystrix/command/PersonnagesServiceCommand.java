package com.clea.nantes.jug.netflix.hystrix.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PersonnagesServiceCommand {

	private RestTemplate rt;

	@Autowired
	private EurekaClient eurekaClient;

	@HystrixCommand(fallbackMethod = "annuairePersonnagesIndisponible")
	public String statusAnnuairePersonne() {
		InstanceInfo  instanceInfo = eurekaClient.getNextServerFromEureka("annuaire-personne", false);
		rt= new RestTemplate();
		
		ResponseEntity<String> responseHttp = rt.getForEntity(instanceInfo.getHomePageUrl()+"/info", String.class);
		String ret=null;
		if(responseHttp.getStatusCode().is2xxSuccessful()){
			ret = responseHttp.getBody();
		}
		return ret;

	}

	private String annuairePersonnagesIndisponible() {
		return "Service indisponible => KO";
	}
}