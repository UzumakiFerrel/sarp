package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.CidadesDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.Cidades;
import br.com.sarp.model.entidades.Estados;

@Service
public class CidadesService {
	
	@Inject
	private CidadesDAO cidadesDao;
	
	
	public void salvar(Cidades cidades) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(cidades.getNome()==null || cidades.getNome()==""){
			throw new ServiceException("Campo nome cidades nao pode ser vazio!");
		} 
		
		try {
			cidadesDao.salvar(cidades);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}


	public Cidades buscarId(int id){
		//validacao das regras de negocio
		
		return cidadesDao.buscarPorId(id);
		
	}
	
	
	public List<Cidades> buscaTodos(){
		
		List<Cidades> lista= cidadesDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Cidades cidades) {
		//validacao a exclusao
		cidadesDao.excluir(cidades);
		
		
	}
	
	public List<Cidades> buscarCidades(Estados estado) {
		
		return cidadesDao.buscarCidades(estado);
	}

	
	
}
