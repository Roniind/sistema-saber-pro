package com.gestion.saberpro.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import com.gestion.saberpro.Entity.Resultado;
import com.gestion.saberpro.Entity.Estudiante;
import com.gestion.saberpro.Repository.ResultadoRepository;
import com.gestion.saberpro.Repository.EstudianteRepository;

@Service
@Transactional
public class ResultadoService {
    
    @Autowired
    private ResultadoRepository resultadoRepository;
    
    @Autowired
    private EstudianteRepository estudianteRepository;
    
    // ✅ MÉTODO QUE FALTABA - CORREGIDO
    public List<Resultado> findByTipoExamen(Resultado.TipoExamen tipoExamen) {
        return resultadoRepository.findByTipoExamen(tipoExamen);
    }
    
    // ✅ MÉTODOS BÁSICOS CRUD
    public List<Resultado> findAll() {
        return resultadoRepository.findAll();
    }
    
    public Optional<Resultado> findById(Long id) {
        return resultadoRepository.findById(id);
    }
    
    public Resultado save(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }
    
    public void deleteById(Long id) {
        resultadoRepository.deleteById(id);
    }
    
    // ✅ MÉTODOS DE BÚSQUEDA
    public List<Resultado> findByEstudianteId(Long estudianteId) {
        return resultadoRepository.findByEstudianteId(estudianteId);
    }
    
    public List<Resultado> findByIdentificacionEstudiante(String identificacion) {
        return resultadoRepository.findByIdentificacionEstudiante(identificacion);
    }
    
    // ✅ MÉTODOS ADICIONALES
    public Double obtenerPromedioEstudiante(Long estudianteId) {
        Double promedio = resultadoRepository.findPromedioByEstudianteId(estudianteId);
        return promedio != null ? promedio : 0.0;
    }
    
    public Resultado guardarResultadoConEstudiante(Resultado resultado, String identificacionEstudiante) {
        Optional<Estudiante> estudiante = estudianteRepository.findByIdentificacion(identificacionEstudiante);
        if (estudiante.isPresent()) {
            resultado.setEstudiante(estudiante.get());
            return resultadoRepository.save(resultado);
        }
        throw new RuntimeException("Estudiante no encontrado con identificación: " + identificacionEstudiante);
    }
    
    public List<Resultado> findByEstudianteIdAndTipoExamen(Long estudianteId, Resultado.TipoExamen tipoExamen) {
        return resultadoRepository.findByEstudianteIdAndTipoExamen(estudianteId, tipoExamen);
    }
}