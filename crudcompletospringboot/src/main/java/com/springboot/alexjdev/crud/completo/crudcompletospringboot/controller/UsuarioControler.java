package com.springboot.alexjdev.crud.completo.crudcompletospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.alexjdev.crud.completo.crudcompletospringboot.model.UsuarioModel;
import com.springboot.alexjdev.crud.completo.crudcompletospringboot.repository.UsuarioRepository;

@RestController
public class UsuarioControler {

	@Autowired // Injeção de dependencia
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/helloworld/{name}")
	@ResponseStatus(HttpStatus.OK)
	public String helloWorld(@PathVariable String name) {

		UsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.setNome(name);
		usuarioRepository.save(usuarioModel); // save , grava no banco de dados

		return "Hello World " + name + " the best";
	}

	@GetMapping(value = "/listartodos")
	@ResponseBody // Retorna os dados para o corpo da resposta
	public ResponseEntity<List<UsuarioModel>> listaUsuario() {

		List<UsuarioModel> usuarios = usuarioRepository.findAll(); // executa a consulta no banco de dados

		return new ResponseEntity<List<UsuarioModel>>(usuarios, HttpStatus.OK); // Retorna a lista em JSON
	}

	@PostMapping(value = "/salvar") // Faz o mapeamento da URL
	@ResponseBody // Descrição da resposta
	public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuarioModel) { // @RequestBody recebe os dados
																							// para salvar

		UsuarioModel user = usuarioRepository.save(usuarioModel);

		return new ResponseEntity<UsuarioModel>(user, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/deletar")
	public ResponseEntity<String> delete(@RequestParam Long iduser) {
		
		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);

	}

	@GetMapping(value = "/buscaruserid") // mapeia a URL
	public ResponseEntity<UsuarioModel> buscarUserPorId(@RequestParam Long iduser) { // Recebe os dados para consultar

		UsuarioModel user = usuarioRepository.findById(iduser).get();

		return new ResponseEntity<UsuarioModel>(user, HttpStatus.OK);

	}

	@PutMapping(value = "/atualizar") // Faz o mapeamento da URL
	@ResponseBody // Descrição da resposta
	public ResponseEntity<?> atualizar(@RequestBody UsuarioModel usuarioModel) { // @RequestBody recebe os dados para
																					// salvar

		if (usuarioModel.getId() == null) {
			return new ResponseEntity<String>("Necessário informar o ID para a atualização", HttpStatus.OK);
		}

		UsuarioModel user = usuarioRepository.saveAndFlush(usuarioModel);

		return new ResponseEntity<UsuarioModel>(user, HttpStatus.OK);
	}

	@GetMapping(value = "/buscarpornome") // mapeia a URL
	public ResponseEntity<List<UsuarioModel>> buscarPorNome(@RequestParam String name) { // Recebe os dados para
																							// consultar

		@SuppressWarnings("unchecked")
		List<UsuarioModel> user =  usuarioRepository.buscarPorNome(name);

		return new ResponseEntity<List<UsuarioModel>>(user, HttpStatus.OK);

	}

}