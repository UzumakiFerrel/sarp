package br.com.sarp.controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
		//System.out.println("entrei before !!! ");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		if (session != null) {
			String mensagem = (String) session.getAttribute("msg");
			
			if (mensagem != null) {
				//System.out.println("Before !!!! "+mensagem);
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, mensagem, null));
				session.setAttribute("msg", null);
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
