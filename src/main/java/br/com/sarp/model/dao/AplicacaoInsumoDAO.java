package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.AplicacaoInsumo;

@Repository
public class AplicacaoInsumoDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(AplicacaoInsumo aplicacaoInsumo) throws DAOException {

		try {
			em.merge(aplicacaoInsumo);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(AplicacaoInsumo aplicacaoInsumo) {

		try {
AplicacaoInsumo aplicacaoInsumoexc=buscarPorId(aplicacaoInsumo.getCd_aplicacao());
			em.remove(aplicacaoInsumoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<AplicacaoInsumo> buscarTodos() {
		Query consulta = em.createQuery("select u from AplicacaoInsumo u");

		return consulta.getResultList();
	}

	@Transactional
	public AplicacaoInsumo buscarPorId(Integer id) {

		return em.find(AplicacaoInsumo.class, id);
	}

}
