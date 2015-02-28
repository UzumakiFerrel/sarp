package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.SetorDAO;
import br.com.sarp.model.entidades.Setor;

@Service
public class SetorService {
	
	@Inject
	private SetorDAO setorDao;
	
	
	public void salvar(Setor setor) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(setor.getNome()==null || setor.getNome()==""){
			throw new ServiceException("Campo nome setor nao pode ser vazio!");
		} 
		
		try {
			setorDao.salvar(setor);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public Setor buscarId(int id){
		//validacao das regras de negocio
		
		return setorDao.buscarPorId(id);
		
	}
	
	
	public List<Setor> buscaTodos(){
		
		List<Setor> lista= setorDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Setor setor) {
		//validacao a exclusao
		setorDao.excluir(setor);
		
		
	}
	

}
