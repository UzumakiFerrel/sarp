package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.SolicitacoesDAO;
import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.Solicitacoes;

@Service
public class SolicitacoesService {

	@Inject
	private SolicitacoesDAO solicitacoesDao;
	
	public void salvar(Solicitacoes solicitacoes) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		
		if(solicitacoes.getNome()==null || solicitacoes.getNome()==""){
			throw new ServiceException("Campo nome solicitacoes nao pode ser vazio!");
		} 
		
		
		*/
		//validacao da regra exemplo
		
		try {
			solicitacoesDao.salvar(solicitacoes);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<Solicitacoes> buscaTodos(){
		
		List<Solicitacoes> lista= solicitacoesDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Solicitacoes solicitacoes) {
		//validacao a exclusao
		solicitacoesDao.excluir(solicitacoes);
		
	}

	public Solicitacoes buscarId(Integer intid) {
		// TODO Auto-generated method stub
		Solicitacoes usu=new Solicitacoes();
		usu = solicitacoesDao.buscarPorId(intid);
		
		return usu ;
	}

	public List<Solicitacoes> buscarTodoscCusto(CentroCusto cCusto) {
		
		// TODO Auto-generated method stub
		
		
		return solicitacoesDao.buscarTodoscCusto(cCusto);
	}
	

}
