/**
 * 
 */
package com.src.comcast.service;

import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.src.comcast.model.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * @author vrjasti
 *
 */
public class ClientServiceHelper  {

	private static final String SERVICE_URL = "http://localhost:8080/ComcastWeb";
	private static final Logger LOGGER = Logger.getLogger(ClientServiceHelper.class.getName());
	
	
	public static ObjectMapper getJsonMapper() {
		ObjectMapper mapper = new ObjectMapper(); 
		return mapper;
	}

}
