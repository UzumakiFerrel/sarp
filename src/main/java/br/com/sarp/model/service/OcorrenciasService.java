package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.OcorrenciasDAO;
import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Ocorrencias;
import br.com.sarp.model.entidades.Rebocador;

@Service
public class OcorrenciasService {

	@Inject
	private OcorrenciasDAO ocorrenciasDao;

	public void salvar(Ocorrencias ocorrencias) throws ServiceException {

		/*
		 * validacao das regras de negocio a base,setor,funcao,situacao devem
		 * existir no cadastro nenhum campo pode ser vazio *
		 */
		// validacao da regra exemplo

		if (ocorrencias.getDescricao() == null) {
			throw new ServiceException("Campo Descricao nao pode ser vazio!");
		} else if (ocorrencias.getSolicitacao() == null) {
			throw new ServiceException("Verificar Solicitacao !");
		} else if (ocorrencias.getTpocorrencia() == null) {
			throw new ServiceException("Verificar Tipo Ocorrencia !");
		} else {

			try {
				ocorrenciasDao.salvar(ocorrencias);
			} catch (DAOException | DataIntegrityViolationException | PSQLException causa) {
				throw new ServiceException("Ocorrencia nao salva , Informacoes invalidas ou Duplicadas!", causa);

			}

		}
	}

	public List<Ocorrencias> buscaTodos() {

		List<Ocorrencias> lista = ocorrenciasDao.buscarTodos();
		return lista;

	}

	public void excluir(Ocorrencias ocorrencias) {
		// validacao a exclusao
		ocorrenciasDao.excluir(ocorrencias);

	}

	public List<Ocorrencias> buscarTodosBase(Base base) {

		// validacao da pesquisa
		List<Ocorrencias> lista = ocorrenciasDao.buscarTodosBase(base);
		return lista;
	}

	public Ocorrencias buscarId(Integer intid) {
		// TODO Auto-generated method stub
		Ocorrencias usu = new Ocorrencias();
		usu = ocorrenciasDao.buscarPorId(intid);

		return usu;
	}

	public List<Ocorrencias> buscarApenasOcorrenciasRebocador(
			Rebocador rebocador) {

		List<Ocorrencias> lista = ocorrenciasDao
				.buscarOcorrenciasRebocador(rebocador);

		return lista;
	}

	public List<Ocorrencias> buscaPendentes(Rebocador rebocador) {

		List<Ocorrencias> lista = ocorrenciasDao.buscaPendentes(rebocador);

		return lista;
	}

}
