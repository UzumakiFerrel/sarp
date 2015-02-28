package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.LocaisDAO;
import br.com.sarp.model.entidades.Locais;

@Service
public class LocaisService {
	
	@Inject
	private LocaisDAO locaisDao;
	
	
	public void salvar(Locais locais) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(locais.getNome()==null || locais.getNome()==""){
			throw new ServiceException("Campo nome locais nao pode ser vazio!");
		} 
		
		try {
			locaisDao.salvar(locais);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<Locais> buscaTodos(){
		
		List<Locais> lista= locaisDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Locais locais) {
		//validacao a exclusao
		locaisDao.excluir(locais);
		
	}

	public Locais buscarId(Integer intid) {
		
		return locaisDao.buscarPorId(intid);
	}
	

}
