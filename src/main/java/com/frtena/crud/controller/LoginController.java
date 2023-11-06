package com.frtena.crud.controller;

import com.frtena.crud.entity.Company;
import com.frtena.crud.repository.CompanyRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/index")
    public String showLoginForm() {
        return "index";
    }

    @PostMapping("/index")
    public String processLoginForm(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Company company = companyRepository.findByEmailAndPassword(email, password);
        if (company != null) {
            session.setAttribute("email", email);
            return "redirect:/employee-list";
        } else {
            session.setAttribute("error", "Correo electrónico o contraseña incorrectos");
            return "redirect:/index";
        }
    }
}