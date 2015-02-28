package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.PropulsaoDAO;
import br.com.sarp.model.entidades.Propulsao;

@Service
public class PropulsaoService {
	
	@Inject
	private PropulsaoDAO propulsaoDao;
	
	
	public void salvar(Propulsao propulsao) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(propulsao.getNome()==null || propulsao.getNome()==""){
			throw new ServiceException("Campo nome propulsao nao pode ser vazio!");
		} 
		
		try {
			propulsaoDao.salvar(propulsao);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<Propulsao> buscaTodos(){
		
		List<Propulsao> lista= propulsaoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Propulsao propulsao) {
		//validacao a exclusao
		propulsaoDao.excluir(propulsao);
		
		
	}

	public Propulsao buscarId(Integer intid) {
		// TODO Auto-generated method stub
		
		return propulsaoDao.buscarPorId(intid);
	}
	

}
