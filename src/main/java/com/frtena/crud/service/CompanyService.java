package com.frtena.crud.service;

import com.frtena.crud.entity.Company;
import com.frtena.crud.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

        @Autowired
        private CompanyRepository companyRepository;

        // Obtener una lista de todas las empresas
        public List<Company> obtenerTodasLasEmpresas() {
            return companyRepository.findAll();
        }

        // Crear una nueva empresa
        public Company crearEmpresa(Company empresa) {
            return companyRepository.save(empresa);
        }

        // Actualizar una empresa existente
        public Company actualizarEmpresa(Long empresaId, Company empresaActualizada) throws Exception {
            Company empresaExistente = companyRepository.findById(empresaId)
                    .orElseThrow(() -> new Exception("Empresa no encontrada"));

            empresaExistente.setNombre(empresaActualizada.getNombre());
            // Puedes manejar la actualización de empleados aquí si es necesario

            return companyRepository.save(empresaExistente);
        }

        // Eliminar una empresa
        public void eliminarEmpresa(Long empresaId) throws Exception {
            Company empresa = companyRepository.findById(empresaId)
                    .orElseThrow(() -> new Exception("Empresa no encontrada"));

            companyRepository.delete(empresa);
        }

        // Guardar una empresa
        public Company saveCompany(Company company) {
            return companyRepository.save(company);
        }

        // Buscar una empresa por email
        public Company findByEmail(String email) {
            return companyRepository.findByEmail(email);
        }
}
