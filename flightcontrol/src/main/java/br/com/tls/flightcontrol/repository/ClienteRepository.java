package br.com.tls.flightcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tls.flightcontrol.entity.Cliente;

public interface ClienteRepository extends   JpaRepository<Cliente, Long>   {

}
