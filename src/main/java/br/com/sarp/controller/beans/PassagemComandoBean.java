package br.com.sarp.controller.beans;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.PassagemComando;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.EmbarqueService;
import br.com.sarp.model.service.PassagemComandoService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.SituacaoRequisicaoService;
import br.com.sarp.model.service.TripulanteService;

@Controller
@ViewScoped
public class PassagemComandoBean {

	private PassagemComando passagemComando = new PassagemComando();
	private PassagemComando passagemComandobx = new PassagemComando();
	private String data;
	private Tripulante comandanteIn, comandanteOut, tripulante;
	private Usuario logado;
	private Rebocador rebocador;
	private Boolean cienteOcorrencias;
	private List<PassagemComando> passagemComandoList;

	@Inject
	PassagemComandoService passagemComandoService;
	@Inject
	EmbarqueService embarqueService;
	@Inject
	TripulanteService tripulanteService;
	@Inject
	SituacaoRequisicaoService situacaoRequisicaoService;

	public void salvar() {

		try {

			passagemComando.setAbertura(getData());
			passagemComando.setComandanteOut(getComandanteOut());
			passagemComando.setRebocador(getRebocador());
			passagemComando.setStPassagemComando(situacaoRequisicaoService
					.buscarId(1));
			passagemComandoService.salvar(passagemComando);
			passagemComando = new PassagemComando();
			passagemComandoList = passagemComandoService.buscaTodos();
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

	public void receber() {

		if (getCienteOcorrencias() == true) {

			PassagemComando pComando = new PassagemComando();
			pComando = getPassagemComandobx();

			if (pComando.getComandanteIn().getiD() > 0) {

				pComando.setStPassagemComando(situacaoRequisicaoService
						.buscarId(2));

				try {
					passagemComandoService.salvar(pComando);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Passagem Recebida com sucesso !", null));

				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} else if (getCienteOcorrencias() == false) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Verificar Ciente !!!!", null));
		}
	}

	@PostConstruct
	public void init() {
		passagemComandoList = passagemComandoService.buscaTodos();
	}

	public void excluir() {
		passagemComandoService.excluir(passagemComando);
		passagemComandoList = passagemComandoService.buscaTodos();
		// limpando instancia do passagemComando
		passagemComando = new PassagemComando();
	}

	public PassagemComando getPassagemComando() {

		return passagemComando;
	}

	public void setPassagemComando(PassagemComando passagemComando) {
		this.passagemComando = passagemComando;
	}

	public PassagemComandoService getPassagemComandoService() {
		return passagemComandoService;
	}

	public void setPassagemComandoService(
			PassagemComandoService passagemComandoService) {
		this.passagemComandoService = passagemComandoService;
	}

	public List<PassagemComando> getPassagemComandoList() {
		return passagemComandoList;
	}

	public void setPassagemComandoList(List<PassagemComando> passagemComandoList) {
		this.passagemComandoList = passagemComandoList;
	}

	public String getData() {

		Calendar cd = Calendar.getInstance();
		Date dat = cd.getTime();
		DateFormat dt = DateFormat.getDateInstance(DateFormat.FULL);
		data = dt.format(dat);

		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Tripulante getComandanteIn() {

		comandanteIn = getTripulante();

		return comandanteIn;
	}

	public void setComandanteIn(Tripulante comandanteIn) {
		this.comandanteIn = comandanteIn;
	}

	public Tripulante getComandanteOut() {

		comandanteOut = getTripulante();

		return comandanteOut;
	}

	public void setComandanteOut(Tripulante comandanteOut) {
		this.comandanteOut = comandanteOut;
	}

	public Rebocador getRebocador() {

		rebocador = embarqueService.buscarRebocadorEmbarque(getLogado());

		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public Tripulante getTripulante() {

		tripulante = tripulanteService.buscarPorId(getLogado().getMatricula());

		return tripulante;
	}

	public void setTripulante(Tripulante tripulante) {
		this.tripulante = tripulante;
	}

	public Usuario getLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		logado = (Usuario) session.getAttribute("usuarioLogado");

		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	public Boolean getCienteOcorrencias() {
		return cienteOcorrencias;
	}

	public void setCienteOcorrencias(Boolean cienteOcorrencias) {
		this.cienteOcorrencias = cienteOcorrencias;
	}

	public PassagemComando getPassagemComandobx() {

		passagemComandobx = new PassagemComando();
		passagemComandobx = passagemComandoService
				.buscarPassagemComandoRebocador(getRebocador());

		if (passagemComandobx != null) {

			if (passagemComandobx.getComandanteOut().equals(getTripulante())) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Comandantes Iguais !!!", null));

			} else if (passagemComandobx.getComandanteOut() != getTripulante()) {
				passagemComandobx.setComandanteIn(getTripulante());
				passagemComandobx.setTrocaTurno(getData());

			}

		}

		return passagemComandobx;
	}

	public void setPassagemComandobx(PassagemComando passagemComandobx) {
		this.passagemComandobx = passagemComandobx;
	}

}
