package com.clea.nantes.jug.netflix.reacteur.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.clea.nantes.jug.netflix.reacteur")
@EnableEurekaClient
//@EnableDiscoveryClient
@EnableAutoConfiguration
public class StartServerReacteur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(StartServerReacteur.class, args);
	}

}
