package com.zdh.core.util;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import net.sf.json.JSONObject;

public class HuanXinSendMsgUtil {

	public static void main(String[] args) {
//		sendMsg("4e28273851aa423e65c7ca31967da8d0","YWMtpEe_xnTgEeaMn__QSNHYmgAAAVg5DLzQrhIvVxHIEBuUKPmyeGo6i6AuU28");
	}
	public static void sendMsg(String username,String token){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/messages");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("target_type", "users");
//		obj.put("target", "[4e28273851aa423e65c7ca31967da8d0]");
		obj.put("target", "[\""+username+"\"]");
		JSONObject info = new JSONObject();
		info.put("type", "txt");
		info.put("msg", "Hello! My name is 小薇~           Nice to meet you! ");
		obj.put("msg", info);
		obj.put("from", "b8c37e33defde51cf91e1e03e51657da");
		JSONObject header = new JSONObject();
		
		header.put("Content-Type", "application/json");
		String result = resource
				.type(MediaType.APPLICATION_JSON)
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer " + token)
				.post(String.class, obj.toString());
		System.out.println(result);
	}
}
