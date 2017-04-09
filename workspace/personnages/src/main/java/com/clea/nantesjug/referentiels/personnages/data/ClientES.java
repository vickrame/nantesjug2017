/**
 * 
 */
package com.clea.nantesjug.referentiels.personnages.data;

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
//		
//		map.put("path.home", environnement.getProperty("path.home"));
		
//		final Builder builder= new Builder();
//
//		
//		TransportClient.Builder buildTransport=builder;
//		
//	    TransportClient client = TransportClient.builder().build();
//	    client.addTransportAddress( new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
//	        
//	        		TransportAddress address = new InetSocketTransportAddress(environment.getProperty("elasticsearch.host"), Integer.parseInt(environment.getProperty("elasticsearch.port")));
//	client.addTransportAddress(address);  *

//	    Settings settings = Settings.settingsBuilder().put(map).build();
//
//	    Client client = TransportClient.builder().settings(settings).build();
//	    	      .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
	    
//		Client client = TransportClient.builder().settings(settings).build()
//                .addTransportAddress(new InetSocketTransportAddress(new InetA
//                        "143.79.236.xxx",
//                        9300));

		String host = environnement.getProperty("elasticsearch.host");
		int port = Integer.valueOf(environnement.getProperty("elasticsearch.port"));
		Client client = null;
		try {
			client = TransportClient.builder().build()
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));		
		
	    System.out.println(client);
	    
//	    NodeBuilder nodeBuilder = new NodeBuilder();
//	    Node node = nodeBuilder.client(true).local(true).clusterName("my-es").settings(Settings.builder().put(map)).build();
	
//	    System.out.println("node " +node );
	    
	    return client;
//	return node.client();
	}
}
