package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.BaseDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.Base;

@Service
public class BaseService {
	
	@Inject
	private BaseDAO baseDao;
	
	
	public void salvar(Base base) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(base.getNome()==null || base.getNome()==""){
			throw new ServiceException("Campo nome base nao pode ser vazio!");
		} 
		
		try {
			baseDao.salvar(base);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public Base buscarId(int id){
		//validacao das regras de negocio
		
		return baseDao.buscarPorId(id);
		
	}
	
	public List<Base> buscaTodos(){
		
		List<Base> lista= baseDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Base base) {
		//validacao a exclusao
		
		baseDao.excluir(base);
		
		
	}
	

}
