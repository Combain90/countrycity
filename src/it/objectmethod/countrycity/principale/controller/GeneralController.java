package it.objectmethod.countrycity.principale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
	
	@GetMapping(value="/home")
	public String launchApplication() {
		return "IndexRest";
	}
	
	@GetMapping(value="*")
	public String defaultindex() {
		return "IndexRest";
	}
}
