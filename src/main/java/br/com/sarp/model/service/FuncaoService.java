package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.FuncaoDAO;
import br.com.sarp.model.entidades.Funcao;

@Service
public class FuncaoService {
	
	@Inject
	private FuncaoDAO funcaoDao;
	
	
	public void salvar(Funcao funcao) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(funcao.getNome()==null || funcao.getNome()==""){
			throw new ServiceException("Campo nome funcao nao pode ser vazio!");
		} 
		
		try {
			funcaoDao.salvar(funcao);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	
	public Funcao buscarId(int id){
		//validacao das regras de negocio
		
		return funcaoDao.buscarPorId(id);
		
	}
	
	
	public List<Funcao> buscaTodos(){
		
		List<Funcao> lista= funcaoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Funcao funcao) {
		//validacao a exclusao
		funcaoDao.excluir(funcao);
		
		
	}
	

}
