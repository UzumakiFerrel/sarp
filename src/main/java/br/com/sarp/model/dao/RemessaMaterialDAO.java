package br.com.sarp.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.RemessaMaterial;
import br.com.sarp.model.entidades.SituacaoRequisicao;
import br.com.sarp.model.entidades.TipoDocumento;
import br.com.sarp.model.service.TipoDocumentoService;

@Repository
public class RemessaMaterialDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;
	@Inject
	TipoDocumentoService tpDocumento;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(RemessaMaterial remessaMaterial) throws DAOException {

		try {
			em.merge(remessaMaterial);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);

		}

	}

	@Transactional
	public void excluir(RemessaMaterial remessaMaterial) {

		try {
			RemessaMaterial remessaMaterialexc = buscarPorId(remessaMaterial
					.getNum_rem());
			em.remove(remessaMaterialexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<RemessaMaterial> buscarTodos() {
		Query consulta = em.createQuery("select u from RemessaMaterial u");

		return consulta.getResultList();
	}

	@Transactional
	public RemessaMaterial buscarPorId(int id) {

		return em.find(RemessaMaterial.class, id);
	}

	public Integer buscarUltimo() {
		Query consulta = em
				.createQuery("select max(rm.num_rem) from RemessaMaterial rm");

		return (Integer) consulta.getSingleResult();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<RemessaMaterial> buscarRequisicaoSituacao(Integer num_req, int i) {
		Query consulta = em
				.createQuery("select rm from RemessaMaterial rm where rm.solicitacao.num_req=:numreq and rm.status.cd_sitrm=:i");
		consulta.setParameter("numreq", num_req);
		consulta.setParameter("i", i);

		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<RemessaMaterial> buscarCCustoSituacao(CentroCusto cCusto,
			int situacao) {
		Query consulta = em
				.createQuery("Select rm from RemessaMaterial rm where rm.cCustoDestino=:cCusto and rm.status.cd_sitrm=:situacao");
		consulta.setParameter("cCusto", cCusto);
		consulta.setParameter("situacao", situacao);

		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<RemessaMaterial> buscarRemessaSolicitacaoCCustoSituacao(
			CentroCusto cCusto, SituacaoRequisicao situacao) {

		TipoDocumento solicitacao = new TipoDocumento();
		solicitacao = tpDocumento.buscarId(4);

		Query consulta = em
				.createQuery("Select rm from RemessaMaterial rm where rm.cCustoDestino=:cCusto and rm.status=:situacao and rm.tpDocumento=:solicitacao");
		consulta.setParameter("cCusto", cCusto);
		consulta.setParameter("situacao", situacao);
		consulta.setParameter("solicitacao", solicitacao);

		return consulta.getResultList();
	}

	@Transactional
	public void baixarRemessaSolicitacao(RemessaMaterial remessaMaterial)
			throws DAOException {

		try {

			em.merge(remessaMaterial);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("Nao foi possivel Baixar", erro);

		}

	}

}
