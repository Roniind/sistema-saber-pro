package com.gestion.saberpro.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import com.gestion.saberpro.Entity.Estudiante;
import com.gestion.saberpro.Repository.EstudianteRepository;

@Service
@Transactional
public class EstudianteService {
    
    @Autowired
    private EstudianteRepository estudianteRepository;
    
    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }
    
    public Optional<Estudiante> findById(Long id) {
        return estudianteRepository.findById(id);
    }
    
    public Estudiante save(Estudiante estudiante) {
        // Validar que no exista otro estudiante con la misma identificación
        if (estudiante.getId() == null && estudianteRepository.existsByIdentificacion(estudiante.getIdentificacion())) {
            throw new RuntimeException("Ya existe un estudiante con la identificación: " + estudiante.getIdentificacion());
        }
        return estudianteRepository.save(estudiante);
    }
    
    public void deleteById(Long id) {
        estudianteRepository.deleteById(id);
    }
    
    public Optional<Estudiante> findByIdentificacion(String identificacion) {
        return estudianteRepository.findByIdentificacion(identificacion);
    }
    
    public List<Estudiante> findByNombre(String nombre) {
        return estudianteRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public List<Estudiante> findByApellido(String apellido) {
        return estudianteRepository.findByApellidoContainingIgnoreCase(apellido);
    }
    
    public boolean existsByIdentificacion(String identificacion) {
        return estudianteRepository.existsByIdentificacion(identificacion);
    }
}