package br.com.tls.flightcontrol.dto;

import br.com.tls.flightcontrol.entity.ItemOrdemDeServico;
public class ItemOrdemDeServicoDTO {

	private Long id;
	private Long idOrdemDeServico;  
	private String removidoInstalado; 
	private String descricao; 
	private String partNumber; 
	private String serialNumber; 
	
	public ItemOrdemDeServicoDTO(){
		
	}
	public ItemOrdemDeServicoDTO(ItemOrdemDeServico itensOrdemDeServico){ 
		this.id = itensOrdemDeServico.getId();
		this.idOrdemDeServico = itensOrdemDeServico.getOrdemDeServico().getId();
		this.removidoInstalado = itensOrdemDeServico.getRemovidoInstalado();
		this.descricao = itensOrdemDeServico.getDescricao();
		this.partNumber = itensOrdemDeServico.getPartNumber();
		this.serialNumber = itensOrdemDeServico.getSerialNumber();
		
	}
	public ItemOrdemDeServico toItensOrdemDeServico(){
		ItemOrdemDeServico itenOrdemDeServico = new ItemOrdemDeServico();
		itenOrdemDeServico.setId(id);
		itenOrdemDeServico.setRemovidoInstalado(removidoInstalado);
		itenOrdemDeServico.setDescricao(descricao);
		itenOrdemDeServico.setPartNumber(partNumber);
		itenOrdemDeServico.setSerialNumber(serialNumber);
		return itenOrdemDeServico;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdOrdemDeServico() {
		return idOrdemDeServico;
	}
	public void setIdOrdemDeServico(Long idOrdemDeServico) {
		this.idOrdemDeServico = idOrdemDeServico;
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
