package com.mulcam.demo.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.demo.entity.StaticMap;
import com.mulcam.demo.service.CsvUtil;
import com.mulcam.demo.service.MapUtil;

@Controller
@RequestMapping("/map")
public class MapController {

	@Value("${naver.accessId}")
	private String accessId;
	@Value("${naver.secretKey}")
	private String secretKey;

	@GetMapping("/staticMap")
	public String staticMapForm() {
		return "map/staticForm";
	}

	@Autowired
	MapUtil mu = new MapUtil();

	@Autowired
	CsvUtil cu = new CsvUtil();

	@PostMapping("/staticMap")
	public String staticMap(StaticMap map, Model model) {
		StringBuilder sb = new StringBuilder();
		sb.append("https://naveropenapi.apigw.ntruss.com/map-static/v2/raster").append("?w=" + map.getWidth())
				.append("&h=" + map.getHeight()).append("&center=" + map.getLng() + "," + map.getLat())
				.append("&level=" + map.getLevel()).append("&maptype=" + map.getMaptype())
				.append("&format=" + map.getFormat()).append("&scale=" + map.getScale())
				.append("&lang=" + map.getLang()).append("&markers=type:d|size:mid|pos:")
				.append(map.getLng() + " " + map.getLat()).append("&markers=type:t|size:tiny|label:광진구청|color:red|pos:")
				.append("127.0824 37.5383").append("&X-NCP-APIGW-API-KEY-ID=" + accessId)
				.append("&X-NCP-APIGW-API-KEY=" + secretKey);
		model.addAttribute("url", sb.toString());
		return "map/staticResult";
	}

	@Value("${roadAddrKey}")
	String confmKey;

	@ResponseBody
	@GetMapping("/roadAddr/{keyword}")
	public String roadAddr(@PathVariable String keyword) throws Exception {
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
		JSONObject jusoItem = (JSONObject) juso.get(0);
		String roadAddr = (String) jusoItem.get("roadAddr");

		return sb.toString() + "<br>" + roadAddr;
	}

	@ResponseBody
	@GetMapping("/geocode")
	public String gecode() throws Exception {
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
		System.out.println(resCode);

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
		Double lng = Double.parseDouble((String) address.get("x"));
		Double lat = Double.parseDouble((String) address.get("y"));

		return lng + " / " + lat;
	}

	@RequestMapping("/hotplaces")
	public String hotPlaces() throws Exception {
		String[] hotPlaces = { "광주 패밀리 랜드", "무등산 국립공원", "광주시립미술관", "전남 대학교", "펭귄마을" };
		String fileName = "d:/SpringTemp/광주명소.csv";
		List<List<String>> allList = new ArrayList<>();

		for (String p : hotPlaces) {
			List<String> oneList = new ArrayList<>();
			String roadAddr = mu.getAddr(p);
			List<String> geocode = mu.gecode(roadAddr);
			oneList.add(p);
			oneList.add(roadAddr);
			oneList.add(geocode.get(0));
			oneList.add(geocode.get(1));
			allList.add(oneList);
		}
		cu.writeCSV(fileName, allList, "\t");
		return "forward:/map/hotPlacesResult";
	}

	@RequestMapping("/hotPlacesResult")
	public String hotPlacesResult(Model model) {
		StringBuilder sb = new StringBuilder();
		List<List<String>> dataList = cu.readCsv("d:/SpringTemp/광주명소.csv", "\t");
		double lngSum = 0.0, latSum = 0.0;
		int i = 0;
		String[] color = { "red", "blue", "green", "purple", "yellow" };

		/** Marker */
		for (List<String> list : dataList) {
			double lng = Double.parseDouble(list.get(2));
			double lat = Double.parseDouble(list.get(3));
			lngSum += lng;
			latSum += lat;
			sb.append("&markers=type:t|size:tiny|pos:").append(lng + " " + lat).append("|color:" + color[i])
					.append("|label:").append(list.get(0));
			i++;
		}
		String marker = sb.toString();
		sb.setLength(0);

		double lngCenter = lngSum / dataList.size();
		double latCenter = latSum / dataList.size();

		sb.append("https://naveropenapi.apigw.ntruss.com/map-static/v2/raster").append("?w=" + 600).append("&h=" + 400)
				.append("&center=" + lngCenter + "," + latCenter).append("&level=" + 10).append("&scale=" + 2)
				.append("&format=png").append(marker).append("&X-NCP-APIGW-API-KEY-ID=" + accessId)
				.append("&X-NCP-APIGW-API-KEY=" + secretKey);

		model.addAttribute("url", sb.toString());
		return "map/staticResult";
	}

}
