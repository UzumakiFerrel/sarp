package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.TripulacaoDAO;
import br.com.sarp.model.entidades.Tripulacao;

@Service
public class TripulacaoService {
	
	@Inject
	private TripulacaoDAO tripulacaoDao;
	
	
	public void salvar(Tripulacao tripulacao) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(tripulacao.getRebocador().getCd_rebocador()==null){
			throw new ServiceException("Tripulacao tem que ter Rebocador cadastrado!");
		} 
		
		try {
			tripulacaoDao.salvar(tripulacao);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
	}

	public List<Tripulacao> buscaTodos(){
		
		List<Tripulacao> lista= tripulacaoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Tripulacao tripulacao) {
		//validacao a exclusao
		tripulacaoDao.excluir(tripulacao);
		
	}

	public Tripulacao buscarPorId(Integer intid) {
		// TODO Auto-generated method stub
		return tripulacaoDao.buscarPorId(intid);
	}
	

}
