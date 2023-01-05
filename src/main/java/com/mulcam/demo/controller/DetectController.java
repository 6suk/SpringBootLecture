package com.mulcam.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/detect")
public class DetectController {

	@Value("${naver.accessId}")
	private String accessId;
	@Value("${naver.secretKey}")
	private String secretKey;

	@SuppressWarnings("unused")
	@GetMapping("/naver")
	public String naver(Model model) throws Exception {
		StringBuffer sb = new StringBuffer();

		String paramName = "image";
		File uploadFile = new File("d:/springTemp/02/yolo.jpg");
		String apiURL = "https://naveropenapi.apigw.ntruss.com/vision-obj/v1/detect"; // 객체 인식

		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setUseCaches(false);
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestMethod("POST");

		// multipart request
		String boundary = "---" + System.currentTimeMillis() + "---";
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", accessId);
		con.setRequestProperty("X-NCP-APIGW-API-KEY", secretKey);

		OutputStream os = con.getOutputStream();
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
		String LINE_FEED = "\r\n";

		// file 추가
		String fileName = uploadFile.getName();
		writer.append("--" + boundary).append(LINE_FEED)
				.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"")
				.append(LINE_FEED).append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName))
				.append(LINE_FEED).append(LINE_FEED);
		writer.flush();

		FileInputStream fis = new FileInputStream(uploadFile);
		byte[] buffer = new byte[4096];
		int bytesRead = -1; // 파일을 다 읽음
		while ((bytesRead = fis.read(buffer)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.flush();
		fis.close();
		writer.append(LINE_FEED).flush();
		writer.append("--" + boundary + "--").append(LINE_FEED);
		writer.close();

		BufferedReader br = null;
		int responseCode = con.getResponseCode();	// 결과 코드
		if (responseCode == 200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 오류 발생
			System.out.println("error!!!!!!! responseCode= " + responseCode);
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		}
		String inputLine;
		if (br != null) {
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
			model.addAttribute("jsonresult", response.toString());
			model.addAttribute("fileName", fileName);
		} else {
			System.out.println("error!");
		}
		return "detect/naverResult";
	}

}
