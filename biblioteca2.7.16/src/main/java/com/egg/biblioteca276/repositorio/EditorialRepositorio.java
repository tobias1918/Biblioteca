/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca276.repositorio;

import com.egg.biblioteca276.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tobia
 */
@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    
}
