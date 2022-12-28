package com.mulcam.demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mulcam.demo.entity.User;
import com.mulcam.demo.service.UserService;
import com.mulcam.demo.session.UserSession;

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

//	@PostMapping("/update")
//	public String update(HttpServletRequest req) {
//		String uid = req.getParameter("uid");
//		String pwdbox[] = req.getParameterValues("pwd");
//		String uname = req.getParameter("name");
//		String email = req.getParameter("email");
//		User u;
//
//		if (email.isEmpty())
//			u = new User(uid, pwdbox[0], uname);
//		else
//			u = new User(uid, pwdbox[0], uname, email);
//
//		if (!pwdbox[0].equals(pwdbox[1])) {
//			System.out.println("비밀번호 오류");
//			return "redirect:/user/list";
//		} else {
//			u = new User(uid, pwdbox[0], uname, email);
//			service.update(u);
//			System.out.println("정보 수정 완료");
//			return "redirect:/user/list";
//		}
//	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute User u) {
		int check = service.update(u);
		
		switch (check) {
		case UserService.CORRECT:
			System.out.println("정보 수정 완료");
			return "redirect:/user/list";
			
		case UserService.WRONG_PWD:
			System.out.println("비밀번호 오류");
			return "redirect:/user/list";

		default:
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

	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}

	@PostMapping("/login")
	public String login(HttpServletRequest req, Model model) {
		String uid = req.getParameter("uid");
		String pwd = req.getParameter("pwd");
		int result = service.login(uid, pwd);

		switch (result) {
		case UserService.CORRECT:
			User u = service.get(uid);
			HttpSession ss = req.getSession();
			ss.setAttribute("uid", u.getUid());
			ss.setAttribute("uname", u.getUname());
			return "redirect:/user/list";

		case UserService.WRONG_PWD:
			return "/user/login";

		case UserService.NULL_UID:
			return "/user/login";

		default:
			return "";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession ss =  req.getSession();
		ss.invalidate();
		return "redirect:/user/list";
	}

}
