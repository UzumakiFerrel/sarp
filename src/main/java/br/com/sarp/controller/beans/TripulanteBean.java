package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TripulanteService;
import br.com.sarp.model.service.UsuarioService;

@Controller
@ViewScoped
public class TripulanteBean {

	private Tripulante tripulante = new Tripulante(); // recebe os dados do
	private Usuario logado = new Usuario();
	private Tripulante tripulantetela = new Tripulante();

	// objeto na tela
	@Inject
	TripulanteService tripulanteService;

	private List<Tripulante> tripulanteList;

	List<Usuario> usuarioList;

	@Inject
	private UsuarioService usuarioService;

	/*
	 * public void buscarTodosBase(ValueChangeEvent evento) {
	 * 
	 * if (evento.getNewValue()!= evento.getOldValue()) {
	 * 
	 * Base base = (Base) evento.getNewValue();
	 * 
	 * usuarioList = usuarioService.buscarTodosBase(base);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * public void buscarUsuarioId(ValueChangeEvent evento){
	 * 
	 * if(evento.getNewValue()!=evento.getOldValue()){ Usuario usu = (Usuario)
	 * evento.getNewValue(); tripulante.setUsuario(usu);
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
	
	public void usuarioListBase() {
		usuarioList = usuarioService.buscarTodosBase(logado.getBase());

	}

	public void salvar() {
		try {

			tripulantetela.setiD(tripulantetela.getUsuario().getMatricula());

			tripulanteService.salvar(tripulantetela);
			tripulantetela = new Tripulante();
			tripulanteList = tripulanteService.buscaTodos();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Salvo com sucesso !", null));

		} catch (ServiceException e) {
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Nao foi possivel salvar ! "
											+ e.getMessage(), null));
			// codigo que envia msg de erro pra tela
			e.printStackTrace();
		}

	}

	public void cancelar() {

		tripulantetela = new Tripulante();

	}

	@PostConstruct
	public void init() {
		tripulanteList = tripulanteService.buscaTodos();

	}

	public void excluir() {
		tripulanteService.excluir(tripulante);
		tripulanteList = tripulanteService.buscaTodos();
		// limpando instancia do tripulante
		tripulante = new Tripulante();
	}

	public Tripulante getTripulante() {

		tripulante = tripulanteService.buscarPorId(logado.getMatricula());

		return tripulante;
	}

	public void setTripulante(Tripulante tripulante) {
		this.tripulante = tripulante;
	}

	public TripulanteService getTripulanteService() {
		return tripulanteService;
	}

	public void setTripulanteService(TripulanteService tripulanteService) {
		this.tripulanteService = tripulanteService;
	}

	public List<Tripulante> getTripulanteList() {
		return tripulanteList;
	}

	public void setTripulanteList(List<Tripulante> tripulanteList) {
		this.tripulanteList = tripulanteList;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getLogado() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		logado = (Usuario) session.getAttribute("usuarioLogado");
		usuarioListBase();

		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	public Tripulante getTripulantetela() {
		return tripulantetela;
	}

	public void setTripulantetela(Tripulante tripulantetela) {
		this.tripulantetela = tripulantetela;
	}

}
