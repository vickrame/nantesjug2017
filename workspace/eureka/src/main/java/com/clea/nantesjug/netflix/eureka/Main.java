/**
 * 
 */
package com.clea.nantesjug.netflix.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author vickrame
 *
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableEurekaServer
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
