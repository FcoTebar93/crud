package com.frtena.crud.controller;

import com.frtena.crud.entity.Employee;
import com.frtena.crud.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String showEmployeeList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Employee employee) {
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-employee";
        }

        employeeRepository.save(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, @Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "update-employee";
        }

        employeeRepository.save(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        employeeRepository.delete(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "index";
    }
}