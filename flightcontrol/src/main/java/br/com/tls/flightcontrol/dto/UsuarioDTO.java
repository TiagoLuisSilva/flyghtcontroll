package br.com.tls.flightcontrol.dto;

import br.com.tls.flightcontrol.entity.Usuario;

public class UsuarioDTO {
	private Long id; 
	private String nome; 
	private String userName; 
	private Boolean admin;
	private String senha; 
	
	public  UsuarioDTO(){ 
	}

	public  UsuarioDTO(Usuario usuario){
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.userName = usuario.getUserName();
		this.admin = usuario.isAdmin();
		this.senha = usuario.getSenha();
	}
	
	public Usuario toUsuario(){
		Usuario usuario =  new Usuario();
		usuario.setId(id);
		usuario.setNome(nome);
		usuario.setUserName(userName);
		usuario.setAdmin(admin == null || !admin ? false : true);
		usuario.setSenha(senha);
		return usuario;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	} 
	
}
