package com.gestion.saberpro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import com.gestion.saberpro.Entity.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByIdentificacion(String identificacion);
    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);
    List<Estudiante> findByApellidoContainingIgnoreCase(String apellido);
    boolean existsByIdentificacion(String identificacion);
}