package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.RequisicaoMaterial;
import br.com.sarp.model.entidades.Solicitacoes;

@Repository
public class RequisicaoMaterialDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	private Solicitacoes solicitacoes = new Solicitacoes();

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(RequisicaoMaterial requisicaoMaterial)
			throws DAOException {

		try {
			em.merge(requisicaoMaterial);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("Nao foi possivel salvar (RequisicaoMaterialDAO)", erro);

		}

	}

	@Transactional
	public void excluir(RequisicaoMaterial requisicaoMaterial) {

		try {
			RequisicaoMaterial requisicaoMaterialexc = buscarPorId(requisicaoMaterial
					.getNum_req());
			em.remove(requisicaoMaterialexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<RequisicaoMaterial> buscarTodos() {
		Query consulta = em.createQuery("select u from RequisicaoMaterial u");

		return consulta.getResultList();
	}

	@Transactional
	public RequisicaoMaterial buscarPorId(int id) {

		return em.find(RequisicaoMaterial.class, id);
	}

	@Transactional
	public Integer buscarUltimo() {
		Integer num;
		Query consulta = em
				.createQuery("select max(rm.num_req) from RequisicaoMaterial rm");
num = (Integer) consulta.getSingleResult();

if (num==null){
num=0;
}
return num;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<RequisicaoMaterial> buscarcCustoSituacao(CentroCusto cCusto,
			int i) {
		Query consulta = em
				.createQuery("select rm from RequisicaoMaterial rm where rm.cCustoOrigem=:cCusto and rm.sit.cd_sitrm=:sit");
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
	public List<RequisicaoMaterial> buscarBaseSituacao(Base base, int i) {

		Query consulta = em
				.createQuery("select rm from RequisicaoMaterial rm where rm.solicitante.base=:base and rm.sit.cd_sitrm=:sit");
		consulta.setParameter("base", base);
		consulta.setParameter("sit", i);

		return consulta.getResultList();
	}

}
