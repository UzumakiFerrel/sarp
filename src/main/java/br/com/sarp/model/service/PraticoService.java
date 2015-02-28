package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.PraticoDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Pratico;

@Service
public class PraticoService {
	
	@Inject
	private PraticoDAO praticoDao;
	
	
	public void salvar(Pratico pratico) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a pratico,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(pratico.getNome()==null || pratico.getNome()==""){
			throw new ServiceException("Campo nome pratico nao pode ser vazio!");
		} 
		
		try {
			praticoDao.salvar(pratico);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	
	public Pratico buscarId(int id){
		//validacao das regras de negocio
		
		return praticoDao.buscarPorId(id);
		
	}
	
	public List<Pratico> buscaTodos(){
		
		List<Pratico> lista= praticoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Pratico pratico) {
		//validacao a exclusao
		praticoDao.excluir(pratico);
		
		
	}


	public List<Pratico> buscaTodosBase(Base base) {
	
		return praticoDao.buscarTodosBase(base);
	}

	

}
