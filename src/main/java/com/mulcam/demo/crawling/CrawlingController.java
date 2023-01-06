package com.mulcam.demo.crawling;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cr")
public class CrawlingController {
	
	@Autowired
	private Crawling cr;
	
	@GetMapping("/fireStation")
	public String fireStationSpiner() {
		return "crawling/fireStationLoading";
	}
	
	@PostMapping("/fireStation")
	public String fireStation(Model model) throws InterruptedException {
		List<FireStation> list = cr.fireStation();
		model.addAttribute("list", list);
		return "crawling/fireStation";
	}
	
	
	@RequestMapping("/interpark")
	public String interpark(Model model) throws IOException {
		List<Interpark> list = cr.interpark();
		model.addAttribute("list", list);
		
		return "crawling/interpark";
	}
	
	@RequestMapping("/genie")
	public String genie(Model model) throws IOException {
		List<Genie> list = cr.genie();
		model.addAttribute("list", list);
		
		return "crawling/genie";
	}
	
	
}
