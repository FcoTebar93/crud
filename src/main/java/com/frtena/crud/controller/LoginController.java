package com.frtena.crud.controller;

import com.frtena.crud.entity.Company;
import com.frtena.crud.service.CompanyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/index")
    public String showLoginForm(HttpSession session) {
        session.removeAttribute("error");
        return "index";
    }

    @PostMapping("/index")
    public String processLoginForm(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Company company = companyService.findByEmailAndPassword(email, password);

        if (company != null && email.equals(company.getEmail()) && password.equals(company.getPassword())) {
            session.setAttribute("email", email);
            return "redirect:/employee-list";
        } else {
            model.addAttribute("error", "Email o contraseña incorrectos");
            return "redirect:/error"; // Agregar un parámetro de error
        }
    }
}