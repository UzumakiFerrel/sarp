package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.ItemRequisicaoDAO;
import br.com.sarp.model.entidades.ItemRequisicao;

@Service
public class ItemRequisicaoService {
	
	@Inject
	private ItemRequisicaoDAO itemRequisicaoDao;
	
	
	public void salvar(ItemRequisicao itemRequisicao) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		
		try {
			itemRequisicaoDao.salvar(itemRequisicao);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<ItemRequisicao> buscaTodos(){
		
		List<ItemRequisicao> lista= itemRequisicaoDao.buscarTodos();
		return lista;
		
	}

	/*
	public void excluir(ItemRequisicao itemRequisicao) {
		//validacao a exclusao
		itemRequisicaoDao.excluir(itemRequisicao);
		
	}
*/
	
	public ItemRequisicao buscarId(Integer intid) {
		
		return itemRequisicaoDao.buscarPorId(intid);
	}

	public List<ItemRequisicao> buscarItensSituacao(Integer numreq,
			Integer situacao) {
	
		return itemRequisicaoDao.buscarItensSituacao(numreq,situacao);

	}
	

}
