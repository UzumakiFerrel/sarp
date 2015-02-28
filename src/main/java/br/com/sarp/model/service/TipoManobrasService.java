package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.TipoManobrasDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.TipoManobras;

@Service
public class TipoManobrasService {
	
	@Inject
	private TipoManobrasDAO tipoManobrasDao;
	
	
	public void salvar(TipoManobras tipoManobras) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a tipoManobras,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(tipoManobras.getNome()==null || tipoManobras.getNome()==""){
			throw new ServiceException("Campo nome tipoManobras nao pode ser vazio!");
		} 
		
		try {
			tipoManobrasDao.salvar(tipoManobras);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	
	public TipoManobras buscarId(int id){
		//validacao das regras de negocio
		
		return tipoManobrasDao.buscarPorId(id);
		
	}
	
	public List<TipoManobras> buscaTodos(){
		
		List<TipoManobras> lista= tipoManobrasDao.buscarTodos();
		return lista;
		
	}

	public void excluir(TipoManobras tipoManobras) {
		//validacao a exclusao
		tipoManobrasDao.excluir(tipoManobras);
		
		
	}

	

}
