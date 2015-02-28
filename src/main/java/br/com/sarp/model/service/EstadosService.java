package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.EstadosDAO;
import br.com.sarp.model.entidades.Estados;

@Service
public class EstadosService {
	
	@Inject
	private EstadosDAO estadosDao;
	
	
	public void salvar(Estados estados) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(estados.getNome()==null || estados.getNome()==""){
			throw new ServiceException("Campo nome estados nao pode ser vazio!");
		} 
		
		try {
			estadosDao.salvar(estados);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	
	public Estados buscarId(Integer l){
		//validacao das regras de negocio
		
		return estadosDao.buscarPorId(l);
		
	}
	
	
	
	public List<Estados> buscaTodos(){
		
		List<Estados> lista= estadosDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Estados estados) {
		//validacao a exclusao
		estadosDao.excluir(estados);
		
		
	}
	

}
