package com.mulcam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mulcam.demo.entity.User;
import com.mulcam.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserControlloer {

	@Autowired
	private UserService service;

	@RequestMapping("/list")
	public String list(Model model) {
		List<User> list = service.getList();
		model.addAttribute("userList", list);
		return "user/list";
	}

	@RequestMapping("/detail/{uid}")
	public String detail(@PathVariable String uid, Model model) {
		User user = service.get(uid);
		model.addAttribute("user", user);
		return "redirect:/user/list";
	}

	@GetMapping("/register")
	public String registerForm() {
		return "user/register";
	}

	@PostMapping("/register")
	public String register(HttpServletRequest req) {
		String uid = req.getParameter("uid");
		String pwdBox[] = req.getParameterValues("pwd");
		String uname = req.getParameter("name");
		String email = req.getParameter("email");

		if (!pwdBox[0].equals(pwdBox[1])) {
			return "redirect:/user/list";
		} else {
			User u = new User(uid, pwdBox[0], uname, email);
			service.register(u);
			return "redirect:/user/list";
		}
	}
	
	@GetMapping("/update/{uid}")
	public String updateForm(@PathVariable String uid, Model model) {
		User user = service.get(uid);
		model.addAttribute("user", user);
		return "user/update";
	}
	
	
	@PostMapping("/update")
	public String update(HttpServletRequest req) {
		String uid = req.getParameter("uid");
		String pwdbox[] = req.getParameterValues("pwd");
		String uname = req.getParameter("name");
		String email = req.getParameter("email");
		User u;
		
		if (email.isEmpty())
			u = new User(uid, pwdbox[0], uname);
		else
			u = new User(uid, pwdbox[0], uname, email);
		
		if (!pwdbox[0].equals(pwdbox[1])) {
			System.out.println("비밀번호 오류");
			return "redirect:/user/list";
		} else {
			u = new User(uid, pwdbox[0], uname, email);
			service.update(u);
			System.out.println("정보 수정 완료");
			return "redirect:/user/list";
		}
	}
	
//	@GetMapping("/delete/{uid}")
//	public String delete(@PathVariable String uid) {
//		service.delete(uid);
//		return "redirect:/user/list";
//	}
	
	@GetMapping("/delete/{uid}")
	public String delete(@PathVariable String uid) {
		service.delete(uid);
		return "redirect:/user/list";
	}
	
}
