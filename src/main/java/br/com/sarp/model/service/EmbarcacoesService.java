package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.EmbarcacoesDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.Embarcacoes;

@Service
public class EmbarcacoesService {
	
	@Inject
	private EmbarcacoesDAO embarcacoesDao;
	
	
	public void salvar(Embarcacoes embarcacoes) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a embarcacoes,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		if(embarcacoes.getNome()==null || embarcacoes.getNome()==""){
			throw new ServiceException("Campo nome embarcacoes nao pode ser vazio!");
		} 

		*/
		//validacao da regra exemplo
		
		try {
			embarcacoesDao.salvar(embarcacoes);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	
	public Embarcacoes buscarId(int id){
		//validacao das regras de negocio
		
		return embarcacoesDao.buscarPorId(id);
		
	}
	
	public List<Embarcacoes> buscaTodos(){
		
		List<Embarcacoes> lista= embarcacoesDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Embarcacoes embarcacoes) {
		//validacao a exclusao
		embarcacoesDao.excluir(embarcacoes);
		
		
	}


	public List<Embarcacoes> buscarAutoCompletar(String query) {
		// TODO Auto-generated method stub
		return embarcacoesDao.buscarAutoCompletar(query);
	}

	

}
