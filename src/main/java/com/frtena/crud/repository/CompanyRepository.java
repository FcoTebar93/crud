package com.frtena.crud.repository;

import com.frtena.crud.entity.Company;
import com.frtena.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAll();
    Company findById(long id);
    Company findByEmailAndPassword(String email, String password);
    Company findByEmail(String email);
}
