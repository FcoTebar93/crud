package com.frtena.crud.repository;

import com.frtena.crud.entity.Company;
import com.frtena.crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAll();
    Company findById(long id);
    @Query("SELECT c FROM Company c WHERE c.email = :email AND c.password = :password")
    Company findByEmailAndPassword(@Param("email")String email, @Param("password")String password);
    Company findByEmail(String email);
}
