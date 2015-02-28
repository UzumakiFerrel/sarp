package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.PrestadorServico;
import br.com.sarp.model.entidades.Estados;

@Repository
public class PrestadorServicoDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(PrestadorServico prestadorServico) throws DAOException {

		try {
			em.merge(prestadorServico);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(PrestadorServico prestadorServico) {

		try {
			PrestadorServico prestadorServicoexc = buscarPorId(prestadorServico
					.getCd_prestador());
			em.remove(prestadorServicoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<PrestadorServico> buscarTodos() {
		Query consulta = em.createQuery("select c from PrestadorServico c");

		return consulta.getResultList();
	}

	@Transactional
	public PrestadorServico buscarPorId(Integer id) {

		return em.find(PrestadorServico.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<PrestadorServico> buscarPrestadorServico(Estados estado) {
		Query consulta = em
				.createQuery("select c from PrestadorServico c where c.estado=:est"); // JPQL
		consulta.setParameter("est", estado);
		return consulta.getResultList();
	}

	public PrestadorServico salvarReturnId(PrestadorServico prs) {

		String nome = prs.getNome();
		String documento = prs.getDocumento();
		String empresa = prs.getEmpresa();
		PrestadorServico pr = null;
		
		try {
			salvar(prs);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Query consulta = em
					.createQuery("select c from PrestadorServico c where c.documento=:documento and c.empresa=:empresa and c.nome=:nome"); // JPQL

			consulta.setParameter("nome", nome);
			consulta.setParameter("empresa", empresa);
			consulta.setParameter("documento", documento);
			
			pr = (PrestadorServico) consulta.getSingleResult();
			

		} catch (Exception erro) {

		}

		return pr;
	}
}
