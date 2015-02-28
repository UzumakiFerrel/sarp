package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.CategoriaMaritimo;

@Repository
public class CategoriaMaritimoDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(CategoriaMaritimo categoriaMaritimo) throws DAOException {

		try {
			em.merge(categoriaMaritimo);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(CategoriaMaritimo categoriaMaritimo) {

		try {
CategoriaMaritimo categoriaMaritimoexc=buscarPorId(categoriaMaritimo.getCd_categoria());
			em.remove(categoriaMaritimoexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<CategoriaMaritimo> buscarTodos() {
		Query consulta = em.createQuery("select s from CategoriaMaritimo s");

		return consulta.getResultList();
	}

	@Transactional
	public CategoriaMaritimo buscarPorId(int id) {

		return em.find(CategoriaMaritimo.class, id);
	}

}
