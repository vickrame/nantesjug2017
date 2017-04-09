package com.clea.nantesjug.referentiels.personnages.main;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.clea.nantesjug.referentiels.personnages")
@EnableEurekaClient
@EnableDiscoveryClient
public class StartServerPersonnage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(StartServerPersonnage.class, args);
	}

}
