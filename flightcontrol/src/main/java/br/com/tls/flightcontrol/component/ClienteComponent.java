package br.com.tls.flightcontrol.component;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tls.flightcontrol.entity.Cliente;
import br.com.tls.flightcontrol.exceptions.ValidarException;
import br.com.tls.flightcontrol.repository.ClienteRepository;
@Component
public class ClienteComponent {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public void persistir(Cliente cliente) throws ValidarException{
    	validarDados(cliente); 
    	if (cliente.getId() == null || cliente.getId() == 0L)
    	{
    		cliente.setDataCadastro(new Date());
    	}
    	clienteRepository.save(cliente);
    }
 
	public Cliente consultarPorId(Long idCliente) throws ValidarException{
    	
    	Cliente cliente = clienteRepository.findOne(idCliente);
    	if (cliente==null){
    		ValidarException validacao = new ValidarException();
			validacao.adicionarMensagem("Cliente n&atildeo encontrado");
			throw validacao;
    	}
    	
    	return cliente; 
    }
    
    public void excluir(Cliente cliente){
    	clienteRepository.delete(cliente);
    }

	protected void validarDados(Cliente cliente) throws ValidarException {
	 
		ValidarException validacao = new ValidarException();
		 if (StringUtils.isBlank(cliente.getNome())){
			 validacao.adicionarMensagem("O campo Nome deve ser informado");
		 } 
		 if ("PF".equals(cliente.getTipoPessoa())){ 
			 if (StringUtils.isBlank(cliente.getCpfCnpj())){
				 validacao.adicionarMensagem("O campo CPF deve ser informado");
			 } 
		 } else {
			 if (StringUtils.isBlank(cliente.getCpfCnpj())){
				 validacao.adicionarMensagem("O campo CNPJ deve ser informado");
			 }  
		 }
		 if (validacao.existeErros()){
			 throw validacao;
		 }
	}

	public List<Cliente> consultarNome(String consulta) {
		if (StringUtils.isBlank(consulta)){
			consulta ="";
		}
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Cliente.class);
		criteria.add(Restrictions.like("nome", "%"+consulta.trim()+"%").ignoreCase());
		return criteria.list();
	}

}
