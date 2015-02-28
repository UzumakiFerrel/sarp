package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.AplicacaoServicosDAO;
import br.com.sarp.model.entidades.AplicacaoServicos;

@Service
public class AplicacaoServicosService {
	
	@Inject
	private AplicacaoServicosDAO aplicacaoServicosDao;
	
	
	public void salvar(AplicacaoServicos aplicacaoServicos) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		if(aplicacaoServicos.getNome()==null || aplicacaoServicos.getNome()==""){
			throw new ServiceException("Campo nome aplicacaoServicos nao pode ser vazio!");
		} 
		
		*/
		//validacao da regra exemplo
		
		try {
			aplicacaoServicosDao.salvar(aplicacaoServicos);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<AplicacaoServicos> buscaTodos(){
		
		List<AplicacaoServicos> lista= aplicacaoServicosDao.buscarTodos();
		
		return lista;
		
	}

	public void excluir(AplicacaoServicos aplicacaoServicos) {
		//validacao a exclusao
		aplicacaoServicosDao.excluir(aplicacaoServicos);
		
		
	}

	public AplicacaoServicos buscarId(Integer intid) {
		// TODO Auto-generated method stub
		return aplicacaoServicosDao.buscarPorId(intid);
	}
	

}
