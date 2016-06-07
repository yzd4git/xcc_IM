package com.zdh.back;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class TestJson {

	public static void main(String[] args) {
		
//        jsonArray.put(jo);
//        jsonArray.put(jo1);
//		String json ="[{\"id\":\"111\"},{\"id\":\"222\"},{\"id\":\"999\"}]";
		
		
	testJson01();
//		testJson02();
		
	}
	public static String testJson01(){
		JSONArray jsonArray1 = new JSONArray();
        JSONObject jo = new JSONObject();
        jo.put("id", "2413");

        JSONObject jo1 = new JSONObject();
        jo1.put("id", "2412");
        
        jsonArray1.add(jo);
        jsonArray1.add(jo1);
        
//        System.out.println(jsonArray1);
        
        
    	JSONArray jsonArray = JSONArray.fromObject(jsonArray1);
		System.out.println(jsonArray);
		String str = "";
		 for (int i = 0; i < jsonArray.size(); i++) {
			 System.out.println(jsonArray.getJSONObject(i).getString("id"));
			 str = str+" '" + jsonArray.getJSONObject(i).getString("id")+ "' "+ " ,";
        }
		 String idStr = str.substring( 0, str.length() -1 );
		 System.out.println(idStr);
        
        return idStr;
	}
	
	public static String testJson02() {
		String json ="[{\"id\":\"111\",\"name\":\"\",\"age\":\"13\"},{\"id\":\"222\",\"name\":\"Z4\",\"age\":\"14\"},{\"id\":\"999\",\"name\":\"Z5\",\"age\":\"15\"}]";
		JSONArray jsonArray = JSONArray.fromObject(json);
		String id = "";
		String name = "";
		String age = "";
		JSONArray newJson = new JSONArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			id = jsonArray.getJSONObject(i).getString("id");
			System.out.println(id);
			JSONObject idJson  = new JSONObject();
			idJson.put("id", id);
			name = jsonArray.getJSONObject(i).getString("name");
			System.out.println(name);
			JSONObject nameJson = new JSONObject();
			nameJson.put("name", name);
			age = jsonArray.getJSONObject(i).getString("age");
			System.out.println(age);
			JSONObject ageJson = new JSONObject();
			ageJson.put("age", age);

			newJson.add(idJson);
			newJson.add(nameJson);
			newJson.add(ageJson);
			
		}
//		System.out.println("id:" + id);
//		System.out.println("name:" + name);
//		System.out.println("age:" + age);
//		
//		

		return newJson.toString();
	}
	
}
