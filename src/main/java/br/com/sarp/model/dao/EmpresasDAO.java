package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Empresas;

@Repository
public class EmpresasDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Empresas empresas) throws DAOException {

		try {
			em.merge(empresas);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Empresas empresas) {

		try {
Empresas empresasexc=buscarPorId(empresas.getCd_empresa());
			em.remove(empresasexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Empresas> buscarTodos() {
		Query consulta = em.createQuery("select u from Empresas u");

		return consulta.getResultList();
	}

	@Transactional
	public Empresas buscarPorId(Integer id) {

		return em.find(Empresas.class, id);
	}

}
