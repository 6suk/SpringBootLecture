package com.mulcam.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;

public class MapUtil {

	@Value("${roadAddrKey}")
	private String confmKey;

	@Value("${naver.accessId}")
	private String accessId;
	
	@Value("${naver.secretKey}")
	private String secretKey;
	
	public String getAddr(String keyword) throws Exception {
		int curPage = 1;
		int countPage = 10;
		String resultType = "json";
		keyword = URLEncoder.encode(keyword, "utf-8");
		StringBuilder sb = new StringBuilder();
		sb.append("https://business.juso.go.kr/addrlink/addrLinkApi.do").append("?confmKey=" + confmKey)
				.append("&currentPage=" + curPage).append("&countPerPage=" + countPage).append("&keyword=" + keyword)
				.append("&resultType=" + resultType);
		URL url = new URL(sb.toString());
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
		sb.setLength(0);

		String tmpStr = null;
		while ((tmpStr = br.readLine()) != null) {
			sb.append(tmpStr);
		}
		br.close();

		/** JSON 데이터에서 원하는 값 추출 */
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(sb.toString());
		JSONObject results = (JSONObject) obj.get("results");
		JSONArray juso = (JSONArray) results.get("juso");
		if(juso == null || juso.size() == 0) return null;
		JSONObject jusoItem = (JSONObject) juso.get(0);
		String roadAddr = (String) jusoItem.get("roadAddr");

		return sb.toString() + "<br>" + roadAddr;
	}
	
	public List<String> gecode() throws Exception {
		List<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		String query = "서울특별시 광진구 자양로 117(자양동)";
		query = URLEncoder.encode(query, "UTF-8");

		sb.append("https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode").append("?query=" + query);

		URL url = new URL(sb.toString());

		/** 헤더 설정 */
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", accessId);
		conn.setRequestProperty("X-NCP-APIGW-API-KEY", secretKey);
		conn.setDoInput(true);

		/** 응답 결과 확인 */
		int resCode = conn.getResponseCode();
		
		/** 데이터 수신 */
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		sb.setLength(0);

		String tmpStr = null;
		while ((tmpStr = br.readLine()) != null) {
			sb.append(tmpStr);
		}
		br.close();
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(sb.toString());
		JSONArray addresses = (JSONArray) obj.get("addresses");
		JSONObject address = (JSONObject) addresses.get(0);
		String lng = (String)address.get("x");
		String lat = (String)address.get("y");
		list.add(lng);
		list.add(lat);
		
		return list;
	}
	
	
}
