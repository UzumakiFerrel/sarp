package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.Locais;

@Repository
public class LocaisDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Locais locais) throws DAOException {

		try {
			em.merge(locais);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Locais locais) {

		try {
Locais locaisexc=buscarPorId(locais.getCd_local());
			em.remove(locaisexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Locais> buscarTodos() {
		Query consulta = em.createQuery("select u from Locais u");

		return consulta.getResultList();
	}

	@Transactional
	public Locais buscarPorId(int id) {

		return em.find(Locais.class, id);
	}

}
