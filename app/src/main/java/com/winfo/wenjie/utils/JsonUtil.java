package com.winfo.wenjie.utils;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * json 和 实体类之间的相互转换
 * @author 00
 *
 */
public class JsonUtil {
	/**
	 * 将一个实体对象  转换成一个json字符串  提示对象中可包含集合
	 * @param t 实体类
	 * @return
	 */
	public static <T> String beanToJson(T t){
		Gson gson = new Gson();
		return gson.toJson(t);
	}
	
	/**
	 * 将一个json字符串 转换成一个实体类对象 可包含list
	 * @param json
	 * @param
	 * @return
	 */
	public static <T> T jsonToBean(String json,Class<T> class1) throws InstantiationException, IllegalAccessException{
		Gson gson = new Gson();
		T t;
		t=gson.fromJson(json, class1);
		return t;
	}
	
	/**
	 * 将json字符串转换成一个json对象
	 * @param str
	 * @return
	 */
	public static JSONObject stringToJson(String str){
		try {
			return new JSONObject(str);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getString(InputStream is){
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[1024];
			int len;
			while((len = is.read(buffer)) != -1){
				baos.write(buffer, 0, len);
			}
			
			byte[] byteArray = baos.toByteArray();
			//String str = new String(byteArray);
			
			return new String(byteArray,"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	/**
	 * 从assert文件夹中读取json文件，然后转化为json对象
	 * @throws Exception 
	 */
	public static JSONObject getJsonDataFromAssets(Context context,String jsonFileName) throws Exception{
		JSONObject mJsonObj;
		StringBuilder sb = new StringBuilder();
		InputStream is = context.getAssets().open(jsonFileName);
		int len;
		byte[] buf = new byte[1024];
		while ((len = is.read(buf)) != -1){
			sb.append(new String(buf, 0, len, "UTF-8"));
		}
		is.close();
		mJsonObj = new JSONObject(sb.toString());
		return mJsonObj;
	}
	
}
