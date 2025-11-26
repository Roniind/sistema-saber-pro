package com.gestion.saberpro.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.gestion.saberpro.Entity.Beneficio;
import com.gestion.saberpro.Entity.Estudiante;
import com.gestion.saberpro.Services.BeneficioService;
import com.gestion.saberpro.Services.EstudianteService;

@Controller
@RequestMapping("/beneficios")
public class BeneficioController {
    
    private final BeneficioService beneficioService;
    private final EstudianteService estudianteService;
    
    public BeneficioController(BeneficioService beneficioService, EstudianteService estudianteService) {
        this.beneficioService = beneficioService;
        this.estudianteService = estudianteService;
    }
    
    @GetMapping
    public String listarBeneficios(Model model) {
        try {
            model.addAttribute("beneficios", beneficioService.findAll());
            return "beneficios/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar beneficios: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        try {
            model.addAttribute("beneficio", new Beneficio());
            model.addAttribute("estudiantes", estudianteService.findAll());
            return "beneficios/form";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar formulario: " + e.getMessage());
            return "error";
        }
    }
    
    @PostMapping("/guardar")
    public String guardarBeneficio(@ModelAttribute Beneficio beneficio,
                                 @RequestParam("estudianteId") Long estudianteId,
                                 Model model) {
        try {
            Optional<Estudiante> estudiante = estudianteService.findById(estudianteId);
            if (estudiante.isPresent()) {
                beneficio.setEstudiante(estudiante.get());
                beneficio.setFechaAsignacion(LocalDate.now());
                beneficioService.save(beneficio);
                return "redirect:/beneficios";
            } else {
                model.addAttribute("error", "Estudiante no encontrado");
                model.addAttribute("estudiantes", estudianteService.findAll());
                return "beneficios/form";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar beneficio: " + e.getMessage());
            model.addAttribute("estudiantes", estudianteService.findAll());
            return "beneficios/form";
        }
    }
    
    @GetMapping("/estudiante/{id}")
    public String beneficiosPorEstudiante(@PathVariable("id") Long estudianteId, Model model) {
        try {
            Optional<Estudiante> estudiante = estudianteService.findById(estudianteId);
            if (estudiante.isPresent()) {
                List<Beneficio> beneficios = beneficioService.findByEstudianteId(estudianteId);
                model.addAttribute("estudiante", estudiante.get());
                model.addAttribute("beneficios", beneficios);
                return "beneficios/estudiante";
            }
            return "redirect:/estudiantes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar beneficios: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarBeneficio(@PathVariable("id") Long id) {
        beneficioService.deleteById(id);
        return "redirect:/beneficios";
    }
}