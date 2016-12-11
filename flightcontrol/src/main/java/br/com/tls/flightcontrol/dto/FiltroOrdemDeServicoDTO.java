package br.com.tls.flightcontrol.dto;

import java.util.Date;

public class FiltroOrdemDeServicoDTO {
    private Long idCliente;
    private Date dataInicio;
    private Date dataFinal;
    
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
}
