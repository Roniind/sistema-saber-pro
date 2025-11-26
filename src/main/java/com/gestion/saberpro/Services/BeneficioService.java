package com.gestion.saberpro.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import com.gestion.saberpro.Entity.Beneficio;
import com.gestion.saberpro.Repository.BeneficioRepository;

@Service
@Transactional
public class BeneficioService {
    
    @Autowired
    private BeneficioRepository beneficioRepository;
    
    public List<Beneficio> findAll() {
        return beneficioRepository.findAll();
    }
    
    public Optional<Beneficio> findById(Long id) {
        return beneficioRepository.findById(id);
    }
    
    public Beneficio save(Beneficio beneficio) {
        return beneficioRepository.save(beneficio);
    }
    
    public void deleteById(Long id) {
        beneficioRepository.deleteById(id);
    }
    
    public List<Beneficio> findByEstudianteId(Long estudianteId) {
        return beneficioRepository.findByEstudianteId(estudianteId);
    }
    
    public List<Beneficio> findByTipoBeneficio(String tipoBeneficio) {
        return beneficioRepository.findByTipoBeneficioContainingIgnoreCase(tipoBeneficio);
    }
    
    public List<Beneficio> findByIdentificacionEstudiante(String identificacion) {
        return beneficioRepository.findByEstudianteIdentificacion(identificacion);
    }
}