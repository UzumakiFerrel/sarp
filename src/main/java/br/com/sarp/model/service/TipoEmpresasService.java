package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.TipoEmpresasDAO;
import br.com.sarp.model.entidades.TipoEmpresas;

@Service
public class TipoEmpresasService {
	
	@Inject
	private TipoEmpresasDAO tipoEmpresasDao;
	
	
	public void salvar(TipoEmpresas tipoEmpresas) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(tipoEmpresas.getNome()==null || tipoEmpresas.getNome()==""){
			throw new ServiceException("Campo nome tipoEmpresas nao pode ser vazio!");
		} 
		
		try {
			tipoEmpresasDao.salvar(tipoEmpresas);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<TipoEmpresas> buscaTodos(){
		
		List<TipoEmpresas> lista= tipoEmpresasDao.buscarTodos();
		return lista;
		
	}

	public void excluir(TipoEmpresas tipoEmpresas) {
		//validacao a exclusao
		tipoEmpresasDao.excluir(tipoEmpresas);
		
		
	}

	public TipoEmpresas buscarId(Integer intid) {
		// TODO Auto-generated method stub
		TipoEmpresas usu=new TipoEmpresas();
		usu = tipoEmpresasDao.buscarPorId(intid);
		
		return usu ;
	}
	

}
