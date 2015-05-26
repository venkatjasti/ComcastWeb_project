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

/**
 * @author vrjasti
 *
 */
public class ClientServiceHelper  {

	private static final String SERVICE_URL = "http://localhost:8080/ComcastWeb";
	private static final Logger LOGGER = Logger.getLogger(ClientServiceHelper.class.getName());
	private static final int RESPONSE_STATUS_200 = 200;
	private static  Client client ;
	private static ObjectMapper mapper ; 
	ClientServiceHelper(){
		this.client = Client.create();
		this.mapper= new ObjectMapper();
	}
	
	public static ClientResponse serviceCall(String uri) {
		ClientResponse response = null;
		try {
			WebResource webResource = client.resource(SERVICE_URL + uri);
			response = webResource.accept("application/json").get(
					ClientResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning("ClientServiceHelper exception" + e.getMessage());
			
		}
		return response;
	}
	
	public static ClientResponse serviceCall(String uri,Object obj) {
		ClientResponse response = null;
		try {
			
			String userString = getJsonMapper().writeValueAsString((User)obj);
			WebResource webResource = client.resource(SERVICE_URL + uri);
			response = webResource.accept("application/json").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, userString);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning("ClientServiceHelper create exception" + e.getMessage());
		}
		return response;
	}
	
	public static ClientResponse serviceCall(String uri,String method) {
		ClientResponse response = null;
		try {
			
			WebResource webResource = client.resource(SERVICE_URL + uri);
			response = webResource.accept("application/json").type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning("ClientServiceHelper exception" + e.getMessage());
			
		}
		return response;
	}

	public static ObjectMapper getJsonMapper() {
		return mapper;
	}

}
