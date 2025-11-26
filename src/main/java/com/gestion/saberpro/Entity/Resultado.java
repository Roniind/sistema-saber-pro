package com.gestion.saberpro.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resultados")
public class Resultado {
    
    // ✅ ENUM DEFINIDO DENTRO DE LA CLASE
    public enum TipoExamen {
        SABER_PRO,
        SABER_LVI,
        IGESTY
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_examen", nullable = false)
    private TipoExamen tipoExamen;
    
    @Column(name = "modulo", nullable = false, length = 50)
    private String modulo;
    
    @Column(name = "puntaje", nullable = false)
    private Double puntaje;
    
    @Column(name = "fecha_examen", nullable = false)
    private LocalDate fechaExamen;
    
    // Constructores
    public Resultado() {}
    
    public Resultado(Estudiante estudiante, TipoExamen tipoExamen, String modulo, Double puntaje, LocalDate fechaExamen) {
        this.estudiante = estudiante;
        this.tipoExamen = tipoExamen;
        this.modulo = modulo;
        this.puntaje = puntaje;
        this.fechaExamen = fechaExamen;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    
    public TipoExamen getTipoExamen() { return tipoExamen; }
    public void setTipoExamen(TipoExamen tipoExamen) { this.tipoExamen = tipoExamen; }
    
    public String getModulo() { return modulo; }
    public void setModulo(String modulo) { this.modulo = modulo; }
    
    public Double getPuntaje() { return puntaje; }
    public void setPuntaje(Double puntaje) { this.puntaje = puntaje; }
    
    public LocalDate getFechaExamen() { return fechaExamen; }
    public void setFechaExamen(LocalDate fechaExamen) { this.fechaExamen = fechaExamen; }
}