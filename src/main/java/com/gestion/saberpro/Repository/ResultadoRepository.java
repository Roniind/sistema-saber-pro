package com.gestion.saberpro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.gestion.saberpro.Entity.Resultado;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    
    // ✅ ESTE MÉTODO DEBE EXISTIR
    List<Resultado> findByTipoExamen(Resultado.TipoExamen tipoExamen);
    
    // ✅ Buscar resultados por estudiante
    List<Resultado> findByEstudianteId(Long estudianteId);
    
    // ✅ Buscar por identificación de estudiante
    @Query("SELECT r FROM Resultado r WHERE r.estudiante.identificacion = :identificacion")
    List<Resultado> findByIdentificacionEstudiante(@Param("identificacion") String identificacion);
    
    // ✅ Buscar por estudiante y tipo de examen
    @Query("SELECT r FROM Resultado r WHERE r.estudiante.id = :estudianteId AND r.tipoExamen = :tipoExamen")
    List<Resultado> findByEstudianteIdAndTipoExamen(@Param("estudianteId") Long estudianteId, 
                                                   @Param("tipoExamen") Resultado.TipoExamen tipoExamen);
    
    // ✅ Calcular promedio de un estudiante
    @Query("SELECT AVG(r.puntaje) FROM Resultado r WHERE r.estudiante.id = :estudianteId")
    Double findPromedioByEstudianteId(@Param("estudianteId") Long estudianteId);
}