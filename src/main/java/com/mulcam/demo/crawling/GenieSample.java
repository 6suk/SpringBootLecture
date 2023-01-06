package com.mulcam.demo.crawling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GenieSample {

	public static void main(String[] args) throws IOException {

		/**
		 * 
		String url = "https://www.genie.co.kr/chart/top200?pg=1";
		Document doc = Jsoup.connect(url).get();
		Elements trs = doc.select("tr.list");
		Element tr = trs.get(0);
		String rank_ = tr.select(".number").text().split(" ")[0];
		int rank = Integer.parseInt(rank_);
		String img = "https:" + tr.select(".cover > img").attr("src");
		String title = tr.select(".title").text().strip();
		String artist = tr.select(".artist").text().strip();
		String albumtitle = tr.select(".albumtitle").text().strip();
		*
		*/
		List<Genie> list = new ArrayList<>();
		
		LocalDateTime now = LocalDateTime.now();
		String ymd = now.toString().substring(0, 10).replace("-", "");
		String hh = now.toString().substring(11, 13);
		for (int i = 1; i <= 4; i++) {
			String url = "https://www.genie.co.kr/chart/top200?ditc=D&ymd=" + ymd + "&hh=" + hh + "&rtm=Y&pg=" + i;
			Document doc = Jsoup.connect(url).get();
			Elements trs = doc.select("tr.list");
			for(Element tr : trs) {
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
		list.forEach(x -> System.out.println(x));

	}

}
