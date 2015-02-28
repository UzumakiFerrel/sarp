package br.com.sarp.model.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sarp.model.entidades.Embarque;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.TripulanteService;

@Repository
public class EmbarqueDAO {

	// injecao de dependencia EntityManager
	@PersistenceContext
	EntityManager em;

	@Inject
	TripulanteService tripulanteService;

	// gerenciador de transacoes do spring

	@Transactional
	public void salvar(Embarque embarque) throws DAOException,
			DataIntegrityViolationException, PSQLException {

		try {
			em.merge(embarque);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("Verificar Dados Usuarios (EmbarqueDao)",
					erro);
		}

	}

	@Transactional
	public void excluir(Embarque embarque) throws DAOException,
			DataIntegrityViolationException, PSQLException {

		try {
			Embarque embarqueexc = buscarPorId(embarque.getCd_embarque());
			em.remove(embarqueexc);
		} catch (Exception erro) {// captura e excecao no banco de dados
			throw new DAOException("Nao foi possivel Excluir", erro);

		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Embarque> buscarTodos() {
		Query consulta = em.createQuery("select u from Embarque u");

		return consulta.getResultList();
	}

	@Transactional
	public Embarque buscarPorId(Integer id) {

		return em.find(Embarque.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Embarque> buscarTodosRebocador(Rebocador rebocador) {
		Query consulta = em
				.createQuery("select c from Embarque c where c.rebocador=:rebocador");
		consulta.setParameter("rebocador", rebocador);
		return consulta.getResultList();

	}

	@Transactional
	public void deleteEmbarque(Embarque embarque) throws DAOException {

		try {
			Query consulta = em
					.createQuery("delete e from embarque e where e=:embarque");
			consulta.setParameter("embarque", embarque);

		} catch (Exception erro) {
			throw new DAOException(
					"Nao foi Possivel excluir Embarque (Embarque DAO)", erro);

		}
	}

	@Transactional
	public Usuario buscarPorUsuario(Usuario usuario) {

		Usuario usu = null;
		try {
			Query consulta = em
					.createQuery("Select e from Embarque e where e.usuario=:usuario");
			consulta.setParameter("usuario", usuario);
			usu = (Usuario) consulta.getSingleResult();
			return usu;
		} catch (Exception erro) {
			System.out.println("Erro ao pesquisar usuario (EmbarqueDao)! "
					+ erro.getMessage());
			return usu;
		}
	}

	// retorna o cmte embarcado
	@Transactional
	public Tripulante buscarCmteEmbarcado(Rebocador rebocador)
			throws DAOException {
		Tripulante tripulante = new Tripulante();
		//tripulante = null;
		
		
		
		// lista de embarcados

		try {
			List<Embarque> embarcados = buscarTodosRebocador(rebocador);

			for (Embarque emb : embarcados) {
		tripulante = tripulanteService.buscarPorId(emb.getUsuario()
						.getMatricula());
				if (tripulante.getFcmaritimo().getCd_categoria() == 1) {

					return tripulante;
				}
			}

		} catch (Exception erro) {

			throw new DAOException(
					"Nao foi Possivel pesquisar Embarque Cmte (Embarque DAO)",
					erro);

		}

		tripulante = new Tripulante();
		tripulante.setiD(0);
		
		return tripulante;

	}

	@Transactional
	public Rebocador buscarRebocadorEmbarque(Usuario usuario) {

		Rebocador rb = null;
		Embarque e = null;
		try {

			Query consulta = em
					.createQuery("Select e from Embarque e where e.usuario=:usuario");
			consulta.setParameter("usuario", usuario);

			e = (Embarque) consulta.getSingleResult();
			rb = e.getRebocador();
			return rb;
		} catch (Exception erro) {
			System.out.println("Erro ao pesquisar Rebocador (EmbarqueDao)! "
					+ erro.getMessage());
			return null;
		}

	}

	@Transactional
	public Embarque buscarEmbarqueTripulante(Tripulante cmte) {

		Integer usuario = cmte.getiD();

		Query consulta = em
				.createQuery("Select e from Embarque e where e.usuario.matricula=:usuario");
		consulta.setParameter("usuario", usuario);

		Embarque emb = (Embarque) consulta.getSingleResult();

		return emb;
	}

}
