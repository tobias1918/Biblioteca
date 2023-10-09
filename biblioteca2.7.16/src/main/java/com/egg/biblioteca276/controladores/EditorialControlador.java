/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca276.controladores;

import com.egg.biblioteca276.excepciones.MiException;
import com.egg.biblioteca276.servicios.EditorialServicio;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tobia
 */
@Controller
@RequestMapping("/editorial")
public class EditorialControlador { //localhost:8080/editorial
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/registrar")  //localhost:8080/editorial/registrar
    public String registrar(){
        return "editorial_form.html";
    } 
    
    @PostMapping("/registro") 
    public String registro(@RequestParam String nombre,ModelMap modelo){
        
        try {
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito", "La editorial fue registrado correctamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "editorial_form.html";
        }
        return "index.html";
    }
    
    
}
