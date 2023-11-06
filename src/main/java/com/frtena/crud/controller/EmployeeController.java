package com.frtena.crud.controller;

import com.frtena.crud.entity.Company;
import com.frtena.crud.entity.Employee;
import com.frtena.crud.service.CompanyService;
import com.frtena.crud.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/employee-list")
    public String showEmployeeList(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        Company company = companyService.findByEmail(email);

        if (company != null) {
            List<Employee> employees = company.getEmployees();
            model.addAttribute("employees", employees);
        } else {
            model.addAttribute("employees", new ArrayList<Employee>());
        }
        return "employee-list";
    }

    @GetMapping("/add-employee")
    public String showAddForm(Employee employee) {
        return "add-employee";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@Valid Employee employee, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "add-employee";
        }

        Employee existingEmployee = employeeService.findByEmailAndCompany(employee.getEmail(), companyService.findByEmail((String) session.getAttribute("email")));

        if (existingEmployee != null) {
            model.addAttribute("error", "Ya existe un empleado con el mismo email");
            return "add-employee";
        } else {
            Company company = companyService.findByEmail((String) session.getAttribute("email"));
            employee.setCompany(company);
            employeeService.saveEmployee(employee);
            model.addAttribute("employees", employeeService.findByCompany(company));
            return "employee-list";
        }
    }

    @GetMapping("/update-employee/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @PostMapping("/update-employee/{id}")
    public String updateEmployee(@PathVariable("id") long id, @Valid Employee employee, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "update-employee";
        }

        employeeService.saveEmployee(employee);
        model.addAttribute("employees", employeeService.findByCompany(companyService.findByEmail((String) session.getAttribute("email"))));
        return "employee-list";
    }

    @GetMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable("id") long id, Model model, HttpSession session) {
        Employee employee = employeeService.findEmployeeById(id);
        employeeService.deleteEmployee(employee);
        model.addAttribute("employees", employeeService.findByCompany(companyService.findByEmail((String) session.getAttribute("email"))));
        return "employee-list";
    }

}
