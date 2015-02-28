package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.FuncaoMaritimoDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.FuncaoMaritimo;

@Service
public class FuncaoMaritimoService {
	
	@Inject
	private FuncaoMaritimoDAO funcaoMaritimoDao;
	
	
	public void salvar(FuncaoMaritimo funcaoMaritimo) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(funcaoMaritimo.getNome()==null || funcaoMaritimo.getNome()==""){
			throw new ServiceException("Campo nome funcaoMaritimo nao pode ser vazio!");
		} 
		
		try {
			funcaoMaritimoDao.salvar(funcaoMaritimo);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}


	public FuncaoMaritimo buscarId(int id){
		//validacao das regras de negocio
		
		return funcaoMaritimoDao.buscarPorId(id);
		
	}
	
	
	public List<FuncaoMaritimo> buscaTodos(){
		
		List<FuncaoMaritimo> lista= funcaoMaritimoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(FuncaoMaritimo funcaoMaritimo) {
		//validacao a exclusao
		funcaoMaritimoDao.excluir(funcaoMaritimo);
		
		
	}
	
	
		
	
}
