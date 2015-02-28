package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.ServicosDAO;
import br.com.sarp.model.entidades.Servicos;

@Service
public class ServicosService {
	
	@Inject
	private ServicosDAO servicosDao;
	
	
	public void salvar(Servicos servicos) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		if(servicos.getNome()==null || servicos.getNome()==""){
			throw new ServiceException("Campo nome servicos nao pode ser vazio!");
		} 
		
		*/
		
		//validacao da regra exemplo
		
		try {
			servicosDao.salvar(servicos);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<Servicos> buscaTodos(){
		
		List<Servicos> lista= servicosDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Servicos servicos) throws DAOException {
		//validacao a exclusao
		servicosDao.excluir(servicos);
		
		
	}

	public Servicos buscarId(Integer id) {

		
		return servicosDao.buscarPorId(id);
	}

	public List<Servicos> buscaQueryDescricao(String query) {
		// TODO Auto-generated method stub
		return servicosDao.buscaQueryDescricao(query);
	}
	
	

}
