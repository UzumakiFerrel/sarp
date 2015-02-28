package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.EmpresasDAO;
import br.com.sarp.model.entidades.Empresas;

@Service
public class EmpresasService {
	
	@Inject
	private EmpresasDAO empresasDao;
	
	
	public void salvar(Empresas empresas) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(empresas.getNome()==null || empresas.getNome()==""){
			throw new ServiceException("Campo nome empresas nao pode ser vazio!");
		} 
		
		try {
			empresasDao.salvar(empresas);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}

	public List<Empresas> buscaTodos(){
		
		List<Empresas> lista= empresasDao.buscarTodos();
		return lista;
		
	}

	public void excluir(Empresas empresas) {
		//validacao a exclusao
		empresasDao.excluir(empresas);
		
		
	}

	public Empresas buscarId(Integer intid) {
		
		return empresasDao.buscarPorId(intid);
	}
	

}
