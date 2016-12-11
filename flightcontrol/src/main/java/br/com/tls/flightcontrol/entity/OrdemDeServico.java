package br.com.tls.flightcontrol.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="ordemdeservico")
public class OrdemDeServico {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	@Column
	private Long cardNumber; 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	@Column
	private String modelo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmissao;
	@Column
	private String prefixo;
	@Column
	private String descricao;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	@OneToMany(mappedBy="ordemDeServico", cascade=CascadeType.ALL ,fetch=FetchType.EAGER)
	private List<ItemOrdemDeServico> listaItens;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getPrefixo() {
		return prefixo;
	}
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ItemOrdemDeServico> getListaItens() {
		return listaItens;
	}
	public void setListaItens(List<ItemOrdemDeServico> listaItens) {
		this.listaItens = listaItens;
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	} 
	 
}
