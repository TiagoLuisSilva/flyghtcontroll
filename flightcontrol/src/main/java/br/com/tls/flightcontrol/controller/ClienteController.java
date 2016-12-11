package br.com.tls.flightcontrol.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tls.flightcontrol.component.ClienteComponent;
import br.com.tls.flightcontrol.dto.ClienteDTO;
import br.com.tls.flightcontrol.entity.Cliente;
import br.com.tls.flightcontrol.exceptions.ValidarException;

@RestController	
public class ClienteController {

	@Autowired
	private ClienteComponent clienteComponent;

	@RequestMapping(value="/api/clientes/consultar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  pesquisar(@RequestBody ClienteDTO clienteDTO) throws ValidarException{
		ResponseEntity<Object> retorno = null;
		
		try {
			List<Cliente> lista =   clienteComponent.consultarNome(clienteDTO.getNome()); 
		    
			List<ClienteDTO> listaDto = new ArrayList<ClienteDTO>();
			for (Cliente cliente : lista){
				ClienteDTO dto = new ClienteDTO(cliente);
				listaDto.add(dto);
			}
			return new ResponseEntity<Object>(listaDto, null, HttpStatus.OK);
		}  catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	}
	 
	@RequestMapping(value="/api/clientes", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  gravar(@RequestBody ClienteDTO clienteDTO) throws ValidarException{
		ResponseEntity<Object> retorno = null; 
		try {
			   Cliente cliente = clienteDTO.toCliente();
			   clienteComponent.persistir( cliente); 
			   retorno = new ResponseEntity<Object> (null, null, HttpStatus.CREATED); 
		}  catch (ValidarException e) { 
			   retorno = new ResponseEntity<Object>(e.getListaErros(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	}


	@RequestMapping(value="/api/clientes/excluir", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  excluir(@RequestBody ClienteDTO clienteDTO) throws ValidarException{
		ResponseEntity<Object> retorno = null;
		try {
			clienteComponent.excluir(clienteDTO.toCliente());
		} catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return retorno;
	}
	
	@RequestMapping(value="/api/clientes/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody ResponseEntity<Object> consultarPorId(@PathVariable("id") Long id) throws ValidarException{ 
		ResponseEntity<Object> retorno = null; 
		try {
			Cliente cliente = clienteComponent.consultarPorId(id);		
			  retorno = new ResponseEntity<Object>(cliente, null, HttpStatus.OK);
		 
		}  catch (ValidarException e) { 
			   retorno = new ResponseEntity<Object>(e.getListaErros(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	} 
	
}
