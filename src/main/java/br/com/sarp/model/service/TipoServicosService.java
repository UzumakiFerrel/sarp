package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.TipoServicosDAO;
import br.com.sarp.model.entidades.TipoServicos;

@Service
public class TipoServicosService {
	
	@Inject
	private TipoServicosDAO tipoServicosDao;
	
	
	public void salvar(TipoServicos tipoServicos) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(tipoServicos.getNome()==null || tipoServicos.getNome()==""){
			throw new ServiceException("Campo nome tipoServicos nao pode ser vazio!");
		} 
		
		try {
			tipoServicosDao.salvar(tipoServicos);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<TipoServicos> buscaTodos(){
		
		List<TipoServicos> lista= tipoServicosDao.buscarTodos();
		return lista;
		
	}

	public void excluir(TipoServicos tipoServicos) {
		//validacao a exclusao
		tipoServicosDao.excluir(tipoServicos);
		
		
	}

	public TipoServicos buscarId(Integer intid) {
		// TODO Auto-generated method stub
		TipoServicos usu=new TipoServicos();
		usu = tipoServicosDao.buscarPorId(intid);
		
		return usu ;
	}
	

}
