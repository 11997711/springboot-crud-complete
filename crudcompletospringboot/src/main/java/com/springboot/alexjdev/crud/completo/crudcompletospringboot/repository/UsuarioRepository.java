package com.springboot.alexjdev.crud.completo.crudcompletospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.alexjdev.crud.completo.crudcompletospringboot.model.UsuarioModel;

import antlr.collections.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
	
	@Query(value = "select u from UsuarioModel u where trim(u.nome) like %?1% ")
	java.util.List<UsuarioModel> buscarPorNome(String name);

	
}
	
	

