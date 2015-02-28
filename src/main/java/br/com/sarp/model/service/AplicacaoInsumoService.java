package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.AplicacaoInsumoDAO;
import br.com.sarp.model.entidades.AplicacaoInsumo;

@Service
public class AplicacaoInsumoService {
	
	@Inject
	private AplicacaoInsumoDAO aplicacaoInsumoDao;
	
	
	public void salvar(AplicacaoInsumo aplicacaoInsumo) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(aplicacaoInsumo.getNome()==null || aplicacaoInsumo.getNome()==""){
			throw new ServiceException("Campo nome aplicacaoInsumo nao pode ser vazio!");
		} 
		
		try {
			aplicacaoInsumoDao.salvar(aplicacaoInsumo);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<AplicacaoInsumo> buscaTodos(){
		
		List<AplicacaoInsumo> lista= aplicacaoInsumoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(AplicacaoInsumo aplicacaoInsumo) {
		//validacao a exclusao
		aplicacaoInsumoDao.excluir(aplicacaoInsumo);
		
		
	}

	public AplicacaoInsumo buscarId(Integer intid) {
		// TODO Auto-generated method stub
		return aplicacaoInsumoDao.buscarPorId(intid);
	}
	

}
