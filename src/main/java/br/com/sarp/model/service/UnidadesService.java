package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.UnidadesDAO;
import br.com.sarp.model.entidades.Unidades;

@Service
public class UnidadesService {
	
	@Inject
	private UnidadesDAO unidadesDao;
	
	
	public void salvar(Unidades unidades) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(unidades.getNome()==null || unidades.getNome()==""){
			throw new ServiceException("Campo nome unidades nao pode ser vazio!");
		} 
		
		try {
			unidadesDao.salvar(unidades);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<Unidades> buscaTodos(){
		
		List<Unidades> lista= unidadesDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Unidades unidades) {
		//validacao a exclusao
		unidadesDao.excluir(unidades);
		
		
	}

	public Unidades buscarId(Integer intid) {
		// TODO Auto-generated method stub
		Unidades usu=new Unidades();
		usu = unidadesDao.buscarPorId(intid);
		
		return usu ;
	}
	

}
