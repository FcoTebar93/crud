package com.frtena.crud.controller;

import com.frtena.crud.entity.Company;
import com.frtena.crud.repository.EmpresaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Company company = empresaRepository.findByEmailAndPassword(email, password);
        if (company != null) {
            session.setAttribute("empresa", company);
            return "redirect:/empresa";
        } else {
            session.setAttribute("error", "Correo electrónico o contraseña incorrectos");
            return "redirect:/login";
        }
    }
}