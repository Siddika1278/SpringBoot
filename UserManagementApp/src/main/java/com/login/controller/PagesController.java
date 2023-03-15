package com.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;




@Controller

public class PagesController {

	@GetMapping("/page")
	public String page(Model model) {
		model.addAttribute("msg", "Sid");
		return "pages/page";
	}
	@GetMapping("/page1")
	public String page1(Model model) {
		model.addAttribute("msg", "Vedu");
		return "pages/page1";
	}
	
	@GetMapping("/page2")
    public String page2( Model model,HttpSession session) {
        model.addAttribute("msg",session.getId());
        model.addAttribute("msg1",session.getLastAccessedTime());
        return "pages/page2";
    }
}
