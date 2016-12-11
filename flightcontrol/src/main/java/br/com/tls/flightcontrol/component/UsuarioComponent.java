package br.com.tls.flightcontrol.component;

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

import br.com.tls.flightcontrol.entity.Usuario;
import br.com.tls.flightcontrol.exceptions.ValidarException;
import br.com.tls.flightcontrol.repository.UsuarioRepository;
@Component
public class UsuarioComponent {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public void persistir(Usuario usuario) throws ValidarException{
    	validarDados(usuario);
    	validarSenha(usuario);
    	
    	usuarioRepository.save(usuario);
    }

    private void validarSenha(Usuario usuario) {

		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class); 
		criteria.add(Restrictions.eq("id", usuario.getId()));
		criteria.setProjection(Projections.property("senha"));
		
		String senha = (String) criteria.uniqueResult();
		if (StringUtils.isBlank(usuario.getSenha())){
			usuario.setSenha(senha);
		}
	}

	public Usuario consultarPorId(Long idUsuario) throws ValidarException{
    	
    	Usuario usuario = usuarioRepository.findOne(idUsuario);
    	if (usuario==null){
    		ValidarException validacao = new ValidarException();
			validacao.adicionarMensagem("Usuario n&atildeo encontrado");
			throw validacao;
    	}
    	
    	return usuario; 
    }
    
    public void excluir(Usuario usuario){
    	usuarioRepository.delete(usuario);
    }

	protected void validarDados(Usuario usuario) throws ValidarException {
	 
		ValidarException validacao = new ValidarException();
		 if (StringUtils.isBlank(usuario.getNome())){
			 validacao.adicionarMensagem("O campo Nome deve ser informado");
		 }
		 if (StringUtils.isBlank(usuario.getUserName())){
			 validacao.adicionarMensagem("O campo Usuario deve ser informado");
		 }
		 if (StringUtils.isBlank(usuario.getSenha())){
			 validacao.adicionarMensagem("O campo Senha deve ser informado");
		 }
		 if (validacao.existeErros()){
			 throw validacao;
		 }
	}

	public List<Usuario> consultarNome(String consulta) {
		if (StringUtils.isBlank(consulta)){
			consulta ="";
		}
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class);
		criteria.add(Restrictions.like("nome", "%"+consulta.trim()+"%").ignoreCase());
		return criteria.list();
	}

}
