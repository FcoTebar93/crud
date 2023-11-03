package com.frtena.crud.controller;

import com.frtena.crud.entity.Company;
import com.frtena.crud.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private CompanyRepository empresaRepository;

    @GetMapping("/register")
    public String showRegistroEmpresaForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegistroEmpresaForm(@RequestParam String nombre, @RequestParam String email, @RequestParam String password) {
        Company empresa = new Company();
        empresa.setNombre(nombre);
        empresa.setEmail(email);
        empresa.setPassword(password);
        empresaRepository.save(empresa);
        return "redirect:/index";
    }
}