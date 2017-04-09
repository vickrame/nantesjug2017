package com.clea.nantes.jug.netflix.cuves.main;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.clea.nantes.jug.netflix.cuves")
@EnableEurekaClient
@EnableDiscoveryClient
public class StartServerCuves {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(StartServerCuves.class, args);
	}

}
