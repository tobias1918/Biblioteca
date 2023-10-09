/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca276.controladores;

import com.egg.biblioteca276.entidades.Autor;
import com.egg.biblioteca276.excepciones.MiException;
import com.egg.biblioteca276.servicios.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/autor")
public class AutorControlador { //localhost:8080/autor
    
    @Autowired
    private AutorServicio autorServicio;
    
    @GetMapping("/registrar") //localhost:8080/autor/registrar
    public String registrar(){
        return "autor_form.html";
    }
    
    @PostMapping("/registro") 
    public String registro(@RequestParam String nombre,ModelMap modelo){
        
        try {
           
            autorServicio.crearAutor(nombre);         
            modelo.put("exito", "El Autor Fue registrado correctamente");
        } catch (MiException ex) {
            
            modelo.put("error", ex.getMessage());
            
            return "autor_form.html";
        }
        return "index.html";
    }
    
     @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Autor> autores = autorServicio.listarAutores();
        modelo.addAttribute("autores", autores);
        return "autor_list.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,ModelMap modelo){
        modelo.put("autor", autorServicio.getOne(id));
        return "autor_modificar.html";
    }
    
    @PostMapping("modificar/{id}")
    public String modificar(@PathVariable String id,String nombre,ModelMap modelo){
        try {
            autorServicio.modificarAutores(nombre, id);
            return "redirect:../lista";
        } catch (MiException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "autor_modificar.html";
        }
    }
    
}
