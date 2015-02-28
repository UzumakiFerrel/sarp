package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.PrioridadesDAO;
import br.com.sarp.model.entidades.Prioridades;

@Service
public class PrioridadesService {
	
	@Inject
	private PrioridadesDAO prioridadesDao;
	
	
	public void salvar(Prioridades prioridades) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(prioridades.getNome()==null || prioridades.getNome()==""){
			throw new ServiceException("Campo nome prioridades nao pode ser vazio!");
		} 
		
		try {
			prioridadesDao.salvar(prioridades);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<Prioridades> buscaTodos(){
		
		List<Prioridades> lista= prioridadesDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Prioridades prioridades) {
		//validacao a exclusao
		prioridadesDao.excluir(prioridades);
		
		
	}

	public Prioridades buscarId(Integer intid) {
		// TODO Auto-generated method stub
		Prioridades usu=new Prioridades();
		usu = prioridadesDao.buscarPorId(intid);
		
		return usu ;
	}
	

}
