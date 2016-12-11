package br.com.tls.flightcontrol.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.google.common.collect.Lists;

public class ValidarException extends Exception{
 
	private static final long serialVersionUID = 1L;
			
	private List<String> listaErros;
	
	private HttpStatus status ;
	
	public void adicionarMensagem(String mensagem){
		this.getListaErros().add(mensagem); 
	} 
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public ValidarException() {
		this.listaErros = new ArrayList<String>();
	}
	
	public ValidarException(String error) {
		this.listaErros = Lists.newArrayList(error);
	}
	
	public boolean existeErros(){
		return listaErros != null && !listaErros.isEmpty();
	}

	public List<String> getListaErros() {		
		return listaErros;
	}

	public void setListaErros(List<String> listaErros) {
		this.listaErros = listaErros;
	}
	
}
