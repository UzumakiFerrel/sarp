package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.SituacaoFuncionarioDAO;
import br.com.sarp.model.entidades.SituacaoFuncionario;

@Service
public class SituacaoFuncionarioService {
	
	@Inject
	private SituacaoFuncionarioDAO situacaoFuncionarioDao;
	
	
	public void salvar(SituacaoFuncionario situacaoFuncionario) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(situacaoFuncionario.getNome()==null || situacaoFuncionario.getNome()==""){
			throw new ServiceException("Campo nome situacaoFuncionario nao pode ser vazio!");
		} 
		
		try {
			situacaoFuncionarioDao.salvar(situacaoFuncionario);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public SituacaoFuncionario buscarId(int id){
		//validacao das regras de negocio
		
		return situacaoFuncionarioDao.buscarPorId(id);
		
	}
	
	public List<SituacaoFuncionario> buscaTodos(){
		
		List<SituacaoFuncionario> lista= situacaoFuncionarioDao.buscarTodos();
		return lista;
		
	}

	public void excluir(SituacaoFuncionario situacaoFuncionario) {
		//validacao a exclusao
		situacaoFuncionarioDao.excluir(situacaoFuncionario);
		
		
	}
	

}
