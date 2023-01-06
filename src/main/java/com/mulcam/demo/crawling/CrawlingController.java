package com.mulcam.demo.crawling;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cr")
public class CrawlingController {
	
	@Autowired
	private Crawling cr;
	
	@RequestMapping("/interpark")
	public String interpark(Model model) throws IOException {
		List<Interpark> list = cr.interpark();
		model.addAttribute("list", list);
		
		return "crawling/interpark";
	}
	
	
}
