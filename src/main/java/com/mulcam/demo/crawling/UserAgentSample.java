package com.mulcam.demo.crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UserAgentSample {

	public static void main(String[] args) throws IOException {
		String url = "https://www.melon.com/chart/";
		Document doc = Jsoup.connect(url).get();
		System.out.println(doc);
		
		// 사이트에서 크롤링을 허용하지 않는 경우
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Whale/3.18.154.7 Safari/537.36";
		doc = Jsoup.connect(url).userAgent(userAgent).get();

	}

}
