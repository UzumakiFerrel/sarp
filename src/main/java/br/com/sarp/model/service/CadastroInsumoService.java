package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.CadastroInsumoDAO;
import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.CadastroInsumo;
import br.com.sarp.model.entidades.MovimentacaoInsumo;

@Service
public class CadastroInsumoService {

	@Inject
	private CadastroInsumoDAO cadastroInsumoDao;
	@Inject
	private MovimentacaoInsumoService movimentacaoInsumoService;

	private List<MovimentacaoInsumo> movimentacaoLista;

	public void salvar(CadastroInsumo cadastroInsumo) throws ServiceException {

		/*
		 * validacao das regras de negocio a base,setor,funcao,situacao devem
		 * existir no cadastro nenhum campo pode ser vazio *
		 */
		// validacao da regra exemplo
	
		if (cadastroInsumo.getDescricao() == "") {
			throw new ServiceException(
					"Campo nome cadastroInsumo nao pode ser vazio!");
		}

		try {
			cadastroInsumoDao.salvar(cadastroInsumo);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !", causa);

		}

	}

	public List<CadastroInsumo> buscaTodos() {

		List<CadastroInsumo> lista = cadastroInsumoDao.buscarTodos();
		return lista;

	}

	public void excluir(CadastroInsumo cadastroInsumo) throws ServiceException,
			PSQLException {

		movimentacaoLista = movimentacaoInsumoService
				.buscarMovimentacaoInsumo(cadastroInsumo);

		if (movimentacaoLista.isEmpty()) { //so exclui o item se nao existir movimentacao
			
			try {
				cadastroInsumoDao.excluir(cadastroInsumo);

			} catch (DAOException causa) {
				throw new ServiceException("Nao e possivel Excluir ", causa);

			}

		}
		else{
			throw new ServiceException("Nao e possivel Excluir , Item com Movimentacao!!! ");

		} 

	}

	public CadastroInsumo buscarId(int parseInt) {

		return cadastroInsumoDao.buscarPorId(parseInt);
	}

	public List<CadastroInsumo> buscaQueryDescricao(String query) {

		// TODO Auto-generated method stub
		return cadastroInsumoDao.buscaQueryDescricao(query);
	}

	public List<MovimentacaoInsumo> getMovimentacaoLista() {
		return movimentacaoLista;
	}

	public void setMovimentacaoLista(List<MovimentacaoInsumo> movimentacaoLista) {
		this.movimentacaoLista = movimentacaoLista;
	}

}
