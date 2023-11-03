package com.frtena.crud.repository;

import com.frtena.crud.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByEmailAndPassword(String email, String password);
    Company findByEmail(String email);
}
