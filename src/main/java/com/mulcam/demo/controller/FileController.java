package com.mulcam.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.demo.entity.FileEntity;

@Component
@RequestMapping("/file")
public class FileController {
	@GetMapping("/upload")
	public String uploadForm() {
		return "file/upload";
	}
	
	@ResponseBody
	@PostMapping("/upload")
	public String upload(@RequestParam MultipartFile[] files, Model model) {
		List<FileEntity> list = new ArrayList<>();
		
		for(MultipartFile f : files) {
			FileEntity fe = new FileEntity();
			fe.setFileName(f.getOriginalFilename());
			fe.setContentType(f.getContentType());
			list.add(fe);
			
			/** 물리적 저장 */
			File fileName = new File(f.getOriginalFilename());
			try {
				f.transferTo(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		StringBuilder sb = new StringBuilder();
		list.forEach(x -> sb.append(x.toString()).append("<br>"));
		return sb.toString();
		
//		model.addAttribute("uploadFiles",list);
//		return "file/upload";
	}
}
