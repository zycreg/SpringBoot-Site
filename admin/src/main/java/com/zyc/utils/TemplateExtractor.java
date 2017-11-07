package com.zyc.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * �?��模板解析器�? <br/>
 * 典型的模板可以像这样: this is ${name}. 其中${}称为取�?标签，name称为变量名称�?br/>
 * ${name}的意义为取模板环境中变量名为"name"的�?�?br/>
 * 通过{@link #addParam(String, String)}为模板环境添加变量名称和变量值，变量值不能包含标签�?<br/>
 * 调用{@link #extrat()} 解析模板，并得到解析后的内容�?br/>
 * <b>请注意，取�?标签是区分大小写�?/b>
 * 
 * @author Gene.zhang
 * @date 2012-3-2
 */
public class TemplateExtractor {
	private Map<String,String> paramMap = new HashMap<String, String>();
	private final Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
	
	public TemplateExtractor(){
	}
	/**
	 * 使用指定的环境参数初始化�?��模板解析器�?
	 * @param params 环境参数�?
	 */
	public TemplateExtractor(Map<String,String> params) {
		if(params!=null){
			this.paramMap=params;
		}
	}
	/**
	 * 清空模板解析器的参数环境�?
	 * @date 2012-3-21
	 */
	public void cleanParams(){
		this.paramMap.clear();
	}
	/**
	 * 为模板环境添加参数�?参数可以带标签�?
	 * @date 2012-3-2
	 * @param key 参数名称
	 * @param value 参数�?
	 */
	public void addParam(String key,String value){
		if(key!=null && value!=null)
			this.paramMap.put(key,value);
	}
	public void addParam(Map<String,String> params){
		if(params!=null)
			this.paramMap.putAll(params);
	}
	
	/**
	 * 解析指定的模板�?
	 * @date 2012-3-2
	 * @param temp 模板内容�?
	 * @return 解析之后的内�?
	 */
	public String extract(String temp){
		if(temp==null) return null;
		return this.ext(temp);
	}
	/**
	 * 解析指定模板中的标签�?
	 * @date 2012-3-2
	 * @param temp
	 * @return
	 */
	private String ext(String temp){
		Matcher m = pattern.matcher(temp);
		while(m.find()){
			String label =m.group(0);
			String var = m.group(1).trim();
			String val =this.paramMap.get(var);
			if(val==null) val="";
			temp=temp.replace(label, val);
		}
		return temp;
	}
	public static void main(String[] args) {
		String tes="this is ${ naMe}, ${ a },${b} 长要要东";
		TemplateExtractor te = new TemplateExtractor();
		Map<String,String> params = new HashMap<String, String>();
		params.put("b", "dddddddddddddddddddddddddddd");
		te.addParam("name", "zjweii");
		te.addParam("a", "aaaaaaaaa");
		te.addParam(params);
		System.out.println(te.extract(tes));
	}
}
