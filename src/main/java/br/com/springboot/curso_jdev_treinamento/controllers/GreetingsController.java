package br.com.springboot.curso_jdev_treinamento.controllers;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @RequestMapping(value = "/mostranome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso jdev treinamentos:  " + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retonaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario);
    	
    	return "Ola mundo " + nome;
    }
    
    @GetMapping(value = "/listartodos")
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario() {
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @PostMapping(value = "salvar") // mapeia a URL
    @ResponseBody/*Descrição da resposta*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
    	
       Usuario user = usuarioRepository.save(usuario);
       
       return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "delete") // mapeia a URL
    @ResponseBody/*Descrição da resposta*/
    public ResponseEntity<String> delete(@RequestParam Long idUser) {
    	
       usuarioRepository.deleteById(idUser);
       
       return new ResponseEntity<String>("User Deletado com Sucesso", HttpStatus.OK);
    }
    
    
    @GetMapping(value = "buscaruserid") // mapeia a URL
    @ResponseBody/*Descrição da resposta*/
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "idUser") Long idUser) {
    	
       Usuario usuario = usuarioRepository.findById(idUser).get();
       
       return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @PutMapping(value = "atualizar") // mapeia a URL
    @ResponseBody/*Descrição da resposta*/
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
    
    	if(usuario.getId() == null) {
    		
    		return new ResponseEntity<String>("Id não foi Informado", HttpStatus.OK);
    		
    	}
       Usuario user = usuarioRepository.saveAndFlush(usuario);
       
       return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
}
