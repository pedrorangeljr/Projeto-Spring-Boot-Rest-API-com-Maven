package br.com.springboot.curso_jdev_treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
