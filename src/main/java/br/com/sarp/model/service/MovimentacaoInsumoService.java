package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.MovimentacaoInsumoDAO;
import br.com.sarp.model.entidades.CadastroInsumo;
import br.com.sarp.model.entidades.MovimentacaoInsumo;

@Service
public class MovimentacaoInsumoService {
	
	@Inject
	private MovimentacaoInsumoDAO movimentacaoInsumoDao;
	
	
	public void salvar(MovimentacaoInsumo movimentacaoInsumo) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		if(movimentacaoInsumo.getNome()==null || movimentacaoInsumo.getNome()==""){
			throw new ServiceException("Campo nome movimentacaoInsumo nao pode ser vazio!");
		} 
		
		*/
		//validacao da regra exemplo
		
		try {
			movimentacaoInsumoDao.salvar(movimentacaoInsumo);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	
	public List<MovimentacaoInsumo> buscaTodos(){
		
		List<MovimentacaoInsumo> lista= movimentacaoInsumoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(MovimentacaoInsumo movimentacaoInsumo) {
		//validacao a exclusao
		movimentacaoInsumoDao.excluir(movimentacaoInsumo);
		
		
	}

	
	public List<MovimentacaoInsumo> buscarMovimentacaoInsumo(
			CadastroInsumo cadastroInsumo) {
		
		return movimentacaoInsumoDao.buscarMovimentacaoInsumo(cadastroInsumo);
	
	}
	

}
