package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.PassagemComando;
import br.com.sarp.model.entidades.Rebocador;

@Repository
public class PassagemComandoDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(PassagemComando passagemComando) throws DAOException {

		try {
			em.merge(passagemComando);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
		}

	}

	@Transactional
	public void excluir(PassagemComando passagemComando) {

		try {
			PassagemComando passagemComandoexc = buscarPorId(passagemComando
					.getCd_passagemComando());
			em.remove(passagemComandoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<PassagemComando> buscarTodos() {
		Query consulta = em.createQuery("select u from PassagemComando u");

		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<PassagemComando> buscarTodosBase(Base base) {
		Query consulta = em
				.createQuery("select c from PassagemComando c where c.base=:base");
		consulta.setParameter("base", base);
		return consulta.getResultList();
	}

	@Transactional
	public PassagemComando buscarPorId(int id) {

		return em.find(PassagemComando.class, id);
	}

	@Transactional
	public PassagemComando buscarPassagemComandoRebocador(Rebocador rebocador) {
		Integer situacao = 1;
		PassagemComando pc = null;

		try {

			Query consulta = em
					.createQuery("select c from PassagemComando c where c.rebocador=:rebocador and c.stPassagemComando.cd_sitrm=:situacao");
			consulta.setParameter("rebocador", rebocador);
			consulta.setParameter("situacao", situacao);
			pc = (PassagemComando) consulta.getSingleResult();

		} catch (Exception erro) {
			System.out
					.println("Erro passagem de comando pendente (passagemComandoDAO) "
							+ erro.getMessage());
			return null;
		}

		return pc;

	}

}
