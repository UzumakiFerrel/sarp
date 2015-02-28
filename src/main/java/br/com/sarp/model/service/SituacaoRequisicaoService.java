package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.SituacaoRequisicaoDAO;
import br.com.sarp.model.entidades.SituacaoRequisicao;

@Service
public class SituacaoRequisicaoService {
	
	@Inject
	private SituacaoRequisicaoDAO situacaoRequisicaoDao;
	
	
	public void salvar(SituacaoRequisicao situacaoRequisicao) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		
		try {
			situacaoRequisicaoDao.salvar(situacaoRequisicao);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public SituacaoRequisicao buscarId(Integer id){
		//validacao das regras de negocio
		
		return situacaoRequisicaoDao.buscarPorId(id);
		
	}
	
	public List<SituacaoRequisicao> buscaTodos(){
		
		List<SituacaoRequisicao> lista= situacaoRequisicaoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(SituacaoRequisicao situacaoRequisicao) {
		//validacao a exclusao
		situacaoRequisicaoDao.excluir(situacaoRequisicao);
		
	}
	

}
