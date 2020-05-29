package com.desafio.resource;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.config.TokenUtil;
import com.desafio.exception.InvalidException;
import com.desafio.model.Usuario;
import com.desafio.repository.UsuarioRepository;
import com.desafio.response.Response;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//retornar token de acesso com as inforções do usuario 
	@GetMapping("/singin")
	public ResponseEntity<Object>  singin(@Validated @RequestBody Usuario usuario) {
		Usuario usuarioResultado = usuarioRepository.findByLogin(usuario.getLogin());

		//campos login ou password null retorna mensagem de erro
		if (usuarioResultado.getLogin() == null || usuarioResultado.getPassword()== null) {
			throw new InvalidException("Invalid login or password");
		}

		Response<Usuario> response  = new Response<>();
		response.setToken(TokenUtil.getToken(usuarioResultado.getLogin()));
		    
		return ResponseEntity.ok(response);
	}

	@GetMapping //Listar todos usuarios
	public List<Usuario> Listar() {
		return usuarioRepository.findAll();
	}

	@PostMapping //Cadastrar um novo usuario
	public Usuario Cadastrar(@Validated @RequestBody Usuario usuario) {

		if (!usuario.validarFields()) {
			throw new InvalidException("Missing Fields");
		}

		return usuarioRepository.save(usuario);
	}

	@GetMapping("/{id}") // /usuario/1 Buscar um usuario pelo id
	ResponseEntity<Usuario> Buscar(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.getOne(id);
	
		//se usuario vir null retornar status não encontrado
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);

	}

	@PutMapping("/{id}") //Atualizar um usuario pelo id
	ResponseEntity<Usuario> Atualizar(@PathVariable Long id, @Validated @RequestBody Usuario usuario) {

		Usuario existe = usuarioRepository.getOne(id);

		//se usuario vir null retornar status não encontrado
		if (existe == null) {
			return ResponseEntity.notFound().build();
		}
		//se campos do usuario for vazio, retornar mensagem de erro.
		if (!usuario.validarFields()) {
			throw new InvalidException("Missing Fields");
		}
		//se campo email for vazio, retornar mensagem de erro.
		if (usuario.getEmail() == existe.getEmail()) {
			throw new InvalidException("Email already exists");
		}
		//se campo login for vazio, retornar mensagem de erro.
		if (usuario.getLogin() == existe.getLogin()) {
			throw new InvalidException("Login already exists");
		}

		BeanUtils.copyProperties(usuario, existe, "id");

		existe = usuarioRepository.save(existe);

		return ResponseEntity.ok(existe);

	}

	@DeleteMapping("/{id}") // /usuario/1 -- deletar um usuario pelo id 
	ResponseEntity<Void> Deletar(@PathVariable Long id, @Validated @RequestBody Usuario usuario) {
		Usuario existeUsu = usuarioRepository.getOne(id);
		
		//se usuario vir null retornar status não encontrado
		if (existeUsu == null) {
			return ResponseEntity.notFound().build();
		}

		usuarioRepository.delete(existeUsu);
		
		//retornar resposta vazia
		return ResponseEntity.noContent().build();

	}

}
