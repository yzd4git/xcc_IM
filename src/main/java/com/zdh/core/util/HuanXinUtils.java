package com.zdh.core.util;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
/**
 * 环信端接口
 * @author yzd
 *
 */
public class HuanXinUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	/**
	 * 获取环信端的Token
	 * @return
	 */
	public static String testHUXINToken() {

		Client client = Client.create();
		String result = "";
		URI u = null;
		try {
//			u = new URI("http://a1.easemob.com/9111011755859790xy/xccim/token");
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/token");
			WebResource resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();

			JSONObject obj = new JSONObject();
			obj.put("grant_type", "client_credentials");
//			obj.put("client_id", "YXA6RFVXsL_zEeWolFH5UG0brw");
//			obj.put("client_secret", "YXA6dWxNVCSGAo7-OFQb-uz2yrYqMgU");
			obj.put("client_id", "YXA61nUJAL5VEeWRGjmxKtAZGA");
			obj.put("client_secret", "YXA6pUUuB21OW0RwMJLf7NdhJ7ZsXsM");
			result = resource.type(MediaType.APPLICATION_JSON).post(String.class, obj.toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 环信端用户注册
	 * @param username 用户昵称
	 * @param password 用户密码
	 * @param token    注册时所需要的Token
	 * @return 
	 */
	public static JSONObject testHUXINRegister(String username, String password, String token) {

		Client client = Client.create();
		URI u = null;
		String result = "";
		try {
//			u = new URI("http://a1.easemob.com/9111011755859790xy/xccim/users");
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/users");
			WebResource resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			JSONObject obj = new JSONObject();
			obj.put("username", username);
			obj.put("password", password);
			JSONObject header = new JSONObject();
			header.put("Content-Type", "application/json");
			// header.put("Authorization", "Bearer
			// YWMtmgU3vr_1EeWskpM7dKETHwAAAVOXYn2r9gzNk8AuYJzoaIzpxtbZsbhw3qw");
			result = resource.type(MediaType.APPLICATION_JSON).header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).post(String.class, obj.toString());
			JSONObject resultJson = JSON.parseObject(result);
			return resultJson;
		} catch (URISyntaxException e) {	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "1");// 注册失败
			return jsonObject;
		}
	}
	
	/**
	 * 环信端用户重置密码
	 * @param phone MD5后的值为环信端用户的昵称
	 * @param newpassword 新的密码
	 * @param token 环信Token
	 * @return
	 */
	public static JSONObject ResetPasswd(String phone , String newpassword , String token){
		Client client = Client.create();
		URI u = null;
		String result = "";
		try {
//			u = new URI("http://a1.easemob.com/9111011755859790xy/xccim/users/" + MD5Util.md5Encode(phone) + "/password");
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/users/" + MD5Util.md5Encode(phone) + "/password");
			WebResource resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			JSONObject obj = new JSONObject();
			obj.put("newpassword", newpassword);
			JSONObject header = new JSONObject();
			header.put("Content-Type", "application/json");
			result = resource.type(MediaType.APPLICATION_JSON).header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).post(String.class, obj.toString());
			JSONObject resultJson = JSON.parseObject(result);
			return resultJson;
		} catch (Exception e) {	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "1");
			return jsonObject;
		}
	}
	
	
	public static JSONObject AlterNickName(String phone , String token){
		Client client = Client.create();
		URI u = null;
		String result = "";
		try {
//			u = new URI("http://a1.easemob.com/9111011755859790xy/xccim/users/" + MD5Util.md5Encode(phone) + "/password");
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/users/" + MD5Util.md5Encode(phone));
			WebResource resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			JSONObject obj = new JSONObject();
			obj.put("nickname", MD5Util.md5Encode(phone));
			JSONObject header = new JSONObject();
			header.put("Content-Type", "application/json");
			result = resource.type(MediaType.APPLICATION_JSON).header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).post(String.class, obj.toString());
			JSONObject resultJson = JSON.parseObject(result);
			return resultJson;
		} catch (Exception e) {	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "1");
			return jsonObject;
		}
	}
	
	
	
	
	/**
	 * 禁用某个IM账户<暂时不用>
	 * @param phone
	 * @param token
	 * @return
	 */
	public static JSONObject Deactivate(String phone , String token){
		Client client = Client.create();
		URI u = null;
		String result = "";
		try {
//			u = new URI("http://a1.easemob.com/9111011755859790xy/xccim/users/" + MD5Util.md5Encode(phone) + "/deactivate");
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/users/" + MD5Util.md5Encode(phone) + "/deactivate");
			WebResource resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			JSONObject obj = new JSONObject();
			JSONObject header = new JSONObject();
			header.put("Content-Type", "application/json");
			result = resource.type(MediaType.APPLICATION_JSON).header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).post(String.class, obj.toString());
			JSONObject resultJson = JSON.parseObject(result);
			return resultJson;
		} catch (Exception e) {	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "1");
			return jsonObject;
		}
	}	
	
	/**
	 * 解禁一个IM账户<暂时不用>
	 * @param phone
	 * @param token
	 * @return
	 */
	public static JSONObject Activate(String phone , String token){
		Client client = Client.create();
		URI u = null;
		String result = "";
		try {
//			u = new URI("http://a1.easemob.com/9111011755859790xy/xccim/users/" + MD5Util.md5Encode(phone) + "/activate");
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/users/" + MD5Util.md5Encode(phone) + "/activate");
			WebResource resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			JSONObject obj = new JSONObject();
			JSONObject header = new JSONObject();
			header.put("Content-Type", "application/json");
			result = resource.type(MediaType.APPLICATION_JSON).header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).post(String.class, obj.toString());
			JSONObject resultJson = JSON.parseObject(result);
			return resultJson;
		} catch (Exception e) {	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "1");
			return jsonObject;
		}
	}
	
	/**
	 * 删除 IM 用户
	 * @param phone
	 * @param token
	 * @return
	 */
	public static JSONObject Delete(String phone , String token){
		Client client = Client.create();
		URI u = null;
		String result = "";
		try {
//			u = new URI("http://a1.easemob.com/9111011755859790xy/xccim/users/" + MD5Util.md5Encode(phone));
			u = new URI("http://a1.easemob.com/9111011755859790xy/demo/users/" + MD5Util.md5Encode(phone));
			WebResource resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			JSONObject obj = new JSONObject();
			JSONObject header = new JSONObject();
			header.put("Content-Type", "application/json");
			result = resource.type(MediaType.APPLICATION_JSON).header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).post(String.class, obj.toString());
			JSONObject resultJson = JSON.parseObject(result);
			return resultJson;
		} catch (Exception e) {	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "1");
			return jsonObject;
		}
	}
}
