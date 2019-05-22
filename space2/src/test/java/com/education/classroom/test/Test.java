package com.education.classroom.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
	/*	System.out.println(SystemService.entryptPassword("admin"));
		System.out.println(PasswordUtil.encodePassword("admin"));*/
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		Map<String,Object> map1 = new LinkedHashMap<String,Object>();
	
		Map<String,Object> map1_1 = new LinkedHashMap<String,Object>();
		map1_1.put("a101", "101");
		Map<String,Object> map1_2 = new LinkedHashMap<String,Object>();
		map1_2.put("a101", "102");
		map1.put("id1", map1_1);
		map1.put("id3", map1_2);
		
		Map<String,Object> map2 = new LinkedHashMap<String,Object>();
		Map<String,Object> map2_1 = new LinkedHashMap<String,Object>();
		map2_1.put("a101", "201");
		Map<String,Object> map2_2 = new LinkedHashMap<String,Object>();
		map2_2.put("a101", "202");
		map2.put("id1", map2_1);
		map2.put("id2", map2_2);
		
		map.put("个人", map1);
		map.put("组", map2);
		
		Map<String,Object> m = (Map<String, Object>)((Map<String, Object>) map.get("个人")).get("id2");
		if(m == null){
			m = (Map<String, Object>)((Map<String, Object>) map.get("组")).get("id2");
		}
		System.out.println(m.get("a101"));
		
		String ss ="{'data':{'/5524/6654':{'list':[{'id':'11','identity':'0','nameValue':'mao'},{'id':'22','identity':'1','nameValue':'amao'}]}}}";
	
/*		 JSONObject jsonObject = new JSONObject(ss);  
	        Map<String, Object> resultMap = new HashMap<String, Object>();  
	        Iterator<String> iter = jsonObject.keys();  
	        String key=null;  
	        Object value=null;  
	        while (iter.hasNext()) {  
	            key=iter.next();  
	            value=jsonObject.get(key);  
	            resultMap.put(key, value);  
	        }  
	        System.out.println(resultMap); */

		
		
	}
}
