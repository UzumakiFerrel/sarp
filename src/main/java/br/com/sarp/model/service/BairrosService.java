package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.BairrosDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.Bairros;
import br.com.sarp.model.entidades.Cidades;

@Service
public class BairrosService {
	
	@Inject
	private BairrosDAO bairrosDao;
	
	
	public void salvar(Bairros bairros) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a bairros,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(bairros.getNome()==null || bairros.getNome()==""){
			throw new ServiceException("Campo nome bairros nao pode ser vazio!");
		} 
		
		try {
			bairrosDao.salvar(bairros);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	
	public Bairros buscarId(int id){
		//validacao das regras de negocio
		
		return bairrosDao.buscarPorId(id);
		
	}
	
	public List<Bairros> buscaTodos(){
		
		List<Bairros> lista= bairrosDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Bairros bairros) {
		//validacao a exclusao
		bairrosDao.excluir(bairros);
		
		
	}


	public List<Bairros> buscarBairros(Cidades cidade) {

		return bairrosDao.buscarBairros(cidade);

	}
	

}
