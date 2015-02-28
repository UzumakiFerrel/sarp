package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.TipoHistoricoRebocadorDAO;
import br.com.sarp.model.entidades.TipoHistoricoRebocador;

@Service
public class TipoHistoricoRebocadorService {
	
	@Inject
	private TipoHistoricoRebocadorDAO tipoHistoricoRebocadorDao;
	
	
	public void salvar(TipoHistoricoRebocador tipoHistoricoRebocador) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(tipoHistoricoRebocador.getNome()==null || tipoHistoricoRebocador.getNome()==""){
			throw new ServiceException("Campo nome tipoHistoricoRebocador nao pode ser vazio!");
		} 
		
		try {
			tipoHistoricoRebocadorDao.salvar(tipoHistoricoRebocador);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<TipoHistoricoRebocador> buscaTodos(){
		
		List<TipoHistoricoRebocador> lista= tipoHistoricoRebocadorDao.buscarTodos();
		return lista;
		
	}

	public void excluir(TipoHistoricoRebocador tipoHistoricoRebocador) {
		//validacao a exclusao
		tipoHistoricoRebocadorDao.excluir(tipoHistoricoRebocador);
		
		
	}

	public TipoHistoricoRebocador buscarId(Integer intid) {
		// TODO Auto-generated method stub
		TipoHistoricoRebocador usu=new TipoHistoricoRebocador();
		usu = tipoHistoricoRebocadorDao.buscarPorId(intid);
		
		return usu ;
	}
	

}
