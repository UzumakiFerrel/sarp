package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.CentroCustoDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.Cidades;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Setor;

@Service
public class CentroCustoService {

	@Inject
	private CentroCustoDAO centroCustoDao;
	private List<CentroCusto> cCustoList;

	public void salvar(CentroCusto centroCusto) throws ServiceException {

		/*
		 * validacao das regras de negocio a centroCusto,setor,funcao,situacao
		 * devem existir no cadastro nenhum campo pode ser vazio *
		 * if(centroCusto.getCd_custo()==null){ throw new
		 * ServiceException("Campo nome centroCusto nao pode ser vazio!"); }
		 */
		// validacao dos centros de custo
		Boolean cadastrar = true;
		cCustoList = centroCustoDao.buscarTodos();
		for (CentroCusto cCusto : cCustoList) {
			if ((centroCusto.getBase() == cCusto.getBase())
					&& (centroCusto.getSetor() == cCusto.getSetor())
					&& (centroCusto.getRebocador() == cCusto.getRebocador())) {
				cadastrar = false;
			}
		}

		if (cadastrar == false) {
			throw new ServiceException("Centro de Custo ja Cadastrado !");
		} else {
			try {
				centroCustoDao.salvar(centroCusto);
			} catch (DAOException causa) {
				throw new ServiceException("Nao foi possivel salvar !", causa);
			}

		}

	}

	
	public CentroCusto buscarId(int id) {
		// validacao das regras de negocio

		return centroCustoDao.buscarPorId(id);

	}

	public List<CentroCusto> buscaTodos() {

		List<CentroCusto> lista = centroCustoDao.buscarTodos();
		return lista;

	}

	public void excluir(CentroCusto centroCusto) {
		// validacao a exclusao
		centroCustoDao.excluir(centroCusto);

	}

	public List<CentroCusto> buscarCentroCusto(Cidades cidade) {

		return centroCustoDao.buscarCentroCusto(cidade);

	}

	public CentroCusto buscarRebocador(Rebocador rebocador) {

		return centroCustoDao.buscarRebocador(rebocador);
	}

	public CentroCusto buscarSetor(Setor setor, Base base) {

		return centroCustoDao.buscarSetor(setor , base);
	}


	public CentroCusto buscarRebocadorII(Integer cd_rebocador) {
		// TODO Auto-generated method stub
		return centroCustoDao.buscarRebocadorII(cd_rebocador);
	}

}
