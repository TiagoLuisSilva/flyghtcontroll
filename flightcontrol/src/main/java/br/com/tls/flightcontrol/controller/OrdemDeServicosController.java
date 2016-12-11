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

import br.com.tls.flightcontrol.component.OdemDeServicoComponent;
import br.com.tls.flightcontrol.dto.FiltroOrdemDeServicoDTO;
import br.com.tls.flightcontrol.dto.OrdemDeServicoDTO;
import br.com.tls.flightcontrol.entity.OrdemDeServico;
import br.com.tls.flightcontrol.exceptions.ValidarException;

@RestController	
public class OrdemDeServicosController {

	@Autowired
	private OdemDeServicoComponent ordemDeServicoComponent;

	@RequestMapping(value="/api/ordemDeServicos/consultar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  pesquisar(@RequestBody FiltroOrdemDeServicoDTO filtroDto) throws ValidarException{
		ResponseEntity<Object> retorno = null;
		
		try {
			List<OrdemDeServico> lista =   ordemDeServicoComponent.consultar(filtroDto); 
		    
			List<OrdemDeServicoDTO> listaDto = new ArrayList<OrdemDeServicoDTO>();
			for (OrdemDeServico ordemDeServico : lista){
				OrdemDeServicoDTO dto = new OrdemDeServicoDTO(ordemDeServico);
				listaDto.add(dto);
			}
			return new ResponseEntity<Object>(listaDto, null, HttpStatus.OK);
		}  catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	}
	 
	@RequestMapping(value="/api/ordemDeServicos", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  gravar(@RequestBody OrdemDeServicoDTO ordemDeServicoDTO) throws ValidarException{
		ResponseEntity<Object> retorno = null; 
		try {
			   OrdemDeServico ordemDeServico = ordemDeServicoDTO.toOrdemDeServico();
			   ordemDeServicoComponent.persistir( ordemDeServico); 
			   retorno = new ResponseEntity<Object> (null, null, HttpStatus.CREATED); 
		}  catch (ValidarException e) { 
			   retorno = new ResponseEntity<Object>(e.getListaErros(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	}


	@RequestMapping(value="/api/ordemDeServicos/excluir", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  excluir(@RequestBody OrdemDeServicoDTO ordemDeServicoDTO) throws ValidarException{
		ResponseEntity<Object> retorno = null;
		try {
			ordemDeServicoComponent.excluir(ordemDeServicoDTO.toOrdemDeServico());
		} catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return retorno;
	}
	
	@RequestMapping(value="/api/ordemDeServicos/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody ResponseEntity<Object> consultarPorId(@PathVariable("id") Long id) throws ValidarException{ 
		ResponseEntity<Object> retorno = null; 
		try {
			OrdemDeServico ordemDeServico = ordemDeServicoComponent.consultarPorId(id);		
			  retorno = new ResponseEntity<Object>(ordemDeServico, null, HttpStatus.OK);
		 
		}  catch (ValidarException e) { 
			   retorno = new ResponseEntity<Object>(e.getListaErros(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	} 
	
}
