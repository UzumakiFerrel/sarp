package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.TipoEmbarcacoesDAO;
import br.com.sarp.model.entidades.TipoEmbarcacoes;

@Service
public class TipoEmbarcacoesService {
	
	@Inject
	private TipoEmbarcacoesDAO tipoEmbarcacoesDao;
	
	
	public void salvar(TipoEmbarcacoes tipoEmbarcacoes) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		if(tipoEmbarcacoes.getNome()==null || tipoEmbarcacoes.getNome()==""){
			throw new ServiceException("Campo nome tipoEmbarcacoes nao pode ser vazio!");
		} 
		
		*/
		//validacao da regra exemplo
		
		try {
			tipoEmbarcacoesDao.salvar(tipoEmbarcacoes);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<TipoEmbarcacoes> buscaTodos(){
		
		List<TipoEmbarcacoes> lista= tipoEmbarcacoesDao.buscarTodos();
		return lista;
		
	}

	public void excluir(TipoEmbarcacoes tipoEmbarcacoes) {
		//validacao a exclusao
		tipoEmbarcacoesDao.excluir(tipoEmbarcacoes);
		
		
	}

	public TipoEmbarcacoes buscarId(Integer intid) {
		// TODO Auto-generated method stub
		TipoEmbarcacoes usu=new TipoEmbarcacoes();
		usu = tipoEmbarcacoesDao.buscarPorId(intid);
		
		return usu ;
	}
	

}
