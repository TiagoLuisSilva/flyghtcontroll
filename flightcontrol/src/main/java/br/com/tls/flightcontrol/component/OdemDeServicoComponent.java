package br.com.tls.flightcontrol.component;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tls.flightcontrol.dto.FiltroOrdemDeServicoDTO;
import br.com.tls.flightcontrol.entity.OrdemDeServico;
import br.com.tls.flightcontrol.exceptions.ValidarException;
import br.com.tls.flightcontrol.repository.OrdemDeServicoRepository;
@Component
public class OdemDeServicoComponent {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private OrdemDeServicoRepository ordemDeServicoRepository;
    
    public void persistir(OrdemDeServico ordemDeServico) throws ValidarException{
    	validarDados(ordemDeServico);
    	 
    	
    	ordemDeServicoRepository.save(ordemDeServico);
    }

 

	public OrdemDeServico consultarPorId(Long idOrdemDeServico) throws ValidarException{
    	
    	OrdemDeServico ordemDeServico = ordemDeServicoRepository.findOne(idOrdemDeServico);
    	if (ordemDeServico==null){
    		ValidarException validacao = new ValidarException();
			validacao.adicionarMensagem("OrdemDeServico n&atildeo encontrado");
			throw validacao;
    	}
    	
    	return ordemDeServico; 
    }
    
    public void excluir(OrdemDeServico ordemDeServico){
    	ordemDeServicoRepository.delete(ordemDeServico);
    }

	protected void validarDados(OrdemDeServico ordemDeServico) throws ValidarException {
	 
		ValidarException validacao = new ValidarException();
		 if (ordemDeServico.getCliente() == null || 
			 ordemDeServico.getCliente().getId() == null || 
			 ordemDeServico.getCliente().getId() == 0L){
			 validacao.adicionarMensagem("O campo Cliente deve ser informado");
		 }
		 if (StringUtils.isBlank(ordemDeServico.getModelo())){
			 validacao.adicionarMensagem("O campo Modelo deve ser informado");			 
		 } 

		 if (StringUtils.isBlank(ordemDeServico.getPrefixo())){
			 validacao.adicionarMensagem("O campo Prefixo deve ser informado");			 
		 } 

		 if (StringUtils.isBlank(ordemDeServico.getDescricao())){
			 validacao.adicionarMensagem("O campo Prefixo deve ser informado");			 
		 } 

		 if (ordemDeServico.getListaItens() == null || ordemDeServico.getListaItens().isEmpty()){
			 validacao.adicionarMensagem("Nenhum Material foi adicionado ");			 
		 } 
		 
		 if (validacao.existeErros()){
			 throw validacao;
		 }
	}

	public List<OrdemDeServico> consultar(FiltroOrdemDeServicoDTO filtro) {
 
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(OrdemDeServico.class);
		if (filtro.getIdCliente() !=null){
			criteria.createAlias("cliente", "cliente");
			criteria.add(Restrictions.eq("cliente.id", filtro.getIdCliente())); 
		}
		if (filtro.getDataInicio() != null && filtro.getDataFinal() == null){
			criteria.add(Restrictions.ge("dataEmissao", filtro.getDataInicio() ));
		} else if (filtro.getDataInicio() == null && filtro.getDataFinal() != null){
			criteria.add(Restrictions.le("dataEmissao", filtro.getDataFinal() ));
		} else if (filtro.getDataInicio() != null && filtro.getDataFinal() != null){
			criteria.add(Restrictions.between("dataEmissao", filtro.getDataInicio(), filtro.getDataFinal()));
		}
		return criteria.list();
	}

}
