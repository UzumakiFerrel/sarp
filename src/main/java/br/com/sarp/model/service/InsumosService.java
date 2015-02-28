package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.InsumosDAO;
import br.com.sarp.model.entidades.Insumos;

@Service
public class InsumosService {
	
	@Inject
	private InsumosDAO insumosDao;
	
	
	public void salvar(Insumos insumos) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		if(insumos.getNome()==null || insumos.getNome()==""){
			throw new ServiceException("Campo nome insumos nao pode ser vazio!");
		} 
		
		*/
		
		//validacao da regra exemplo
		
		try {
			insumosDao.salvar(insumos);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<Insumos> buscaTodos(){
		
		List<Insumos> lista= insumosDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Insumos insumos) {
		//validacao a exclusao
		insumosDao.excluir(insumos);
		
		
	}
	

}
