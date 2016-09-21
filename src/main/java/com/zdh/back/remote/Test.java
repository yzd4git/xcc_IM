package com.zdh.back.remote;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.alibaba.fastjson.JSON;
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
//		 testLogin() ;
		 //testHUXINToken() ;
//		testHUXINRegister();
		//testJPUSH() ;
//		updateNumberPasswd();	
//		GetSalt();
//		getFriend();
//		getNumberPasswd();
//		testGetGroupImg();
//		testFind1();
//		System.out.println(checkEmail("luoyj@136.cn"));
//		testIsRegiest();
//		getVersion();
//		getToken();
//		RegiestUser();
//		DoFriend();
//		sendMsg();
//		getCode();
//		Regiest();
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/xcc_IM/IM/rest/").build();
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
	
	public static void updateNumberPasswd(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/updateNumberPasswd");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject json = new JSONObject();
		json.put("id", "13131");
		json.put("passwdHash", "1231321");
		json.put("numberPasswd", "1231321");
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(json.toString()));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	public static void GetSalt(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/getSalt");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject json = new JSONObject();
		json.put("phone", "18510195274");
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(json.toString()));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	public static void getFriend(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/getFriend");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject json = new JSONObject();
		json.put("im_username", "13131");
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(json.toString()));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	public static void getNumberPasswd(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/getNumberPasswd");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject json = new JSONObject();
		json.put("phone", "13913476956");
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(json.toString()));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	public static boolean checkEmail(String account){
		String regex = "^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[_.])+[A-Za-z0-9]{2,5}$" ;
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(account); 
		return m1.matches(); 
	}
	
	
	public static void testIsRegiest() {
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/isRegister");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONArray array =  new JSONArray();
		JSONObject j1 = new JSONObject();
		j1.put("phone", "18510195274");
		JSONObject j2 = new JSONObject();
		j2.put("phone", "13120215658");
		
		array.add(j1);
		array.add(j2);
		System.out.println(array);
//		JSONObject json = new JSONObject();
//		JSONObject j1 = new JSONObject();
//		j1.put("phone", "13913476956");
//		json.put("phone", "18510195274");
		
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(array.toString()));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	
	
	public static void getVersion(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/getVersion");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject json = new JSONObject();
		json.put("type", "0");
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(json.toString()));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	
	public static void getCode(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://localhost:8080/xcc_IM/rest/remoteApi/getVerifCode");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject json = new JSONObject();
		json.put("phone", "18653448926");
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(json.toString()));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	
	public static void Regiest(){
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
		JSONObject json = new JSONObject();
		json.put("phone", "18510195274");
		json.put("passwd", "123");
		json.put("code", "444452");
		json.put("salt", "123");
		JSONObject obj = new JSONObject();
		obj.put("data", Base64Util.encode(json.toString()));
		String result = resource.type(MediaType.APPLICATION_JSON).post(
				String.class, obj.toString());
		System.out.println(result);
	}
	
	
	public static void getToken(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/token");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("grant_type", "client_credentials");
		obj.put("client_id", "YXA61nUJAL5VEeWRGjmxKtAZGA");
		obj.put("client_secret", "YXA6pUUuB21OW0RwMJLf7NdhJ7ZsXsM");
		JSONObject header = new JSONObject();
		header.put("Content-Type", "application/json");
		// header.put("Authorization",
		// "Bearer YWMtmgU3vr_1EeWskpM7dKETHwAAAVOXYn2r9gzNk8AuYJzoaIzpxtbZsbhw3qw")
		// ;
		String result = resource
				.type(MediaType.APPLICATION_JSON)
				.header("Content-Type", "application/json")
				.post(String.class, obj.toString());
		System.out.println(result);
	}
	
	public static void RegiestUser(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/users");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
		obj.put("username", "1001");
		obj.put("password", "5d180c53bf5608a9c2e3a5b0d064bd38");
		JSONObject header = new JSONObject();
		header.put("Content-Type", "application/json");
		// header.put("Authorization",
		// "Bearer YWMtmgU3vr_1EeWskpM7dKETHwAAAVOXYn2r9gzNk8AuYJzoaIzpxtbZsbhw3qw")
		// ;
		String result = resource
				.type(MediaType.APPLICATION_JSON)
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer ")
				.post(String.class, obj.toString());
		System.out.println(result);
	}
	
	
	public static void DoFriend(){
		Client client = Client.create();
		URI u = null;
		try {
			u = new URI("http://a1.easemob.com/9111011755859790xy/xccim/users/1001/contacts/users/4e28273851aa423e65c7ca31967da8d0");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(u);
		WebResource resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		JSONObject obj = new JSONObject();
//		obj.put("username", "1001");
//		obj.put("password", "5d180c53bf5608a9c2e3a5b0d064bd38");
		JSONObject header = new JSONObject();
		header.put("Content-Type", "application/json");
		// header.put("Authorization",
		// "Bearer YWMtmgU3vr_1EeWskpM7dKETHwAAAVOXYn2r9gzNk8AuYJzoaIzpxtbZsbhw3qw")
		// ;
		String result = resource
				.type(MediaType.APPLICATION_JSON)
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer YWMtgW-UGHTdEeaWfIlWPamWJwAAAVg4-C9Kip1IrqmiPqcLdEeddgrzK3tpMkE")
				.post(String.class, obj.toString());
		System.out.println(result);
	}
	
	
	public static void sendMsg(){
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
		String username = "4e28273851aa423e65c7ca31967da8d0";
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
						"Bearer YWMtHWpAjnVgEeayNjE5GcIX8gAAAVg8UCVTLBsIIi1R1j9s-gvHAa85fEduPPY")
				.post(String.class, obj.toString());
		System.out.println(result);
	}
}
