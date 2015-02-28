package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.CategoriaMaritimoDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.CategoriaMaritimo;

@Service
public class CategoriaMaritimoService {
	
	@Inject
	private CategoriaMaritimoDAO categoriaMaritimoDao;
	
	
	public void salvar(CategoriaMaritimo categoriaMaritimo) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(categoriaMaritimo.getNome()==null || categoriaMaritimo.getNome()==""){
			throw new ServiceException("Campo nome categoriaMaritimo nao pode ser vazio!");
		} 
		
		try {
			categoriaMaritimoDao.salvar(categoriaMaritimo);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}


	public CategoriaMaritimo buscarId(int id){
		//validacao das regras de negocio
		
		return categoriaMaritimoDao.buscarPorId(id);
		
	}
	
	
	public List<CategoriaMaritimo> buscaTodos(){
		
		List<CategoriaMaritimo> lista= categoriaMaritimoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(CategoriaMaritimo categoriaMaritimo) {
		//validacao a exclusao
		categoriaMaritimoDao.excluir(categoriaMaritimo);
		
		
	}
	
	
		
	
}
