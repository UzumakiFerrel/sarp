package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.PrestadorServicoDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.PrestadorServico;
import br.com.sarp.model.entidades.Estados;

@Service
public class PrestadorServicoService {
	
	@Inject
	private PrestadorServicoDAO prestadorServicoDao;
	
	
	public void salvar(PrestadorServico prestadorServico) throws ServiceException{
		
		/*validacao das regras de negocio
		 * a base,setor,funcao,situacao devem existir no cadastro
		 * nenhum campo pode ser vazio		 * 
		*/
		//validacao da regra exemplo
		if(prestadorServico.getNome()==null || prestadorServico.getNome()==""){
			throw new ServiceException("Campo nome prestadorServico nao pode ser vazio!");
		} 
		
		try {
			prestadorServicoDao.salvar(prestadorServico);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !",causa);
		
		}
		
	}


	public PrestadorServico buscarId(int id){
		//validacao das regras de negocio
		
		return prestadorServicoDao.buscarPorId(id);
		
	}
	
	
	public List<PrestadorServico> buscaTodos(){
		
		List<PrestadorServico> lista= prestadorServicoDao.buscarTodos();
		return lista;
		
	}

	public void excluir(PrestadorServico prestadorServico) {
		//validacao a exclusao
		prestadorServicoDao.excluir(prestadorServico);
		
		
	}
	
	public List<PrestadorServico> buscarPrestadorServico(Estados estado) {
		
		return prestadorServicoDao.buscarPrestadorServico(estado);
	}


	public PrestadorServico salvarReturnId(PrestadorServico prs) {
		
		
		return prestadorServicoDao.salvarReturnId(prs);
	}

	
	
}
