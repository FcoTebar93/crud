package com.frtena.crud.controller;

import com.frtena.crud.entity.Company;
import com.frtena.crud.entity.Employee;
import com.frtena.crud.repository.CompanyRepository;
import com.frtena.crud.repository.EmployeeRepository;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/employee-list")
    public String showEmployeeList(Model model, HttpSession session) {
        // Obtener el email de la sesión actual
        String email = (String) session.getAttribute("email");
        // Obtener el objeto "Company" correspondiente al email de la sesión actual
        Company company = companyRepository.findByEmail(email);
        // Obtener la lista de empleados correspondientes a la empresa con el id obtenido
        List<Employee> employees = (List<Employee>) employeeRepository.findByCompany(company);
        // Agregar la lista de empleados obtenida al modelo
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @GetMapping("/add-employee")
    public String showAddForm(Employee employee) {
        return "add-employee";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-employee";
        }

        employeeRepository.save(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee-list";
    }

    @GetMapping("/edit-employee/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @PostMapping("/update-employee/{id}")
    public String updateEmployee(@PathVariable("id") long id, @Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "update-employee";
        }

        employeeRepository.save(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        employeeRepository.delete(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee-list";
    }
}