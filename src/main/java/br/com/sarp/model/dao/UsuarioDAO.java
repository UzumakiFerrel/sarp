package br.com.sarp.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Usuario;

@Repository
public class UsuarioDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	// gerenciador de transacoes do spring
	@Transactional
	public void salvar(Usuario usuario) throws DAOException {

		try {
			em.merge(usuario);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("Nao foi possivel salvar(UsuarioDao)", erro);
		}

	}

	@Transactional
	public void excluir(Usuario usuario) {

		try {
			Usuario usuarioexc = buscarPorId(usuario.getMatricula());
			em.remove(usuarioexc);
		} catch (Exception e) {// captura e excecao no banco de dados
			// e.printStackTrace();
			System.out.println("Erro ao excluir! " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Usuario> buscarTodos() {
		Query consulta = em.createQuery("select u from Usuario u");

		return consulta.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Usuario> buscarTodosBase(Base base) {
		Query consulta = em
				.createQuery("select c from Usuario c where c.base=:base");
		consulta.setParameter("base", base);
		return consulta.getResultList();
	}

	@Transactional
	public Usuario buscarPorId(int id) {

		return em.find(Usuario.class, id);
	}

	@Transactional
	public Usuario buscarUsuarioLogin(String login, String senha) {
		Usuario usuario = null;
		String sim = "SIM";
		try {
			Query query = em
					.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha AND u.acesso=:sim");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			query.setParameter("sim", sim);
			usuario = (Usuario) query.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("DAO: Nao foi encontrado resultado!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DAO: Nao foi encontrado resultado!");
		} finally {
			em.close();
		}

		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Usuario> buscarApenasUsuariosBase(Base base) {
		Integer setor=1;
		Query consulta = em
				.createQuery("select c from Usuario c where c.base=:base and c.setor.cd_setor > :setor");
		consulta.setParameter("base", base);
		consulta.setParameter("setor", setor);
		
		return consulta.getResultList();

	}

}
