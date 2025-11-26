package com.gestion.saberpro.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.gestion.saberpro.Entity.Estudiante;
import com.gestion.saberpro.Entity.Resultado;
import com.gestion.saberpro.Entity.Beneficio;
import com.gestion.saberpro.Services.EstudianteService;
import com.gestion.saberpro.Services.ResultadoService;
import com.gestion.saberpro.Services.BeneficioService;

@Controller
@RequestMapping("/informes")
public class InformeController {
    
    private final EstudianteService estudianteService;
    private final ResultadoService resultadoService;
    private final BeneficioService beneficioService;
    
    public InformeController(EstudianteService estudianteService, 
                           ResultadoService resultadoService,
                           BeneficioService beneficioService) {
        this.estudianteService = estudianteService;
        this.resultadoService = resultadoService;
        this.beneficioService = beneficioService;
    }
    
    @GetMapping
    public String mostrarInformes(Model model) {
        try {
            long totalEstudiantes = estudianteService.findAll().size();
            long totalResultados = resultadoService.findAll().size();
            long totalBeneficios = beneficioService.findAll().size();
            
            model.addAttribute("totalEstudiantes", totalEstudiantes);
            model.addAttribute("totalResultados", totalResultados);
            model.addAttribute("totalBeneficios", totalBeneficios);
            
            return "informes/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar informes: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/detallado/{id}")
    public String informeDetallado(@PathVariable("id") Long estudianteId, Model model) {
        try {
            Optional<Estudiante> estudiante = estudianteService.findById(estudianteId);
            if (estudiante.isPresent()) {
                List<Resultado> resultados = resultadoService.findByEstudianteId(estudianteId);
                List<Beneficio> beneficios = beneficioService.findByEstudianteId(estudianteId);
                Double promedio = resultadoService.obtenerPromedioEstudiante(estudianteId);
                
                model.addAttribute("estudiante", estudiante.get());
                model.addAttribute("resultados", resultados);
                model.addAttribute("beneficios", beneficios);
                model.addAttribute("promedio", promedio != null ? String.format("%.2f", promedio) : "N/A");
                
                return "informes/detallado";
            }
            return "redirect:/estudiantes";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar informe: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/beneficios")
    public String informeBeneficios(Model model) {
        try {
            List<Beneficio> beneficios = beneficioService.findAll();
            Map<String, Long> conteoPorTipo = new HashMap<>();
            
            for (Beneficio beneficio : beneficios) {
                String tipo = beneficio.getTipoBeneficio();
                conteoPorTipo.put(tipo, conteoPorTipo.getOrDefault(tipo, 0L) + 1);
            }
            
            model.addAttribute("beneficios", beneficios);
            model.addAttribute("conteoPorTipo", conteoPorTipo);
            
            return "informes/beneficios";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar informe de beneficios: " + e.getMessage());
            return "error";
        }
    }
}