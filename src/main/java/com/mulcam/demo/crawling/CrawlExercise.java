package com.mulcam.demo.crawling;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlExercise {
	public static void main(String[] args) throws Exception {
		String url = "http://book.interpark.com/display/collectlist.do?_method=BestsellerHourNew201605&bestTp=1&dispNo=028#";
		Document doc = Jsoup.connect(url).get();
		Elements lis = doc.select(".rankBestContentList > ol > li");
		
		/*
		 * 
		Element li = lis.get(8);
		
		String title = li.select(".itemName").text().strip();
		String company = li.select(".company").text().strip();
		String author = li.select(".author").text().strip();
		
//		Element imgs = li.selectFirst(".coverImage").selectFirst("img");
//		String src = imgs.attr("src");
		String src = li.select(".coverImage img").attr("src");
		String price_ = li.select(".price > em").text().strip();
		int price = Integer.parseInt(price_.replace(",",""));
		
		//순위
		Elements spans = li.select(".rankNumber.digit2").select("span");
		StringBuilder sb = new StringBuilder();
		for(Element span : spans) {
//			String classes = span.attr("class");
			String classes = span.className().strip();
			sb.append(classes.substring(classes.length()-1));
		}
		int rank = Integer.parseInt(sb.toString());
		
		// 데이터 정리
		Interpark book = new Interpark(rank, src, title, author, company, price);
		System.out.println(book.toString());
		*
		*/
		
		List<Interpark> list = new ArrayList<>();
		
		for(Element li : lis) {
			// 순위
			Elements spans = li.select(".rankNumber.digit2").select("span");
			StringBuilder sb = new StringBuilder();
			for(Element span : spans) {
				String classes = span.className().strip();
				sb.append(classes.substring(classes.length()-1));
			}
			int rank = Integer.parseInt(sb.toString());
			// 이미지 주소
			String src = li.select(".coverImage img").attr("src");
			
			// 제목, 출판사, 글쓴이
			String title = li.select(".itemName").text().strip();
			String company = li.select(".company").text().strip();
			String author = li.select(".author").text().strip();
			
			String price_ = li.select(".price > em").text().strip();
			int price = Integer.parseInt(price_.replace(",",""));
			Interpark book = new Interpark(rank, title, author, company, price, src);
			list.add(book);
		}
		list.forEach(x -> System.out.println(x));
		
		
	}
}
