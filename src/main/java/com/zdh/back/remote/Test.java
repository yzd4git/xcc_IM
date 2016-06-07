package com.zdh.back.remote;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.zdh.core.base.test.domain.Product;
import com.zdh.core.util.Base64Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class Test {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		// Fluent interfaces
		// System.out.println(service.path("remoteApi").path("login").accept(MediaType.TEXT_PLAIN).get(ClientResponse.class).toString());
		// // Get plain text
		// System.out.println(service.path("remoteApi").path("login").accept(MediaType.TEXT_PLAIN).get(String.class));
		// // Get XML
		// System.out.println(service.path("remoteApi").path("login").accept(MediaType.TEXT_XML).get(String.class));
		// // The HTML
		// System.out.println(service.path("remoteApi").path("login").accept(MediaType.TEXT_HTML).get(String.class));
		// System.out.println(service.path("remoteApi").path("login").accept(MediaType.APPLICATION_JSON).get(String.class));
		// testUrl();
		// testLogin() ;
		 //testHUXINToken() ;
//		testHUXINRegister();
		//testJPUSH() ;
		
//		testGetGroupImg();
		testFind1();
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/xcc_IM/IM/rest/").build();
	}

	public static void testUrl() {

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		Client c = Client.create(clientConfig);
		String url = "http://localhost:8080/xcc_IM/IM/rest/remoteApi/login";
		WebResource r = c.resource(url);
		MultivaluedMap<String, String> param = new MultivaluedMapImpl();
		param.add("userId", "10000");
		param.add("userName", "脏话");
		param.add("nickName", "脏话色狼");
		Product p = new Product();
		System.out.println(r.queryParams(param).type(MediaType.WILDCARD)
				.post(String.class));
		
		System.out.println("状态码="
				+ r.path("setUser")
						.accept(new String[] { MediaType.APPLICATION_JSON })
						.entity(p, MediaType.APPLICATION_JSON)
						.post(String.class));
	}
	public static void testLogin() {

		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/register");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		// params.add("userId", "10000");
		// params.add("userName", "脏话");
		// params.add("nickName", "脏话色狼");
		JSONObject obj = new JSONObject();
		obj.put("username", "dadaaa");
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}

	public static void AlterNickName(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/users/9f908f56341ae4304d777736603f4a5d");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("nickname", "8f808f56341ae4304d777736603f4a5d");
		JSONObject header = new JSONObject();
		header.put("Content-Type", "application/json");
		// header.put("Authorization",
		// "Bearer YWMtmgU3vr_1EeWskpM7dKETHwAAAVOXYn2r9gzNk8AuYJzoaIzpxtbZsbhw3qw")
		// ;
		String result = resource
				.type(MediaType.APPLICATION_JSON)
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer YWMthq3Kyh1jEeaT30dL2PzoOAAAAVX7rwAkNXC2WKJBQpzwJOwdzEf1Vm_VkX8")
				.post(String.class, obj.toString());
		System.out.println(result);
	}
	
	
	
	public static void testbackApp() {
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/backApp");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();

		JSONArray jsonArray1 = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("", "");
		JSONObject jo1 = new JSONObject();
		jo1.put("", "");
		JSONObject jo2 = new JSONObject();
		jo2.put("", "");
		jsonArray1.add(jo);
		jsonArray1.add(jo1);
		jsonArray1.add(jo2);

		JSONObject obj = new JSONObject();
		obj.put("phone", "13120215658");
		obj.put("code", "186860");
		obj.put("passwdHash", "112233");
		
		String data = null;
		data = Base64Util.encode(new String(obj.toString()));
		obj.clear();
		obj.put("data", data);
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}

	
	public static void testSetQuestion() {
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/setQuestion");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("id", "1e6b5b88-7aae-4438-94bd-15d0b10de0d6");
		obj.put("question", "问题1");
		obj.put("answer", "答案1");
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	public static void alterPasswdByQues() {
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/alterPasswdByQues");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("phone", "018558712241");
		obj.put("question", "你猜猜我是谁");
		obj.put("answer", "不猜不猜就不猜");
		obj.put("passwd", "666666");
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}

	public static void alterQuestion() {
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/alterQuestion");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("id", "735fef7b-947c-4fc1-811c-539d30c83703");
		obj.put("oldQuestion", "你是猪吗");
		obj.put("oldAnswer", "猪，你的鼻子有两个孔~");
		obj.put("newQuestion", "测试密保问题");
		obj.put("newAnswer", "测试密保答案");
		
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	public static void testDeleteFriend(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/deleteFriend");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("userId", "2412");
		obj.put("friendId", "2222");
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	
	public static void testClearFriends(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/clearFriends");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("id", "2412");
		//obj.put("friendId", "2222");
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	public static void testGainInfo() {
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/gainInfo");
			WebResource resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			JSONObject obj = new JSONObject();
			obj.put("id", "8f4f22f762e247fdb5f5cd8e5939a4b9");
			obj.put("query", "4e28273851aa423e65c7ca31967da8d0");
			String data = null;
			data = Base64Util.encode(new String(obj.toString()));
			obj.clear();
			obj.put("data", data);

			String result = resource.type(MediaType.APPLICATION_JSON).post(String.class, obj.toString());
				System.out.println(result);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void testUpdateInfo(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/updateInfo");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();

		JSONObject obj = new JSONObject();
		obj.put("grant_type", "client_credentials");
		obj.put("client_id", "YXA6RFVXsL_zEeWolFH5UG0brw");
		obj.put("client_secret", "YXA6dWxNVCSGAo7-OFQb-uz2yrYqMgU");
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);

	}

	public static void testHUXINRegister() {

		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/feedBack");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("advice", "");
		obj.put("phone", 123456);	
		obj.put("contact", "");
		String encode = Base64Util.encode(obj.toString());
		
		JSONObject inf = new JSONObject();
		inf.put("data", encode);
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, inf.toString());
		System.out.println(result);
	}
	
	
	
	public static void testGetGroupImg() {
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/getGroupImg");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONArray jsonArray1 = new JSONArray();
		/*JSONObject jo1 = new JSONObject();
		jo1.put("groupId", "12");
		JSONObject jo2 = new JSONObject();
		jo2.put("groupId", "122");
		jsonArray1.add(jo1);
		jsonArray1.add(jo2);*/
		
		String json = jsonArray1.toString();
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(json));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	
	

	public static void testUpdatePasswd() {
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/sendUpdateMsg");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("username", "jliu");
		obj.put("password", "123456");
		JSONObject header = new JSONObject();
		header.put("Content-Type", "application/json");
		// header.put("Authorization",
		// "Bearer YWMtmgU3vr_1EeWskpM7dKETHwAAAVOXYn2r9gzNk8AuYJzoaIzpxtbZsbhw3qw")
		// ;
		String result = resource
				.type(MediaType.APPLICATION_JSON)
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer YWMt3mN9kMAmEeWmA4GNRvYXjQAAAVOYpV4WnSg6bTxsava5Dq3_2uNgepUkSfI")
				.post(String.class, obj.toString());
		System.out.println(result);

	}
	
	public static void testHuanXinChatGroups() {

		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://a1.easemob.com/9111011755859790xy/xccim/chatgroups");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("groupname", "TestGroup001");
		obj.put("desc", "This's a simple test. not worry");
		obj.put("public", true);
		obj.put("maxusers", 300);
		obj.put("approval", true);
		obj.put("owner", "Mr.test");		
		JSONObject header = new JSONObject();
		header.put("Content-Type", "application/json");
		// header.put("Authorization",
		// "Bearer YWMtmgU3vr_1EeWskpM7dKETHwAAAVOXYn2r9gzNk8AuYJzoaIzpxtbZsbhw3qw")
		// ;
		String result = resource
				.type(MediaType.APPLICATION_JSON)
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer YWMtIgseIvS7EeWDzuGgk60MJgAAAVTxOo40AHQSrq5pUVkBpHjQxk9XgBZciiw")
				.post(String.class, obj.toString());
		System.out.println(result);

	}
	
	public static void testJPUSH() {

		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://api.jpush.cn/v3/push");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		
		
		JSONObject header = new JSONObject();
		header.put("Content-Type", "application/json");
		// header.put("Authorization",
		// "Bearer YWMtmgU3vr_1EeWskpM7dKETHwAAAVOXYn2r9gzNk8AuYJzoaIzpxtbZsbhw3qw")
		// ;
		String sss = "{\"platform\": \"all\",\"audience\" : \"all\",\"notification\" : {\"alert\" : \"Hi, JPush!\",\"android\" : {},\"ios\" : {\"extras\" : { \"newsid\" : 321}}}}" ;
		String encode = new BASE64Encoder().encode("c8feaad5506121faad7c55fc:8d3771d20923d800ae9eeaf9".getBytes()) ;
		JSONObject obj = JSONObject.fromObject(sss) ;
		String result = resource
				.type(MediaType.APPLICATION_JSON)
				.header("Content-Type", "application/json")
				.header("Authorization","Basic " + encode)
				.post(String.class, obj.toString());
		System.out.println(result);
	}

	
	
	public static void testFind(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/f");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONArray jsonArray1 = new JSONArray();
		JSONObject jo1 = new JSONObject();
		jo1.put("groupId", "12");
		JSONObject jo2 = new JSONObject();
		jo2.put("groupId", "122");
		jsonArray1.add(jo1);
		jsonArray1.add(jo2);
		
		String json = jsonArray1.toString();
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(json));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	
	public static void testFind1(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/getGroup");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject json = new JSONObject();
		json.put("groupId", "1463019351821");
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(json.toString()));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	
	
}
