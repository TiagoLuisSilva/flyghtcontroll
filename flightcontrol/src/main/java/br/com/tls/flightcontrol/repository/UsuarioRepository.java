package br.com.tls.flightcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tls.flightcontrol.entity.Usuario;

public interface UsuarioRepository extends   JpaRepository<Usuario, Long>   {

}
