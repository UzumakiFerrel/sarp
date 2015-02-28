package br.com.sarp.controller.beans;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.EmbarqueService;
import br.com.sarp.model.service.TripulanteService;

@Controller
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
	private Usuario logado;
	private Rebocador rebocador = new Rebocador();
	private Tripulante tripulante = new Tripulante();
	private String setor;
	private String dtlogin;

	@Inject
	private TripulanteService tripulanteService = new TripulanteService();
	@Inject
	private EmbarqueService embarqueService = new EmbarqueService();

	public String verificaAcesso() {

		logado = getUsuario();
		String regra = logado.getRegra();
		if (regra == "ROLE_CMTE") {
			return "true";
		} else {

			return "false";
		}

	}

	public Usuario getUsuario() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		usuario = (Usuario) session.getAttribute("usuarioLogado");

		return usuario;

	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getLogado() {

		logado = getUsuario();

		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	public Tripulante getTripulante() {
		tripulante = tripulanteService.buscarPorId(usuario.getMatricula());
		return tripulante;

	}

	public void setTripulante(Tripulante tripulante) {
		this.tripulante = tripulante;
	}

	public String getSetor() {

		if (getRebocador() != null) {
			setor = getRebocador().getNome();
		}

		else if (getRebocador() == null) {
			setor = getLogado().getSetor().getNome();
		}
		
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public TripulanteService getTripulanteService() {
		return tripulanteService;
	}

	public void setTripulanteService(TripulanteService tripulanteService) {
		this.tripulanteService = tripulanteService;
	}

	public String getDtlogin() {

		Calendar cd = Calendar.getInstance();
		Date data = cd.getTime();
		DateFormat dt = DateFormat.getDateInstance(DateFormat.FULL);
		dtlogin = dt.format(data);
		return dtlogin;
	}

	public void setDtlogin(String dtlogin) {
		this.dtlogin = dtlogin;
	}

	public Rebocador getRebocador() {

		rebocador = embarqueService.buscarRebocadorEmbarque(getLogado());
		
		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

}
