package com.proyecto.joaquin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pruebas")
@Controller
public class PruebaController {

	@GetMapping("/")
	public String  prueba() {
		return "prueba.jsp";
	}
}
