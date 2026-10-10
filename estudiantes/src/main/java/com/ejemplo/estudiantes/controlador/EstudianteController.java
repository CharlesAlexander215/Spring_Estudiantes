package com.ejemplo.estudiantes.controlador;

import com.ejemplo.estudiantes.modelo.Estudiante;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstudianteController {

    private List<Estudiante> estudiantes = new ArrayList<>();

    // Constructor
    public EstudianteController() {

        estudiantes.add(
            new Estudiante(1, "Juan", "Sistemas", 9.2)
        );

        estudiantes.add(
            new Estudiante(2, "Ana", "Computación", 8.7)
        );  
    }

    @GetMapping("/")
    public String inicio() {
        return "CONTROLADOR FUNCIONANDO";
    }
    
    @GetMapping("/estudiantes/{id}")
    public Estudiante obtenerEstudiante(@PathVariable int id) {
        for( Estudiante estudiante: estudiantes){
            if(estudiante.getId() == id)
                return estudiante;
        }
        return null;
    }

    @GetMapping("/estudiantes")
    public List<Estudiante> obtenerEstudiantes() {
        return estudiantes;
    }
    
    @PostMapping("/estudiantes")
    public Estudiante crearEstudiante(@RequestBody Estudiante estudiante) {
    estudiantes.add(estudiante);
    return estudiante;
    }
    
    @DeleteMapping("/estudiantes/{id}") // O @PostMapping("/estudiantes/remover/{id}")
    public String removerEstudiantePorId(@PathVariable int id) {
        // Buscamos y removemos usando una expresión en Java 8+
        boolean eliminado = estudiantes.removeIf(estudiante -> estudiante.getId() == id);
        
        if (eliminado) {
            return "Estudiante con ID " + id + " eliminado correctamente.";
        } else {
            return "No se encontró ningún estudiante con el ID " + id;
        }
    }
    
    @PutMapping("/estudiantes/{id}")
    public Estudiante actualizarEstudiante(@PathVariable int id, @RequestBody Estudiante estudianteActualizado) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                estudiante.setNombre(estudianteActualizado.getNombre());
                estudiante.setCarrera(estudianteActualizado.getCarrera());
                estudiante.setPromedio(estudianteActualizado.getPromedio());
                return estudiante; 
            }
        }
        return null; 
    }
    
     
}
