/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca276.servicios;

import com.egg.biblioteca276.entidades.Autor;
import com.egg.biblioteca276.excepciones.MiException;
import com.egg.biblioteca276.repositorio.AutorRepositorio;
import java.util.ArrayList;
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
public class AutorServicio {
    
    @Autowired
    AutorRepositorio autorRepositorio;
    
    @Transactional
    public void crearAutor(String nombre) throws MiException{
        
        
        if(nombre.isEmpty()||nombre==null){
            throw new MiException("EL NOMBRE NO PUEDE ESTAR VACIO NI SER NULO");
        }
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
        
    }
    
    public List<Autor> listarAutores(){
        
        List<Autor> autores = new ArrayList();
        
        autores= autorRepositorio.findAll();
        
        return autores;
                
        
    }
    
    public void modificarAutores(String nombre,String id) throws MiException{
        
        validar(nombre, id);
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);
            
            
        }
        
    }
    
    private void validar(String nombre,String id) throws MiException{
        
       if(nombre.isEmpty() || nombre==null){
           throw new MiException("EL NOMBRE NO PUEDE ESTAR VACIO O SER NULO");
       }
       
       if(id.isEmpty()||id==null){
           throw new MiException("LA ID NO PUEDE ESTAR VACIO O SER NULA");
       }
        
    }
    
    public Autor getOne(String id){
        return autorRepositorio.getOne(id);
    }
    
}
