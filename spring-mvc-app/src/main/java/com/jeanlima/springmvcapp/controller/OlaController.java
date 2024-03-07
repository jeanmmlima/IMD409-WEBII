package com.jeanlima.springmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OlaController {

    @GetMapping("/")
    public String showMenu(){
		return "menu";
	}

	@RequestMapping("/showForm")
	public String showForm(){
		return "ola-form";
	}

	@RequestMapping("/processaForm")
	public String processForm(HttpServletRequest request, Model model){

		

		String paramNome = request.getParameter("nome");

		/*
		 * Spring Framework em conjunto com o Thymeleaf, 
		 * geralmente você passa os atributos para o modelo usando o 
		 * objeto Model fornecido pelo Spring
		 */

		model.addAttribute("nome", paramNome);
		return "saudacao";
	}

	/* @RequestMapping("/processaFormOutraForma")
	public String processFormDois(@RequestParam("nome") String nome, Model model){

		nome = nome.toUpperCase();
		String result = "Olá! " + nome;
		model.addAttribute("msg", result);
		return "saudacao";
	} */
    
}
