package br.com.tls.flightcontrol.dto;

import java.util.Date;

import javax.persistence.Column;

import br.com.tls.flightcontrol.entity.Cliente;

public class ClienteDTO {

	private Long id; 
	private String nome;
	private String tipoPessoa; 
	private String cpfCnpj; 
	private String endereco; 
	private String numero; 
	private String bairro; 
	private String cep; 
	private String cidade;
	private Date dataCadastro;

	public  ClienteDTO(){ 
	}

	public ClienteDTO(Cliente cliente) { 
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.tipoPessoa = cliente.getTipoPessoa();
		this.cpfCnpj = cliente.getCpfCnpj();
		this.endereco = cliente.getEndereco();
		this.numero = cliente.getNumero();
		this.bairro = cliente.getBairro();
		this.cep = cliente.getCep();
		this.cidade = cliente.getCidade();
		this.dataCadastro = cliente.getDataCadastro();
	}
	
	public Cliente toCliente(){
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);
		cliente.setTipoPessoa(tipoPessoa);
		cliente.setCpfCnpj(cpfCnpj);
		cliente.setEndereco(endereco);
		cliente.setNumero(numero);
		cliente.setBairro(bairro);
		cliente.setCep(cep);
		cliente.setCidade(cidade);
		cliente.setDataCadastro(dataCadastro);
		return cliente;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	} 
	
}
