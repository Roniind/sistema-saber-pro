package com.gestion.saberpro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.gestion.saberpro.Entity.Beneficio;

@Repository
public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {
    List<Beneficio> findByEstudianteId(Long estudianteId);
    
    List<Beneficio> findByTipoBeneficioContainingIgnoreCase(String tipoBeneficio);
    
    List<Beneficio> findByEstudianteIdentificacion(String identificacion);
}