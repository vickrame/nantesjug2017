/**
 * 
 */
package com.clea.nantes.jug.netflix.reacteur.data;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author vickrame
 *
 */
@Configuration
@PropertySource(value= "classpath:elasticsearch.properties")
public class ClientES {

	@Resource
	private Environment environnement;
	
	@Bean
	public Client client() {
		
		Map<String, String> map= new HashMap<String, String>();
		map.put("elasticsearch.host", environnement.getProperty("elasticsearch.host"));
		map.put("elasticsearch.port", environnement.getProperty("elasticsearch.port"));
		map.put("cluster.name", environnement.getProperty("cluster.name"));

		String host = environnement.getProperty("elasticsearch.host");
		int port = Integer.valueOf(environnement.getProperty("elasticsearch.port"));
		Client client = null;
		try {
			client = TransportClient.builder().build()
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	    System.out.println(client);
	    
	    return client;
	}
}
