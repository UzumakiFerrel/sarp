package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.Cidades;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Setor;

@Repository
public class CentroCustoDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(CentroCusto centroCusto) throws DAOException {

		try {
			em.merge(centroCusto);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(CentroCusto centroCusto) {

		try {
			CentroCusto centroCustoexc = buscarPorId(centroCusto.getCd_custo());
			em.remove(centroCustoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<CentroCusto> buscarTodos() {
		Query consulta = em.createQuery("select b from CentroCusto b");

		return consulta.getResultList();
	}

	@Transactional
	public CentroCusto buscarPorId(int id) {

		return em.find(CentroCusto.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<CentroCusto> buscarCentroCusto(Cidades cidade) {
		Query consulta = em
				.createQuery("select c from CentroCusto c where c.cidade=:est"); // JPQL
		consulta.setParameter("est", cidade);
		return consulta.getResultList();
	}

	@Transactional
	public CentroCusto buscarRebocador(Rebocador rebocador) {

		Query consulta = em
				.createQuery("select c from CentroCusto c where c.rebocador=:rebocador");
		consulta.setParameter("rebocador", rebocador);
		CentroCusto centro = (CentroCusto) consulta.getSingleResult();
		return centro;
	}

	@Transactional
	public CentroCusto buscarSetor(Setor setor, Base base) {
		Rebocador rb = new Rebocador();
		Query consulta = em
				.createQuery("select c from CentroCusto c where c.setor=:setor and c.base=:base and rebocador=:rb");
		consulta.setParameter("setor", setor);
		consulta.setParameter("base", base);
		consulta.setParameter("rb", rb);

		CentroCusto centro = (CentroCusto) consulta.getSingleResult();
		return centro;
	}

	public CentroCusto buscarRebocadorII(Integer cd_rebocador) {
		Query consulta = em
				.createQuery("select c from CentroCusto c where c.rebocador.cd_rebocador=:rebocador");
		consulta.setParameter("rebocador", cd_rebocador);
		CentroCusto centro = (CentroCusto) consulta.getSingleResult();
		return centro;
	}

}
