package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.SolicitacaoServico;
import br.com.sarp.model.entidades.Solicitacoes;

@Repository
public class SolicitacaoServicoDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	private Solicitacoes solicitacoes = new Solicitacoes();

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(SolicitacaoServico solicitacaoServico)
			throws DAOException {

		try {
			em.merge(solicitacaoServico);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(SolicitacaoServico solicitacaoServico) {

		try {
			SolicitacaoServico solicitacaoServicoexc = buscarPorId(solicitacaoServico.getNum_sserv());
			em.remove(solicitacaoServicoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SolicitacaoServico> buscarTodos() {
		Query consulta = em.createQuery("select u from SolicitacaoServico u");

		return consulta.getResultList();
	}

	@Transactional
	public SolicitacaoServico buscarPorId(int id) {

		return em.find(SolicitacaoServico.class, id);
	}

	@Transactional
	public Integer buscarUltimo() {
		Query consulta = em
				.createQuery("select max(rm.num_sserv) from SolicitacaoServico rm");

		return (Integer) consulta.getSingleResult();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SolicitacaoServico> buscarcCustoSituacao(CentroCusto cCusto,
			int i) {
		Query consulta = em
				.createQuery("select rm from SolicitacaoServico rm where rm.cCustoOrigem=:cCusto and rm.sit.cd_sitrm=:sit");
		consulta.setParameter("cCusto", cCusto);
		consulta.setParameter("sit", i);

		return consulta.getResultList();
	}

	public Solicitacoes getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(Solicitacoes solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SolicitacaoServico> buscarBaseSituacao(Base base, int i) {

		Query consulta = em
				.createQuery("select rm from SolicitacaoServico rm where rm.solicitante.base=:base and rm.sit.cd_sitrm=:sit");
		consulta.setParameter("base", base);
		consulta.setParameter("sit", i);

		return consulta.getResultList();
	}

}
