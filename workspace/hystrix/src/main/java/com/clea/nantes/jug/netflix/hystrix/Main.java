package com.clea.nantes.jug.netflix.hystrix;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@EnableHystrixDashboard
@EnableCircuitBreaker
@ComponentScan
@EnableEurekaClient
@EnableDiscoveryClient
public class Main  extends SpringBootServletInitializer {
	
    public static void main(String[] args) {
        new SpringApplicationBuilder(Main.class).web(true).run(args);
    }
}