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

import br.com.tls.flightcontrol.component.UsuarioComponent;
import br.com.tls.flightcontrol.dto.UsuarioDTO;
import br.com.tls.flightcontrol.entity.Usuario;
import br.com.tls.flightcontrol.exceptions.ValidarException;

@RestController	
public class UsuarioController {

	@Autowired
	private UsuarioComponent usuarioComponent;

	@RequestMapping(value="/api/usuarios/consultar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  pesquisar(@RequestBody UsuarioDTO usuarioDTO) throws ValidarException{
		ResponseEntity<Object> retorno = null;
		
		try {
			List<Usuario> lista =   usuarioComponent.consultarNome(usuarioDTO.getNome()); 
		    
			List<UsuarioDTO> listaDto = new ArrayList<UsuarioDTO>();
			for (Usuario usuario : lista){
				UsuarioDTO dto = new UsuarioDTO(usuario);
				listaDto.add(dto);
			}
			return new ResponseEntity<Object>(listaDto, null, HttpStatus.OK);
		}  catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	}
	 
	@RequestMapping(value="/api/usuarios", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  gravar(@RequestBody UsuarioDTO usuarioDTO) throws ValidarException{
		ResponseEntity<Object> retorno = null; 
		try {
			   Usuario usuario = usuarioDTO.toUsuario();
			   usuarioComponent.persistir( usuario); 
			   retorno = new ResponseEntity<Object> (null, null, HttpStatus.CREATED); 
		}  catch (ValidarException e) { 
			   retorno = new ResponseEntity<Object>(e.getListaErros(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	}


	@RequestMapping(value="/api/usuarios/excluir", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public  @ResponseBody ResponseEntity<Object>  excluir(@RequestBody UsuarioDTO usuarioDTO) throws ValidarException{
		ResponseEntity<Object> retorno = null;
		try {
			usuarioComponent.excluir(usuarioDTO.toUsuario());
		} catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return retorno;
	}
	
	@RequestMapping(value="/api/usuarios/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody ResponseEntity<Object> consultarPorId(@PathVariable("id") Long id) throws ValidarException{ 
		ResponseEntity<Object> retorno = null; 
		try {
			Usuario usuario = usuarioComponent.consultarPorId(id);		
			  retorno = new ResponseEntity<Object>(usuario, null, HttpStatus.OK);
		 
		}  catch (ValidarException e) { 
			   retorno = new ResponseEntity<Object>(e.getListaErros(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) { 
			   retorno = new ResponseEntity<Object>("Erro interno", null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	} 
	
}
