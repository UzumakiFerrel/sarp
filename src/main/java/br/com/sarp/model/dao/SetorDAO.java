package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.sarp.model.entidades.Setor;

@Repository
public class SetorDAO {
	
	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager sem;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Setor setor) throws DAOException {

		try {
			sem.merge(setor);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("nao foi possivel salvar", erro);
			
		}

	}

	@Transactional
	public void excluir(Setor setor) {

		try {
Setor setorexc=buscarPorId(setor.getCd_setor());
			sem.remove(setorexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			//e.printStackTrace();
System.out.println("Erro ao excluir! "+e.getMessage());
		}

	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Setor> buscarTodos() {
		Query consulta = sem.createQuery("select s from Setor s");

		return consulta.getResultList();
	}

	@Transactional
	public Setor buscarPorId(int id) {

		return sem.find(Setor.class, id);
	}

}
