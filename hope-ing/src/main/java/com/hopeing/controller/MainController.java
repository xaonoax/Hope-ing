package com.hopeing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/hope-ing")
@Slf4j
public class MainController {
	
	@GetMapping("")
	public String Main() {
				
		return "hope-ing";
	}
	
}
