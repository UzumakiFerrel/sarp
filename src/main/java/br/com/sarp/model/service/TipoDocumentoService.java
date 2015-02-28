package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.TipoDocumentoDAO;
import br.com.sarp.model.entidades.TipoDocumento;

@Service
public class TipoDocumentoService {
	
	@Inject
	private TipoDocumentoDAO tipoDocumentoDao;
	
	
	public void salvar(TipoDocumento tipoDocumento) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(tipoDocumento.getNome()==null || tipoDocumento.getNome()==""){
			throw new ServiceException("Campo nome tipoDocumento nao pode ser vazio!");
		} 
		
		try {
			tipoDocumentoDao.salvar(tipoDocumento);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<TipoDocumento> buscaTodos(){
		
		List<TipoDocumento> lista= tipoDocumentoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(TipoDocumento tipoDocumento) {
		//validacao a exclusao
		tipoDocumentoDao.excluir(tipoDocumento);
		
		
	}

	public TipoDocumento buscarId(Integer intid) {
		// TODO Auto-generated method stub
		TipoDocumento usu=new TipoDocumento();
		usu = tipoDocumentoDao.buscarPorId(intid);
		
		return usu ;
	}
	

}
