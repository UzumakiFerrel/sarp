package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Tripulacao;
import br.com.sarp.model.entidades.Armador;

@Repository
public class ArmadorDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Armador armador) throws DAOException {

		try {
			em.merge(armador);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(Armador armador) {

		try {
			Armador armadorexc = buscarPorId(armador.getCd_armador());
			em.remove(armadorexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Armador> buscarTodos() {
		Query consulta = em.createQuery("select u from Armador u");

		return consulta.getResultList();
	}

	@Transactional
	public Armador buscarPorId(Integer id) {

		return em.find(Armador.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Armador> buscarPorTripulacao(Tripulacao tripulacao) {
		Query consulta = em
				.createQuery("select t from Armador t where t.tripulacao=:tripulacao");
		consulta.setParameter("tripulacao", tripulacao);

		return consulta.getResultList();
	}
}
