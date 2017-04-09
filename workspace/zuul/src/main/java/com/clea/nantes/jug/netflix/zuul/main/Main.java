/**
 * 
 */
package com.clea.nantes.jug.netflix.zuul.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author vickrame
 *
 */
@EnableAutoConfiguration
@EnableZuulProxy
@EnableDiscoveryClient
@EnableHystrixDashboard
public class Main {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

	}

}
