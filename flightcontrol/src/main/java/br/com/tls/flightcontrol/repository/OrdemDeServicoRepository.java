package br.com.tls.flightcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tls.flightcontrol.entity.OrdemDeServico;

public interface OrdemDeServicoRepository extends   JpaRepository<OrdemDeServico, Long>   {

}
