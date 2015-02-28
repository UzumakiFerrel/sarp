package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.RebocadorDAO;
import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Rebocador;

@Service
public class RebocadorService {
	
	@Inject
	private RebocadorDAO rebocadorDao;
	
	
	public void salvar(Rebocador rebocador) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(rebocador.getNome()==null || rebocador.getNome()==""){
			throw new ServiceException("Campo nome rebocador nao pode ser vazio!");
		} 
		
		try {
			rebocadorDao.salvar(rebocador);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<Rebocador> buscaTodos(){
		
		List<Rebocador> lista= rebocadorDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Rebocador rebocador) {
		//validacao a exclusao
		rebocadorDao.excluir(rebocador);
		
		
	}

	public Rebocador buscarId(Integer intid) {
		
		return rebocadorDao.buscarPorId(intid);
		
		
	}

	public List<Rebocador> buscarTodosBase(Base base) {
		// TODO Auto-generated method stub
		return rebocadorDao.buscarTodosBase(base);
	}


}
