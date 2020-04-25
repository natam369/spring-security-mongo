package com.mindtree.mongosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/")
	public String homepage() {
		System.out.println("in /");
		return "home";
	}

	@RequestMapping("/login")
	public String loginpage() {
		System.out.println("in /login");

		return "login";
	}

	@RequestMapping("/logout-success")
	public String logoutpage() {
		System.out.println("in /logout");
		return "logout";
	}

}
