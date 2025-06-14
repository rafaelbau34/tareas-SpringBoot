package com.demo.controller;

import com.demo.model.Tarea;
import com.demo.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tareas", service.findAll());
        return "tareas";
    }

    @GetMapping("/form")
    public String formulario(@RequestParam(required = false) Long id, Model model) {
        Tarea tarea = (id != null) ? service.findById(id) : new Tarea();
        model.addAttribute("tarea", tarea);
        return "tarea-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("tarea") Tarea tarea) {
        service.save(tarea);
        return "redirect:/tareas";
    }

    @PostMapping("/{id}/toggle-status")
    public String toggleStatus(@PathVariable Long id) {
       
        Tarea tarea = service.findById(id);
        if (tarea == null) {
            throw new IllegalArgumentException("Invalid tarea ID");
        }

        switch (tarea.getEstado()) {
            case "Pendiente":
              tarea.setEstado("En progreso");
            break;
            case "En progreso":
            tarea.setEstado("Terminado");
             break;
            case "Terminado":
              tarea.setEstado("Pendiente");
                break;
            default:
                tarea.setEstado("Pendiente");
        }

        service.save(tarea);
            return "redirect:/tareas";
    }



    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/tareas";
    }
}
