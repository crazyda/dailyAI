package com.axp.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class LngAndLatUtil {

	public static Map<String, Double> getLngAndLat(String address) {
		Map<String, Double> map = new HashMap<String, Double>();
		String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address.replace(" ", "").replace("	", "") + "&output=json&ak=FhqpjmNavgBUzcjqOlHfeqrR";
		String json = loadJSON(url);
		double lng = 0000.00000000000;
		double lat = 0000.00000000000;
		
		JSONObject obj = JSONObject.fromObject(json);
		if (obj.get("status").toString().equals("0")) {
			lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
			lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
		} else {
			System.out.println("没有找到相应的地址");
		}
		map.put("lng", lng);
		map.put("lat", lat);
		return map;
	}

	public static String getDistrict(double lng, double lat) {
		String district = "";
		String url = "http://api.map.baidu.com/geocoder/v2/?ak=FhqpjmNavgBUzcjqOlHfeqrR&callback=renderReverse&location=" + lat + "," + lng + "&output=json&pois=";
		String json = loadJSON(url);
		json = json.replace("renderReverse&&renderReverse(", "");
		json = json.replace(")", "");
		JSONObject obj = JSONObject.fromObject(json);
		if (obj.get("status").toString().equals("0")) {
			district = obj.getJSONObject("result").getJSONObject("addressComponent").getString("district");
		} else {
			System.out.println("没有找到相应的地址");
		}

		return district;
	}

	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = null;
			yc = oracle.openConnection();

			InputStream input = yc.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[input.available()];
		    int i = 0;
		    while ((i = input.read(buffer)) != -1) {
		        output.write(buffer, 0, i);
		    }
		    String sb = new String(buffer,"UTF-8");
		    json.append(sb);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
