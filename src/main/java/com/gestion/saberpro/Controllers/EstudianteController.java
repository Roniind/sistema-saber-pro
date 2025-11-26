package com.gestion.saberpro.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import com.gestion.saberpro.Entity.Estudiante;
import com.gestion.saberpro.Services.EstudianteService;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {
    
    private final EstudianteService estudianteService;
    
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }
    
    @GetMapping
    public String listarEstudiantes(Model model) {
        try {
            model.addAttribute("estudiantes", estudianteService.findAll());
            return "estudiantes/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar estudiantes: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/form";
    }
    
    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante, Model model) {
        try {
            estudianteService.save(estudiante);
            return "redirect:/estudiantes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar estudiante: " + e.getMessage());
            model.addAttribute("estudiante", estudiante);
            return "estudiantes/form";
        }
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        try {
            Optional<Estudiante> estudiante = estudianteService.findById(id);
            if (estudiante.isPresent()) {
                model.addAttribute("estudiante", estudiante.get());
                return "estudiantes/form";
            }
            return "redirect:/estudiantes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar estudiante: " + e.getMessage());
            return "redirect:/estudiantes";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable("id") Long id, Model model) {
        try {
            estudianteService.deleteById(id);
            return "redirect:/estudiantes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al eliminar estudiante: " + e.getMessage());
            return "redirect:/estudiantes";
        }
    }
    
    @GetMapping("/buscar")
    public String buscarEstudiantes(@RequestParam("nombre") String nombre, Model model) {
        try {
            model.addAttribute("estudiantes", estudianteService.findByNombre(nombre));
            return "estudiantes/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error en la búsqueda: " + e.getMessage());
            return "estudiantes/lista";
        }
    }
}