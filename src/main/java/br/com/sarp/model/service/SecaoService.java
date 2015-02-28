package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.SecaoDAO;
import br.com.sarp.model.entidades.Secao;

@Service
public class SecaoService {
	
	@Inject
	private SecaoDAO secaoDao;
	
	
	public void salvar(Secao secao) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(secao.getNome()==null || secao.getNome()==""){
			throw new ServiceException("Campo nome secao nao pode ser vazio!");
		} 
		
		try {
			secaoDao.salvar(secao);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public Secao buscarId(int id){
		//validacao das regras de negocio
		
		return secaoDao.buscarPorId(id);
		
	}
	
	public List<Secao> buscaTodos(){
		
		List<Secao> lista= secaoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Secao secao) {
		//validacao a exclusao
		secaoDao.excluir(secao);
		
		
	}
	

}
