package xcc_IM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zdh.core.util.Base64Util;

public class TestJson {

	public static void test001() {

		JSONArray array = new JSONArray();
		JSONObject j1 = new JSONObject();
		j1.put("phone", "110");
		JSONObject j2 = new JSONObject();
		j2.put("phone", "120");

		array.add(j1);
		array.add(j2);

		System.out.println(array);

	}

	public static void test002() {
		// JSONArray
		JSONArray array = new JSONArray();
		JSONObject j1 = new JSONObject();
		j1.put("phone", "110");
		JSONObject j2 = new JSONObject();
		j2.put("phone", "120");

		array.add(j1);
		array.add(j2);

		// ----Base64 _ encode
		String encode = Base64Util.encode(array.toJSONString());
		System.out.println("Base64_encode:" + encode);

		// Base_decode
		System.out.println("Base_decode:" + Base64Util.decode(encode));

		String str = "";
		for (int i = 0; i < array.size(); i++) {
			try {
				str = str + " '" + array.getJSONObject(i).getString("phone") + "'" + ",";
			} catch (Exception e) {
			}
		}
		String idStr = str.substring(0, str.length() - 1);

		System.out.println(" __ " + idStr);
	}

	public static void test003() {
		List list = new ArrayList<>();

		Map m1 = new HashMap<>();
		Map m2 = new HashMap<>();
		m1.put("id", 111);
		m2.put("id", 222);
		//

		list.add(m1);
		list.add(m2);
		List l = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map object = (Map) list.get(i);
			Integer object2 = (Integer) object.get("id");
			l.add(object2);
			System.out.println(object2);
		}
		System.out.println(l);

		// String [] aa = new String[3];
		// aa[1] = "aa";
		// aa[2] = "bb";
		// aa[0] = "cc";
		// System.out.println(Arrays.toString(aa));

	}

	public static void test004() {
		String str = "0123456789";
		String sub = "234";
		if (str.indexOf(sub, 0) >= 0) {
			// 要大于等于0
			System.out.println("找到字串：" + sub);
		} else {
			System.out.println("没有找到字串！");
		}

	}

	public static void main(String[] args) {
		// test001();
		// test002();
		// test003();
		// test004();
	}
}
