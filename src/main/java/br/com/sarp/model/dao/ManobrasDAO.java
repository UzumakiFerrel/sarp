package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Tripulacao;
import br.com.sarp.model.entidades.Manobras;

@Repository
public class ManobrasDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Manobras manobras) throws DAOException {

		try {
			em.merge(manobras);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(Manobras manobras) {

		try {
			Manobras manobrasexc = buscarPorId(manobras.getCd_manobras());
			em.remove(manobrasexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Manobras> buscarTodos() {

		Query consulta = em.createQuery("select u from Manobras u");
		return consulta.getResultList();
	}

	@Transactional
	public Manobras buscarPorId(Integer id) {

		return em.find(Manobras.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Manobras> buscarPorTripulacao(Tripulacao tripulacao) {
		Query consulta = em
				.createQuery("select t from Manobras t where t.tripulacao=:tripulacao");
		consulta.setParameter("tripulacao", tripulacao);

		return consulta.getResultList();
	}

	@Transactional
	public Integer buscarNumeroManobra(Integer mes, Integer ano) {

		Integer num;

		Query consulta = em
				.createQuery("select max(m.cd_manobras) from Manobras m where m.mes=:mes and m.ano=:ano");
		consulta.setParameter("mes", mes);
		consulta.setParameter("ano", ano);

		num = (Integer) consulta.getSingleResult();

		if (num == null) {
			num = 0;
		}

		return num;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Manobras> buscarManobraDia(Integer dia, Integer mes, Integer ano) {

		Query consulta = em
				.createQuery("select m from Manobras m where m.dia=:dia and m.mes=:mes and m.ano=:ano");
		consulta.setParameter("mes", mes);
		consulta.setParameter("ano", ano);
		consulta.setParameter("dia", dia);

		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Manobras> buscarManobraPeriodo(Integer iniciodia,
			Integer iniciomes, Integer inicioano, Integer fimdia,
			Integer fimmes, Integer fimano) {

		Query consulta = em
				.createQuery("select m from Manobras m where m.dia>= :idia and m.dia<= :fdia and m.mes>= :imes and m.mes<= :fmes and m.ano>=:iano and m.ano<=:fano");
		consulta.setParameter("imes", iniciomes);
		consulta.setParameter("iano", inicioano);
		consulta.setParameter("idia", iniciodia);
		consulta.setParameter("fmes", fimmes);
		consulta.setParameter("fano", fimano);
		consulta.setParameter("fdia", fimdia);

		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Manobras> buscarManobraPeriodoRb(Integer iniciodia,
			Integer iniciomes, Integer inicioano, Integer fimdia,
			Integer fimmes, Integer fimano, Rebocador rbRelatorio) {

		Query consulta = em
				.createQuery("select m from Manobras m where m.dia>= :idia and m.dia<= :fdia and m.mes>= :imes and m.mes<= :fmes and m.ano>=:iano and m.ano<=:fano and m.rebocador=:rbRelatorio");
		consulta.setParameter("imes", iniciomes);
		consulta.setParameter("iano", inicioano);
		consulta.setParameter("idia", iniciodia);
		consulta.setParameter("fmes", fimmes);
		consulta.setParameter("fano", fimano);
		consulta.setParameter("fdia", fimdia);
		consulta.setParameter("rbRelatorio", rbRelatorio);

		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Manobras> buscarManobraDiaRb(Integer dia, Integer mes,
			Integer ano, Rebocador rebocador) {

		Query consulta = em
				.createQuery("select m from Manobras m where m.dia=:dia and m.mes=:mes and m.ano=:ano and m.rebocador=:rebocador");
		consulta.setParameter("mes", mes);
		consulta.setParameter("ano", ano);
		consulta.setParameter("dia", dia);
		consulta.setParameter("rebocador", rebocador);
		
		return consulta.getResultList();
	}
}
