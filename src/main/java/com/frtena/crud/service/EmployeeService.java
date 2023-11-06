package com.frtena.crud.service;

import com.frtena.crud.entity.Company;
import com.frtena.crud.entity.Employee;
import com.frtena.crud.repository.CompanyRepository;
import com.frtena.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List<Employee> obtenerEmpleadosPorEmpresa(Long empresaId) {
        return employeeRepository.findByCompany(companyRepository.findById(empresaId).orElse(null));
    }

    public Employee crearEmpleado(Long empresaId, Employee empleado) {
        Company empresa = companyRepository.findById(empresaId).orElse(null);
        if (empresa != null) {
            empleado.setCompany(empresa);
            return employeeRepository.save(empleado);
        }
        return null; // Puedes manejar el caso en que la empresa no se encuentre
    }

    public Employee actualizarEmpleado(Long empleadoId, Employee empleadoActualizado) {
        Employee empleadoExistente = employeeRepository.findById(empleadoId).orElse(null);
        if (empleadoExistente != null) {
            empleadoExistente.setFirstName(empleadoActualizado.getFirstName());
            // Puedes manejar otras propiedades del empleado aquí si es necesario
            return employeeRepository.save(empleadoExistente);
        }
        return null; // Puedes manejar el caso en que el empleado no se encuentre
    }

    public void eliminarEmpleado(Long empleadoId) {
        Employee empleado = employeeRepository.findById(empleadoId).orElse(null);
        if (empleado != null) {
            employeeRepository.delete(empleado);
        }
        // Puedes manejar el caso en que el empleado no se encuentre
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee findByEmailAndCompany(String email, Company company) {
        return employeeRepository.findByEmailAndCompany(email, company);
    }

    public List<Employee> findByCompany(Company company) {
        return employeeRepository.findByCompany(company);
    }
}
