package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.TipoMoveDAO;
import br.com.sarp.model.entidades.TipoMove;

@Service
public class TipoMoveService {
	
	@Inject
	private TipoMoveDAO tipoMoveDao;
	
	
	public void salvar(TipoMove tipoMove) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(tipoMove.getNome()==null || tipoMove.getNome()==""){
			throw new ServiceException("Campo nome tipoMove nao pode ser vazio!");
		} 
		
		try {
			tipoMoveDao.salvar(tipoMove);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<TipoMove> buscaTodos(){
		
		List<TipoMove> lista= tipoMoveDao.buscarTodos();
		return lista;
		
	}

	public void excluir(TipoMove tipoMove) {
		//validacao a exclusao
		tipoMoveDao.excluir(tipoMove);
		
		
	}

	public TipoMove buscarId(Integer intid) {
		// TODO Auto-generated method stub
		TipoMove usu=new TipoMove();
		usu = tipoMoveDao.buscarPorId(intid);
		
		return usu ;
	}
	

}
