package com.zyc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jerry Zhang
 * Create date 2010-5-24
 * @version 1.0.0
 * @email jerry.zhang@jamboree.com.cn
 */
public class ReverseTreeListUtils {
	private static List targetList = null;
	private static Map<String, Integer> sort_Map = null;
	private static int sort_No;
	private static int pNode_Id;
	private static int step;
	public ReverseTreeListUtils(){
		targetList = new ArrayList();
		sort_Map = new HashMap<String, Integer>();
		sort_No = 0;
		pNode_Id = 0;
		step = 1;
	}
	public static ReverseTreeListUtils newInstance(){
		return new ReverseTreeListUtils();
	}
	public static List reverse(List sourceList){
		for (int i = 0; i < sourceList.size(); i++) {
			Map tempObj = (Map) sourceList.get(i);
			add(sourceList, tempObj);
		}
		return targetList;
	}
	
	public static void add(List sourceList,Map tempObj){
		String currentId = tempObj.get("id") + "";
		String CLASSID = tempObj.get("class_id") + "";
		if (StringUtils.isEmpty(CLASSID)) {//顶级栏目
			step = 1;
			targetList.add(tempObj);
			tempObj.put("step", step);
			addChild(sourceList,currentId);
		}
	}
	
	public static void addChild(List sourceList,String currentId){
		for (int i = 0; i < sourceList.size(); i++) {
			Map _tempObj = (Map) sourceList.get(i);
			String _currentId = _tempObj.get("id") + "";
			String _currentName = _tempObj.get("class_name") + "";
			String _parentId = _tempObj.get("class_id") + "";
			if (!StringUtils.isEmpty(_parentId) && _parentId.equals(currentId)) {
				step++;
				_tempObj.put("step", step);
				targetList.add(_tempObj);
				addChild(sourceList,_currentId);
			}
		}
		step--;
	}
}
