package com.mulcam.demo.crawling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class Crawling {

	public List<Genie> genie() throws IOException {
		List<Genie> list = new ArrayList<>();
		LocalDateTime now = LocalDateTime.now();
		String ymd = now.toString().substring(0, 10).replace("-", "");
		String hh = now.toString().substring(11, 13);
		for (int i = 1; i <= 4; i++) {
			String url = "https://www.genie.co.kr/chart/top200?ditc=D&ymd=" + ymd + "&hh=" + hh + "&rtm=Y&pg=" + i;
			Document doc = Jsoup.connect(url).get();
			Elements trs = doc.select("tr.list");
			for (Element tr : trs) {
				String rank_ = tr.select(".number").text().split(" ")[0];
				int rank = Integer.parseInt(rank_);
				String img = "https:" + tr.select(".cover > img").attr("src");
				String title = tr.select(".title").text().strip();
				String artist = tr.select(".artist").text().strip();
				String albumtitle = tr.select(".albumtitle").text().strip();
				Genie genie = new Genie(rank, title, artist, albumtitle, img);
				list.add(genie);
			}
		}
		return list;
	}

	public List<Interpark> interpark() throws IOException {
		List<Interpark> list = new ArrayList<>();
		String url = "http://book.interpark.com/display/collectlist.do?_method=BestsellerHourNew201605&bestTp=1&dispNo=028#";
		Document doc = Jsoup.connect(url).get();
		Elements lis = doc.select(".rankBestContentList > ol > li");

		for (Element li : lis) {
			// 순위
			Elements spans = li.select(".rankNumber.digit2").select("span");
			StringBuilder sb = new StringBuilder();
			for (Element span : spans) {
				String classes = span.className().strip();
				sb.append(classes.substring(classes.length() - 1));
			}
			int rank = Integer.parseInt(sb.toString());
			// 이미지 주소
			String src = li.select(".coverImage img").attr("src");

			// 제목, 출판사, 글쓴이
			String title = li.select(".itemName").text().strip();
			String company = li.select(".company").text().strip();
			String author = li.select(".author").text().strip();

			String price_ = li.select(".price > em").text().strip();
			int price = Integer.parseInt(price_.replace(",", ""));
			Interpark book = new Interpark(rank, title, author, company, price, src);
			list.add(book);
		}

		return list;
	}
}
