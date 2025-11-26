package com.gestion.saberpro.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "beneficios")
public class Beneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;
    
    @Column(name = "tipo_beneficio", nullable = false, length = 100)
    private String tipoBeneficio;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDate fechaAsignacion;
    
    // Constructor
    public Beneficio() {}
    
    public Beneficio(Estudiante estudiante, String tipoBeneficio, String descripcion, LocalDate fechaAsignacion) {
        this.estudiante = estudiante;
        this.tipoBeneficio = tipoBeneficio;
        this.descripcion = descripcion;
        this.fechaAsignacion = fechaAsignacion;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    
    public String getTipoBeneficio() { return tipoBeneficio; }
    public void setTipoBeneficio(String tipoBeneficio) { this.tipoBeneficio = tipoBeneficio; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDate fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
}