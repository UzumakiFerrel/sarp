package br.com.sarp.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.dao.UsuarioDAO;
import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.entidades.Usuario;

@Service
public class UsuarioService {

	@Inject
	private UsuarioDAO usuarioDao;

	public void salvar(Usuario usuario) throws ServiceException {

		/*
		 * validacao das regras de negocio a base,setor,funcao,situacao devem
		 * existir no cadastro nenhum campo pode ser vazio *
		 */
		// validacao da regra exemplo
		if (usuario.getNome() == null || usuario.getNome() == "") {
			throw new ServiceException("Campo nome usuario nao pode ser vazio!");
		}

		try {
			usuarioDao.salvar(usuario);
		} catch (DAOException causa) {
			throw new ServiceException("Nao foi possivel salvar !", causa);

		}

	}

	public List<Usuario> buscaTodos() {

		List<Usuario> lista = usuarioDao.buscarTodos();
		return lista;

	}

	public void excluir(Usuario usuario) {
		// validacao a exclusao
		usuarioDao.excluir(usuario);

	}

	public List<Usuario> buscarTodosBase(Base base) {

		// validacao da pesquisa
		List<Usuario> lista = usuarioDao.buscarTodosBase(base);
		return lista;
	}

	public Usuario buscarId(Integer intid) {
		// TODO Auto-generated method stub
		Usuario usu = new Usuario();
		usu = usuarioDao.buscarPorId(intid);

		return usu;
	}

	public List<Usuario> buscarApenasUsuariosBase(Base base) {

		List<Usuario> lista = usuarioDao.buscarApenasUsuariosBase(base);
		
		return lista;
	}

}
