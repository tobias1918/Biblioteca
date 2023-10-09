/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca276.servicios;

import com.egg.biblioteca276.entidades.Autor;
import com.egg.biblioteca276.entidades.Editorial;
import com.egg.biblioteca276.entidades.Libro;
import com.egg.biblioteca276.excepciones.MiException;
import com.egg.biblioteca276.repositorio.AutorRepositorio;
import com.egg.biblioteca276.repositorio.EditorialRepositorio;
import com.egg.biblioteca276.repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tobia
 */
@Service
public class LibroServicio {
    
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    
    @Transactional
    public void crearLibro(Long isbn, String titulo , Integer ejemplares, String idAutor, String idEditorial) throws MiException{
       
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
   
        Libro libro = new Libro();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
        
        
    }
    
    public List<Libro> listarLibros(){
        
        List<Libro> libros = new ArrayList();
        
        libros = libroRepositorio.findAll();
        
        return libros;
    }
    
    public void  modificarLibro(Long isbn, String titulo , Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        //OPTIONAL CONTROLA QUE EL ISBN/ID NO SEA ERRONEO O SEA NULO Y PRODUZCA UN ERROR ;
        Optional<Libro> respuesta =  libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        
        if(respuestaAutor.isPresent()){
            autor=respuestaAutor.get();
        }
        
        if(respuestaEditorial.isPresent()){
            editorial=respuestaEditorial.get();
        }
        
        //IS PRESENT DEVUELVE TRUE SI CONTIENE ALGO YA QUE SI ESTABA MAL EL ERROR EN EL CODIGO ANTERIOR NO SE HUBIESE LLENADO Y DEVOLVERIA FALSE
        if(respuesta.isPresent()){
            
            Libro libro = respuesta.get();
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            
            libroRepositorio.save(libro);
        }
        
    }
    
    private void validar(Long isbn, String titulo , Integer ejemplares, String idAutor, String idEditorial) throws MiException{
         
        if(isbn==null){
            throw new MiException("ISBN NO PUEDE ESTAR VACIO");
        }
        if(titulo==null || titulo.isEmpty()){
            throw new MiException("TITULO NO PUEDE SER VACIO O NULO");
        }
        
        if(ejemplares== null ){
            throw new MiException("EJEMPLARES NO PUEDEN SER NULOS");
        }
        
        if(idAutor == null||idAutor.isEmpty()){
            throw new MiException("IDAUTOR NO PUEDE SER ESTAR VACIO NI NULO");
        }
        
        if(idEditorial == null || idEditorial.isEmpty()){
            throw new MiException("IDEDITORIAL NO PUEDE ESTAR VACIO NI NULO");
        }
    }
    
    
}


