/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca276.servicios;

import com.egg.biblioteca276.entidades.Editorial;
import com.egg.biblioteca276.excepciones.MiException;
import com.egg.biblioteca276.repositorio.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EditorialServicio {
    
    @Autowired
    EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre) throws MiException{
        
        if(nombre.isEmpty()||nombre==null){
            throw new MiException("EL NOMBRE NO PUEDE ESTAR VACIO O SER NULO");
        }
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
        
        
    }
    
    public List<Editorial> listarEditoriales(){
        
        List<Editorial> editoriales = new ArrayList();
        
        editoriales = editorialRepositorio.findAll();
        
        return editoriales;
        
    }
    
    public void modificarEditorial(String id,String nombre) throws MiException{
        
        validar(id, nombre);
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Editorial editorial=respuesta.get();
            
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
            
        }
        
    }
    
    private void validar(String id,String nombre)throws MiException{
        
        if(id.isEmpty()||id==null){
            throw new MiException("EL ID NO PUEDE ESTAR NULO O ESTAR VACIO");
        }
        
        if(nombre.isEmpty()||nombre==null){
            throw new MiException("EL NOMBRE NO PUEDE ESTAR VACIO O SER NULO");
        }
        
    }
    
}
