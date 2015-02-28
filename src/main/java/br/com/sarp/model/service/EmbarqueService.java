package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.EmbarqueDAO;
import br.com.sarp.model.dao.HistoricoEmbarqueDAO;
import br.com.sarp.model.dao.UsuarioDAO;
import br.com.sarp.model.entidades.Embarque;
import br.com.sarp.model.entidades.HistoricoEmbarque;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;

@Service
public class EmbarqueService {

	@Inject
	private UsuarioDAO usuarioDao;
	
	@Inject
	private EmbarqueDAO embarqueDao;
	@Inject
	private HistoricoEmbarqueDAO historicoEmbarqueDao;

	public void salvar(Embarque embarque) throws ServiceException {

		/*
		 * validacao das regras de negocio a base,setor,funcao,situacao devem
		 * existir no cadastro nenhum campo pode ser vazio *
		 */
		// validacao da regra exemplo
		
		if ((embarque.getUsuario() == null)
				&& (embarque.getPrestadorServico().getNome() == null || embarque.getPrestadorServico().getNome() == "")) {
			throw new ServiceException(
					"Informar Tripulante ou Visitante para Embarque!");
		}
/*		
		if ((embarque.getUsuario() != null)
				&& (embarque.getPrestadorServico().getNome() != null && embarque.getPrestadorServico().getNome() != "")) {
			throw new ServiceException(
					"Informar Tripulante ou Visitante Apenas!");
		}
*/
		if ((embarque.getLocalembarque() == null)
				|| (embarque.getHrembarque() == null || embarque
						.getHrembarque() == "")
				|| (embarque.getEmbarque() == null || embarque.getEmbarque() == "")) {
			throw new ServiceException("Informa Embarque , Data e Hora!");

		}

		if ((embarque.getLocaldesenbarque()!=null) && (embarque.getHrdesenbarque()!=null) && (embarque.getDesenbarque()!=null)){
			
			//desenbarque usuario
			HistoricoEmbarque historicoEmbarque = new HistoricoEmbarque();
			
			historicoEmbarque.setDesenbarque(embarque.getDesenbarque());
			historicoEmbarque.setPrestadorServico(embarque.getPrestadorServico());
			historicoEmbarque.setEmbarque(embarque.getEmbarque());
			historicoEmbarque.setHrdesenbarque(embarque.getHrdesenbarque());
			historicoEmbarque.setHrembarque(embarque.getHrembarque());
			historicoEmbarque.setLocaldesenbarque(embarque.getLocaldesenbarque());
			historicoEmbarque.setLocalembarque(embarque.getLocalembarque());
			historicoEmbarque.setRebocador(embarque.getRebocador());
			historicoEmbarque.setUsuario(embarque.getUsuario());
			 
			//tira o acesso do usuario ao sistema no desenbarque
			
			try{
				embarque.getUsuario().setAcesso("NAO");
				historicoEmbarqueDao.salvar(historicoEmbarque);
				usuarioDao.salvar(embarque.getUsuario());
				embarqueDao.excluir(embarque);
			}catch(DAOException | DataIntegrityViolationException | PSQLException causa){
				throw new ServiceException("Nao foi possivel Excluir Embarque !", causa);
				
			}
		
			
		} else {
			
			try {
				embarque.getUsuario().setAcesso("SIM");
				usuarioDao.salvar(embarque.getUsuario()); //habilita o acesso do usuario ao sistema
				embarqueDao.salvar(embarque);
			} catch (DAOException | DataIntegrityViolationException | PSQLException causa) {
				throw new ServiceException("Verificar Dados ou Usuario ja Embarcado!", causa);

		}
		}
	}

	
	public List<Embarque> buscaTodos() {
		List<Embarque> lista = embarqueDao.buscarTodos();
		return lista;
	}

/*	
	public void excluir(Embarque embarque) {
		// validacao a exclusao
		try {
			embarqueDao.excluir(embarque);
		} catch (DAOException | DataIntegrityViolationException | PSQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

*/
	public Embarque buscarId(Integer intid) {
		return embarqueDao.buscarPorId(intid);
	}

	public List<Embarque> buscaTodosRebocador(Rebocador rbd) {

		List<Embarque> lista = embarqueDao.buscarTodosRebocador(rbd);

		return lista;
	}

	public EmbarqueDAO getEmbarqueDao() {
		return embarqueDao;
	}

	public void setEmbarqueDao(EmbarqueDAO embarqueDao) {
		this.embarqueDao = embarqueDao;
	}

	public Rebocador buscarRebocadorEmbarque(Usuario usuario) {
	
		return embarqueDao.buscarRebocadorEmbarque(usuario);
	}


	public Tripulante buscarCmteEmbarcado(Rebocador rebocador) throws DAOException {
		// TODO Auto-generated method stub
	
		return embarqueDao.buscarCmteEmbarcado(rebocador);
	}


	public Embarque buscarEmbarqueTripulante(Tripulante cmte) {
		//Retorna embarque por tripulante
		
		return embarqueDao.buscarEmbarqueTripulante(cmte);
	}



}
