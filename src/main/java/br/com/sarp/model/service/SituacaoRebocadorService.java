package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.SituacaoRebocadorDAO;
import br.com.sarp.model.entidades.SituacaoRebocador;

@Service
public class SituacaoRebocadorService {
	
	@Inject
	private SituacaoRebocadorDAO situacaoRebocadorDao;
	
	
	public void salvar(SituacaoRebocador situacaoRebocador) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(situacaoRebocador.getNome()==null || situacaoRebocador.getNome()==""){
			throw new ServiceException("Campo nome situacaoRebocador nao pode ser vazio!");
		} 
		
		try {
			situacaoRebocadorDao.salvar(situacaoRebocador);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public SituacaoRebocador buscarId(Integer id){
		//validacao das regras de negocio
		
		return situacaoRebocadorDao.buscarPorId(id);
		
	}
	
	public List<SituacaoRebocador> buscaTodos(){
		
		List<SituacaoRebocador> lista= situacaoRebocadorDao.buscarTodos();
		return lista;
		
	}

	public void excluir(SituacaoRebocador situacaoRebocador) {
		//validacao a exclusao
		situacaoRebocadorDao.excluir(situacaoRebocador);
		
	}
	

}
