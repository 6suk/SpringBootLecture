package com.mulcam.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.demo.entity.StaticMap;

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

	@PostMapping("/staticMap")
	public String staticMap(StaticMap map, Model model) {
		StringBuilder sb = new StringBuilder();
		sb.append("https://naveropenapi.apigw.ntruss.com/map-static/v2/raster")
			.append("?w=" + map.getWidth())
			.append("&h=" + map.getHeight())
			.append("&center=" + map.getLng() + "," + map.getLat())
			.append("&level=" + map.getLevel())
			.append("&maptype=" + map.getMaptype())
			.append("&format=" + map.getFormat())
			.append("&scale=" + map.getScale())
			.append("&lang=" + map.getLang())
			.append("&markers=type:d|size:mid|pos:")
			.append(map.getLng() + " " + map.getLat())
			.append("&markers=type:t|size:tiny|label:광진구청|color:red|pos:")
			.append("127.0824 37.5383")
			.append("&X-NCP-APIGW-API-KEY-ID=" + accessId)
			.append("&X-NCP-APIGW-API-KEY=" + secretKey);
		model.addAttribute("url", sb.toString());
		return "map/staticResult";
	}

}
