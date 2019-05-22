package com.education.classroom.core.dict.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.education.classroom.core.dict.entity.Dict;

public class DictUtilsEx {
	
	/**
	 * 字典表数据存放model
	 * 2016年4月18日
	 * By 朱杰
	 * @param model 页面Model
	 * @param types 字典type集合
	 * @return
	 */
	public static void addDicts(Model model,String[] types) {
		Map<String, List<Dict>> map = new HashMap<String, List<Dict>>();
		for (String type : types) {
			map.put(type, DictUtils.getDictList(type));
		}
		model.addAttribute("dicts",map);
	}
	
	/**
	 * 字典表数据集合取得
	 * 2016年4月18日
	 * By 朱杰
	 * @param types 字典type集合
	 * @return
	 */
	public static Map<String,List<Dict>> getDicts(String[] types) {
		Map<String, List<Dict>> map = new HashMap<String, List<Dict>>();
		for (String type : types) {
			map.put(type, DictUtils.getDictList(type));
		}
		return map;
	}
	
	/**
	 * 字典表数据存放model
	 * 2016年7月29日
	 * By zhujie
	 * @param dicts 字典集合
	 * @param dictId 字典值
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String dictName(List<Dict> dicts, String dictId, String defaultValue) {
		if (dicts != null && dicts.size() > 0) {
			for (Dict d : dicts) {
				if (d.getValue().equals(dictId)) {
					return d.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	/**
	 * 获取字典参数的所有字典数据
	 * 2016年7月29日
	 * By zhujie
	 * @param types
	 * @return
	 */
	public static Map<String, List<Dict>> getDictMap(String[] types) {
		Map<String, List<Dict>> map = new HashMap<String, List<Dict>>();
		for (String type : types) {
			map.put(type, DictUtils.getDictList(type));
		}
		return map;
	}
	
}
