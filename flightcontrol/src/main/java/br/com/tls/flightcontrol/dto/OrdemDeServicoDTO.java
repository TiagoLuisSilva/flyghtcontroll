package br.com.tls.flightcontrol.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.tls.flightcontrol.entity.ItemOrdemDeServico;
import br.com.tls.flightcontrol.entity.OrdemDeServico;

public class OrdemDeServicoDTO {
 
	private Long id;  
	private ClienteDTO cliente; 
	private String modelo; 
	private Date dataEmissao; 
	private String prefixo; 
	private String descricao;  
	private UsuarioDTO usuario; 
	private List<ItemOrdemDeServicoDTO> listaItens;
	
	public OrdemDeServicoDTO(){
		
	}
	public OrdemDeServicoDTO(OrdemDeServico ordemDeServico){
		this.id = ordemDeServico.getId();
		this.cliente = new ClienteDTO(ordemDeServico.getCliente());
		this.modelo = ordemDeServico.getModelo();
		this.dataEmissao  = ordemDeServico.getDataEmissao();
		this.prefixo = ordemDeServico.getPrefixo();
		this.descricao = ordemDeServico.getDescricao();
		this.usuario = new UsuarioDTO(ordemDeServico.getUsuario());
		listaItens = new ArrayList<ItemOrdemDeServicoDTO>();
		if (ordemDeServico.getListaItens() != null){
			for (ItemOrdemDeServico item : ordemDeServico.getListaItens() ){
				ItemOrdemDeServicoDTO itemDto = new ItemOrdemDeServicoDTO(item);
				listaItens.add(itemDto);
			}	
		}
	}
	
	public OrdemDeServico toOrdemDeServico(){
		OrdemDeServico ordemDeServico = new OrdemDeServico();
		ordemDeServico.setCliente(cliente.toCliente());
		ordemDeServico.setModelo(modelo);
		ordemDeServico.setDataEmissao(dataEmissao);
		ordemDeServico.setPrefixo(prefixo);
		ordemDeServico.setDescricao(descricao);
		ordemDeServico.setUsuario(usuario.toUsuario());
		ordemDeServico.setListaItens(new ArrayList<ItemOrdemDeServico>());
		if (listaItens != null){
			for (ItemOrdemDeServicoDTO itemDto : listaItens ){
				ItemOrdemDeServico item = itemDto.toItensOrdemDeServico();
				ordemDeServico.getListaItens().add(item);
			}
		}
		return ordemDeServico; 
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
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
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public List<ItemOrdemDeServicoDTO> getListaItens() {
		return listaItens;
	}
	public void setListaItens(List<ItemOrdemDeServicoDTO> listaItens) {
		this.listaItens = listaItens;
	}
	
	
	
}
