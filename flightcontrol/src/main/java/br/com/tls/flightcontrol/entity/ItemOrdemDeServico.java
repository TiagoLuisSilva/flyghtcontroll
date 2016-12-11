package br.com.tls.flightcontrol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="ItensOrdemDeServico")
public class ItemOrdemDeServico {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;  
	 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_ordemdeservico")
	private OrdemDeServico ordemDeServico; 
	@Column
	private String removidoInstalado;
	@Column
	private String descricao;
	@Column
	private String partNumber;
	@Column
	private String serialNumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OrdemDeServico getOrdemDeServico() {
		return ordemDeServico;
	}
	public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
		this.ordemDeServico = ordemDeServico;
	}
	public String getRemovidoInstalado() {
		return removidoInstalado;
	}
	public void setRemovidoInstalado(String removidoInstalado) {
		this.removidoInstalado = removidoInstalado;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}
