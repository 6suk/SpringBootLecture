package com.mulcam.demo.crawling;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumSample {

	// Driver
//	private static WebDriver dv;
	// Properties
	private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private static final String WEB_DRIVER_PATH = "D:/1.개인작업/Multicampus/4. DevTool/chromedriver_win32/chromedriver.exe";

	public static void main(String[] args) throws InterruptedException {
		// Driver Setup
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
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
		
		
		/*
		// 페이지 이동 -> 셀레니움 & 페이지 내에서 작업 -> Jsoup
		Document doc = Jsoup.parse(dv.getPageSource());
		Elements lis = doc.select(".stations-list > li");
		Element li = lis.get(0);
		String name = li.select(".title").text().strip();
		String addr = li.select("address").text().strip();
		String tel = li.select(".tel").text().strip();
		FireStation fs = new FireStation(name, addr, tel);
		System.out.println(fs);
		*/
		
		/*
		// 2페이지로 이동
		String page2 = "//*[@id=\"listForm\"]/div/section/ul/li[1]/div/div/ul/li[4]/a";
		dv.findElement(By.xpath(page2)).click();
		Thread.sleep(2000); // 2초지연
		
		Document doc = Jsoup.parse(dv.getPageSource());
		Elements lis = doc.select(".stations-list > li");
		Element li = lis.get(0);
		String name = li.select(".title").text().strip();
		String addr = li.select("address").text().strip();
		String tel = li.select(".tel").text().strip();
		
		FireStation fs = new FireStation(name, addr, tel);
		System.out.println(fs);
		
		dv.findElement(By.cssSelector(".next_page")).click();
		Thread.sleep(2000); // 2초지연
		*/
		
		
		List<FireStation> list = new ArrayList<>();
		for(int i = 1; i <= pages; i++) {
			if(i > 1 && i % 2 == 0) {
				dv.findElement(By.xpath("//*[@id=\"listForm\"]/div/section/ul/li[1]/div/div/ul/li[4]/a")).click();
				Thread.sleep(1000);
			} else if(i > 1 && i % 2 == 1) {
				dv.findElement(By.cssSelector(".next_page")).click();
				Thread.sleep(1000);
			}
			
			Document doc = Jsoup.parse(dv.getPageSource());
			Elements lis = doc.select(".stations-list > li");
			for(Element li : lis) {
				String name = li.select(".title").text().strip();
				String addr = li.select("address").text().strip();
				String tel = li.select(".tel").text().strip();
				FireStation fs = new FireStation(name, addr, tel);
				list.add(fs);
			}
		}
		System.out.println(list.size());
		dv.close();

	}

}
