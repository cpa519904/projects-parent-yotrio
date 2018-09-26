package com.yotrio.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author Watson
 * @since 3.7
 */
public final class JsonUtil {
	public static final SerializeConfig SERIALIZE_CONFIG = new SerializeConfig();
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	static{
		SERIALIZE_CONFIG.put(Date.class,new SimpleDateFormatSerializer(DATE_TIME_FORMAT));
		SERIALIZE_CONFIG.put(Timestamp.class,new SimpleDateFormatSerializer(DATE_TIME_FORMAT));
		SERIALIZE_CONFIG.put(Time.class,new SimpleDateFormatSerializer(DATE_TIME_FORMAT));
		SERIALIZE_CONFIG.put(java.sql.Date.class,new SimpleDateFormatSerializer(DATE_TIME_FORMAT));
	}
	/**
	 * javaObject >>> JsonObject
	 * @param javaObject
	 * @return
	 */
	public static final JSONObject parseJavaObject(Object javaObject) {
        return (JSONObject) JSON.toJSON(javaObject);
    }
	/**
	 * JsonObject >>> javaObject
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static final <T> T toJavaObject(JSON json, Class<T> clazz){
		return JSON.toJavaObject(json,clazz);
	}
	public static final <T> T toJavaObject(String text, Class<T> clazz){
		return JSON.parseObject(text,clazz);
	}
	
	/**
	 * 将 list 对象  >>> JsonObject
	 * @param list
	 * @return
	 */
	public static final JSONArray parseJavaList(List<?> list) {
        return (JSONArray) JSON.toJSON(list);
    }
	
	/**
	 * 封装将json对象转换为java集合对象
	 * @param <T>
	 * @param clazz List对象的类 如 User.class
	 * @param jsons
	 * @return
	 */
	public static final <T> List<T> getJavaCollection(String jsons, Class<T> clazz){
		JSONArray array = JSON.parseArray(jsons);
		return jsonArrayToList(array,clazz);
	}
	/**
	 * JSONArray >>> List<T> 等同于 jsonArrayToList
	 * @param array json数组
	 * @param clazz List对象的类 如 User.class
	 * @return
	 */
	public static final <T> List<T> toJavaList(JSONArray array, Class<T> clazz) {
		return jsonArrayToList(array,clazz);
    }
	/**
	 * JSONArray >>> List<T> 等同于 jsonArrayToList
	 * @param array json数组
	 * @param clazz List对象的类 如 User.class
	 * @return
	 * @throws Exception
	 */
	public static final <T> List<T> jsonArrayToList(JSONArray array, Class<T> clazz){
		List<T> objs = null;
		if (array != null) {
			objs = new ArrayList<T>();
			for (int i=0;i<array.size();i++){
				JSONObject jsonObject = array.getJSONObject(i);
				T obj = JSON.toJavaObject(jsonObject,clazz);
				objs.add(obj);
			}
		}
		return objs;
	}
	/**
	 * 对象转json字符串
	 * @param object
	 * @return
	 */
	public static final String toJsonString(Object object) {
		//两种写法
		return JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
		//return JSON.toJSONString(object,SERIALIZE_CONFIG);
	}
	/**
	 * 转JSON捕获异常的
	 * @param object
	 * @return
	 */
	public static final String toJsonStringNoEx(Object object){
		try{
			return JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
		}catch (Exception e) {
			return "format json error";
		}
	}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	public static String convertMap(Map object) throws Exception {
		Iterator<?> ite = object.keySet().iterator();
		while (ite.hasNext()) {
			Object key = ite.next();
			if (object.get(key) == null) {
				object.put(key, "");
			}
		}
		return toJsonString(object);
	}
	@SuppressWarnings({"rawtypes"})
	public static final String convert(Collection object) {
		return toJsonString(object);
	}
	public static final String convert(Object object) throws Exception {
		return toJsonString(object);
	}
	public static final String convert(Boolean object) throws Exception {
		return toJsonString(object);
	}
	public static final Map<String,String> fromJsonToMap(String json) throws Exception {
		Map<String, Object> map = JSON.parseObject(json);
		Map<String,String> retMap = new HashMap<String,String>();
		for(String key:map.keySet()){
			retMap.put(key, String.valueOf(map.get(key)));
		}
		return retMap;
	}
	public static final boolean isBadJson(String json) {
		return !isJson(json);
	}
	/**
	 * 最好不要用这个方法，直接转，如果异常就不是JSON了
	 * @param json
	 * @return
	 */
	public static final boolean isJson(String json) {
		if (StringUtil.isBlankOrEmpty(json)) {
			return false;
		}
		try {
			JSON.parse(json);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**===================一下方法复写JSON对象的方法===================**/
    public static final JSONObject parseObject(String text) {
    	return JSON.parseObject(text);
    }
    public static final <T> T parseObject(String text, Class<T> clazz){
    	return JSON.parseObject(text,clazz);
    }
    public static final JSONArray parseArray(String text) {
    	return JSON.parseArray(text);
    }
    /**===================扩展获取默认值(方便对json数据做异常时候方便返回想要的值)===================**/
    /**
     * 获取字符串方法
     * @param json 如果此参数==null 则直接返回默认值
     * @param key
     * @return 所有异常默认返回 ""
     */
    public static final String getString(JSONObject json, String key){
    	return getString(json,key,"");
    }
    public static final String getString(JSONObject json, String key, String defaultVal){
    	try{
    		if(json==null || key==null || json.getString(key)==null)return defaultVal;
    		String val = json.getString(key);
    		if(val==null)return defaultVal;
    		return val;
    	}catch (Exception e){
			return defaultVal;
    	}
    }
    /**
     * 获取整型方法
     * @param json	如果此参数==null 则直接返回默认值
     * @param key	需要获取值的key
     * @return 所有发生了异常的返回 0
     */
    public static final int getInt(JSONObject json, String key){
    	return getInt(json,key,0);
    }
    /**
     * 获取整型方法
     * @param json	如果此参数==null 则直接返回默认值
     * @param key	需要获取值的key
     * @param defaultVal	默认值
     * @return 所有发生了异常的返回 0
     */
    public static final int getInt(JSONObject json, String key, int defaultVal){
    	try{
    		if(json==null || key==null || json.getString(key)==null)return defaultVal;
    		return json.getIntValue(key);
    	}catch (Exception e) {
			return defaultVal;
    	}
    }
    /**
     * 获取浮点方法，发生异常返回 0D
     * @param json	如果此参数==null 则直接返回默认值
     * @param key	需要获取值的key
     * @return
     */
    public static final double getDouble(JSONObject json, String key){
    	return getDouble(json,key,0D);
    }
    /**
     * 获取浮点方法
     * @param json	如果此参数==null 则直接返回默认值
     * @param key	需要获取值的key
     * @param defaultVal	默认值
     * @return
     */
    public static final double getDouble(JSONObject json, String key, double defaultVal){
    	try{
    		if(json==null || key==null || json.getString(key)==null)return defaultVal;
    		return json.getDoubleValue(key);
    	}catch (Exception e) {
			return defaultVal;
    	}
    }
    /**
     * 获取长整型方法 发生异常返回 0L
     * @param json	如果此参数==null 则直接返回默认值
     * @param key	需要获取值的key
     * @return
     */
    public static final long getLong(JSONObject json, String key){
    	return getLong(json,key,0L);
    }
    /**
     * 获取长整型方法
     * @param json	如果此参数==null 则直接返回默认值
     * @param key	需要获取值的key
     * @param defaultVal	默认值
     * @return
     */
    public static final long getLong(JSONObject json, String key, long defaultVal){
    	try{
    		if(json==null || key==null || json.getString(key)==null)return defaultVal;
    		return json.getLongValue(key);
    	}catch (Exception e) {
			return defaultVal;
    	}
    }
}
