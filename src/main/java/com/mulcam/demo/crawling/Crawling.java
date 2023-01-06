package com.mulcam.demo.crawling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Crawling {
	
	@Value("${web.driver.id}")
	private String webDriverId;
	@Value("${web.driver.path}")
	private String webDriverPath;

	public List<FireStation> fireStation() throws InterruptedException {
		// Driver Setup
		System.setProperty(webDriverId, webDriverPath);
		
		//창 띄우지 않기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		WebDriver dv = new ChromeDriver(options);
		
		String url = "https://www.nfa.go.kr/nfa/introduce/status/firestationidfo";
		dv.get(url);
		Thread.sleep(3000); // 3초지연

		/** 지역 선택 */
		WebElement inputBox = dv.findElement(By.cssSelector("#searchKeyword"));
		inputBox.sendKeys("서울");
		WebElement searchBtn = dv.findElement(By.cssSelector("#fsSearchBtn"));
		searchBtn.click();

		Thread.sleep(2000); // 1초지연

		/** 총 페이지수 구하기 */
		// *[@id="listForm"]/div/section/div/p/strong
		String xPath = "//*[@id=\"listForm\"]/div/section/div/p/strong[2]";
		String num_ = dv.findElement(By.xpath(xPath)).getText().strip();
		int num = Integer.parseInt(num_.substring(0, num_.length() - 1));
		int pages = (int) Math.ceil(num / 10);

		List<FireStation> list = new ArrayList<>();
		for (int i = 1; i <= pages; i++) {
			if (i > 1 && i % 2 == 0) {
				dv.findElement(By.xpath("//*[@id=\"listForm\"]/div/section/ul/li[1]/div/div/ul/li[4]/a")).click();
				Thread.sleep(1000);
			} else if (i > 1 && i % 2 == 1) {
				dv.findElement(By.cssSelector(".next_page")).click();
				Thread.sleep(1000);
			}

			Document doc = Jsoup.parse(dv.getPageSource());
			Elements lis = doc.select(".stations-list > li");
			for (Element li : lis) {
				String name = li.select(".title").text().strip();
				String addr = li.select("address").text().strip();
				String tel = li.select(".tel").text().strip();
				FireStation fs = new FireStation(name, addr, tel);
				list.add(fs);
			}
		}
		dv.quit();
		return list;
	}

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
