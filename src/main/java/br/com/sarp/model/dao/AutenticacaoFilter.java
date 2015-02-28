package br.com.sarp.model.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.sarp.model.entidades.Usuario;

public class AutenticacaoFilter extends UsernamePasswordAuthenticationFilter {

	@PersistenceContext
	EntityManager em;

	private String mensagem;

	@Inject
	UsuarioDAO usuarioDao;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		String login = request.getParameter("j_login");
		String senha = request.getParameter("j_senha");
		System.out.println("Entrou Login  :" + login);
		System.out.println("Entrou senha  :" + senha);
		try {

			Usuario usuario = usuarioDao.buscarUsuarioLogin(login, senha);

			if (usuario != null) {

				Collection<GrantedAuthority> regras = new ArrayList<GrantedAuthority>();
				regras.add((GrantedAuthority) new SimpleGrantedAuthority(
						usuario.getRegra()));

				request.getSession().setAttribute("usuarioLogado", usuario);
			//	mensagem = "Bem Vindo" + usuario.getLogin();
			//	System.out.println("Encomtrou usuario   :" + usuario.getNome());
				return new UsernamePasswordAuthenticationToken(
						usuario.getLogin(), usuario.getSenha(), regras);

			} else {
				System.out.println("Nao Encotrou usuario I   :" + mensagem);
				mensagem = "Dados Incorretos ou Tripulante nao EMBARCADO!!!";
				throw new BadCredentialsException(mensagem);
			
			}

		} catch (Exception e) {
			System.out.println("Nao Encotrou usuario  Excpetion :"
					+ e.getMessage());
			throw new BadCredentialsException(e.getMessage());
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {
		try {

			SecurityContextHolder.getContext().setAuthentication(authResult);
			//request.getSession().setAttribute("msg", mensagem);
			response.sendRedirect("All/index.xhtml");

		} catch (Exception e) {
			System.out.println("Usuario sem acesso !!!" + e.getMessage());
			mensagem = "Usuario Sem acesso";
			request.getSession().setAttribute("msg", mensagem);
			response.sendRedirect("All/index.xhtml");
			
		}

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {

		request.getSession().setAttribute("msg", mensagem);
		response.sendRedirect("login.xhtml");

	}

	
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	
	
}
