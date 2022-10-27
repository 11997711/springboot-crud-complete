package com.springboot.alexjdev.crud.completo.crudcompletospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.alexjdev.crud.completo.crudcompletospringboot.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

}
	
	

