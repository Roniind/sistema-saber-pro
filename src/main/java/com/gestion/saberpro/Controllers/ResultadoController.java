package com.gestion.saberpro.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.gestion.saberpro.Entity.Resultado;
import com.gestion.saberpro.Entity.Estudiante;
import com.gestion.saberpro.Services.ResultadoService;
import com.gestion.saberpro.Services.EstudianteService;

@Controller
@RequestMapping("/resultados")
public class ResultadoController {
    
    private final ResultadoService resultadoService;
    private final EstudianteService estudianteService;
    
    public ResultadoController(ResultadoService resultadoService, EstudianteService estudianteService) {
        this.resultadoService = resultadoService;
        this.estudianteService = estudianteService;
    }
    
    @GetMapping
    public String listarResultados(Model model) {
        try {
            model.addAttribute("resultados", resultadoService.findAll());
            model.addAttribute("tiposExamen", getTiposExamen());
            return "resultados/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar resultados: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        try {
            model.addAttribute("resultado", new Resultado());
            model.addAttribute("estudiantes", estudianteService.findAll());
            model.addAttribute("tiposExamen", getTiposExamen());
            return "resultados/form";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar formulario: " + e.getMessage());
            return "error";
        }
    }
    
    @PostMapping("/guardar")
    public String guardarResultado(@ModelAttribute Resultado resultado, 
                                 @RequestParam("estudianteId") Long estudianteId,
                                 Model model) {
        try {
            Optional<Estudiante> estudiante = estudianteService.findById(estudianteId);
            if (estudiante.isPresent()) {
                resultado.setEstudiante(estudiante.get());
                resultadoService.save(resultado);
                return "redirect:/resultados";
            } else {
                model.addAttribute("error", "Estudiante no encontrado");
                model.addAttribute("estudiantes", estudianteService.findAll());
                model.addAttribute("tiposExamen", getTiposExamen());
                return "resultados/form";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar resultado: " + e.getMessage());
            model.addAttribute("estudiantes", estudianteService.findAll());
            model.addAttribute("tiposExamen", getTiposExamen());
            return "resultados/form";
        }
    }
    
    @GetMapping("/estudiante/{id}")
    public String resultadosPorEstudiante(@PathVariable("id") Long estudianteId, Model model) {
        try {
            Optional<Estudiante> estudiante = estudianteService.findById(estudianteId);
            if (estudiante.isPresent()) {
                List<Resultado> resultados = resultadoService.findByEstudianteId(estudianteId);
                model.addAttribute("estudiante", estudiante.get());
                model.addAttribute("resultados", resultados);
                return "resultados/estudiante";
            }
            return "redirect:/estudiantes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar resultados: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/tipo/{tipo}")
    public String resultadosPorTipo(@PathVariable("tipo") String tipo, Model model) {
        try {
            // Conversión segura de String a enum
            Resultado.TipoExamen tipoExamen = Resultado.TipoExamen.valueOf(tipo.toUpperCase());
            List<Resultado> resultados = resultadoService.findByTipoExamen(tipoExamen);
            model.addAttribute("resultados", resultados);
            model.addAttribute("tipoExamen", tipoExamen);
            model.addAttribute("tiposExamen", getTiposExamen());
            return "resultados/lista";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Tipo de examen no válido: " + tipo);
            return "redirect:/resultados";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar resultados: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarResultado(@PathVariable("id") Long id) {
        resultadoService.deleteById(id);
        return "redirect:/resultados";
    }
    
    // ✅ MÉTODO AUXILIAR
    private Resultado.TipoExamen[] getTiposExamen() {
        return Resultado.TipoExamen.values();
    }
}