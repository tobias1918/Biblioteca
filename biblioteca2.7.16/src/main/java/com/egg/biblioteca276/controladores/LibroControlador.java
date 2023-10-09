/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca276.controladores;

import com.egg.biblioteca276.entidades.Autor;
import com.egg.biblioteca276.entidades.Editorial;
import com.egg.biblioteca276.entidades.Libro;
import com.egg.biblioteca276.excepciones.MiException;
import com.egg.biblioteca276.servicios.AutorServicio;
import com.egg.biblioteca276.servicios.EditorialServicio;
import com.egg.biblioteca276.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/libro")  ////localhost:8080/libro
public class LibroControlador {
    
    @Autowired
    private LibroServicio libroServicio;
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @GetMapping("/registrar") //localhost:8080/libro/registrar
    public String registrar(ModelMap modelo){
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        return "libro_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo ,@RequestParam(required = false) Integer ejemplares,
            @RequestParam String idAutor,@RequestParam String idEditorial, ModelMap modelo){
       
        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exitoLibro", "el libro fue cargado correctamente");
        } catch (MiException ex) {
             List<Autor> autores = autorServicio.listarAutores();
             List<Editorial> editoriales = editorialServicio.listarEditoriales();
        
              modelo.addAttribute("autores", autores);
              modelo.addAttribute("editoriales", editoriales);
            modelo.put("errorLibro", ex.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Libro> libros = libroServicio.listarLibros();
        modelo.addAttribute("libros", libros);
        
        return "libro_list.html";
        
    }
   

        
    
}
