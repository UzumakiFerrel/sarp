package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.ArmadorDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.Armador;

@Service
public class ArmadorService {
	
	@Inject
	private ArmadorDAO armadorDao;
	
	
	public void salvar(Armador armador) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a armador,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(armador.getNome()==null || armador.getNome()==""){
			throw new ServiceException("Campo nome armador nao pode ser vazio!");
		} 
		
		try {
			armadorDao.salvar(armador);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	
	public Armador buscarId(int id){
		//validacao das regras de negocio
		
		return armadorDao.buscarPorId(id);
		
	}
	
	public List<Armador> buscaTodos(){
		
		List<Armador> lista= armadorDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Armador armador) {
		//validacao a exclusao
		armadorDao.excluir(armador);
		
		
	}

	

}
