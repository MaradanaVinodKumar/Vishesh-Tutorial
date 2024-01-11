package com.vishesh.student.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class WebController {
 @GetMapping("/")
 public String home(Model model) {
	 return "index";
 }
 
 @GetMapping("/paymentSuccess.html")
 public String success() {
	 return "paymentSuccess";
 }
}
