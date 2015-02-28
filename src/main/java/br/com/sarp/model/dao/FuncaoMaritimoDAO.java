package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.FuncaoMaritimo;

@Repository
public class FuncaoMaritimoDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(FuncaoMaritimo funcaoMaritimo) throws DAOException {

		try {
			em.merge(funcaoMaritimo);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(FuncaoMaritimo funcaoMaritimo) {

		try {
FuncaoMaritimo funcaoMaritimoexc=buscarPorId(funcaoMaritimo.getCd_categoria());
			em.remove(funcaoMaritimoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<FuncaoMaritimo> buscarTodos() {
		Query consulta = em.createQuery("select s from FuncaoMaritimo s");

		return consulta.getResultList();
	}

	@Transactional
	public FuncaoMaritimo buscarPorId(int id) {

		return em.find(FuncaoMaritimo.class, id);
	}

}
