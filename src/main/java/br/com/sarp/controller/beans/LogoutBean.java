package br.com.sarp.controller.beans;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@Controller
@ViewScoped
public class LogoutBean {

	public void sairIndex(){
		
		try {

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);

			fc.getExternalContext().redirect("index.xhtml");
			
			session.invalidate();
			
fc.responseComplete();			
			
System.out
					.println("Saindo do sarp ###################################################");

		} catch (Exception e) {
			System.out.println("Erro ao sair #######################"
					+ e.getMessage());
		}
	}


		
	
	
	public void sair() {

		/*
		 * FacesContext.getCurrentInstance().getExternalContext().invalidateSession
		 * ();
		 * 
		 * FacesContext context = FacesContext.getCurrentInstance(); HttpSession
		 * session = (HttpSession) context.getExternalContext()
		 * .getSession(false); usuario = (Usuario)
		 * session.getAttribute("usuarioLogado");

			HttpServletResponse response = (HttpServletResponse) fc
					.getExternalContext().getResponse();
			
			 response.sendRedirect("All/index.xhtml");

		 */
		try {

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);

			fc.getExternalContext().redirect("../All/index.xhtml");
			
			session.invalidate();
			
fc.responseComplete();			
			
System.out
					.println("Saindo do sarp ###################################################");

		} catch (Exception e) {
			System.out.println("Erro ao sair #######################"
					+ e.getMessage());
		}
	}

}

