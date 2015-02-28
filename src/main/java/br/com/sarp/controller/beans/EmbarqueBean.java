package br.com.sarp.controller.beans;

import java.text.DateFormat;
import java.util.ArrayList;
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

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.Embarque;
import br.com.sarp.model.entidades.PassagemComando;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.EmbarqueService;
import br.com.sarp.model.service.PassagemComandoService;
import br.com.sarp.model.service.PrestadorServicoService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TripulanteService;
import br.com.sarp.model.service.UsuarioService;

@Controller
@ViewScoped
public class EmbarqueBean {

	private Embarque embarque = new Embarque(); // recebe os dados do objeto na
												// tela
	private Embarque embarqueTripulante = new Embarque();
	private Embarque embarqueFuncionario = new Embarque();
	private Embarque embarqueOutros = new Embarque();
	private Usuario logado = new Usuario();
	private Usuario funcionario = new Usuario();
	private Tripulante tripulante = new Tripulante();
	private Rebocador rebocador = new Rebocador();
	private PassagemComando pComando = new PassagemComando();
	private Tripulante tripulanteEmbarque = new Tripulante();

	@SuppressWarnings("unused")
	private String data;
	private List<Tripulante> tripulanteList;
	private List<Usuario> tripulanteDisponivelList;
	private List<Usuario> funcionarioList;

	@Inject
	EmbarqueService embarqueService;
	@Inject
	UsuarioService usuarioService;
	@Inject
	TripulanteService tripulanteService;
	@Inject
	PrestadorServicoService prestadorServicoService;
	@Inject
	PassagemComandoService passagemComandoService;

	List<Embarque> embarqueFuncionarioList = new ArrayList<Embarque>();
	List<Embarque> embarqueTripulanteList = new ArrayList<Embarque>();
	List<Embarque> embarqueList = new ArrayList<>();

	public void salvar() throws DAOException, ServiceException {

		embarque.setPrestadorServico(null);

		try {

			// Embarque tripulante
			// verifica se usuario he cmte
			// verifica se existi outro cmte embarcado
			// verifica se existi passagem de comando

			if ((tripulanteEmbarque.getFcmaritimo().getCd_categoria() == 1)) {

				// verifica se existe passagem de comando gerada
				setpComando(passagemComandoService
						.buscarPassagemComandoRebocador(getRebocador()));

				if (pComando != null
						&& pComando.getStPassagemComando().getCd_sitrm() == 2) {

					Tripulante Cmte = new Tripulante();
					Cmte = embarqueService.buscarCmteEmbarcado(getRebocador());

					if (Cmte.getiD() > 0) {

						// desenbarcar Comandante embarcado
						Embarque emb = embarqueService
								.buscarEmbarqueTripulante(Cmte);
						emb.setDesenbarque(getData());
						emb.setHrdesenbarque(embarqueTripulante.getHrembarque());
						emb.setLocaldesenbarque(embarqueTripulante
								.getLocalembarque());
						embarqueService.salvar(emb); // desenbarca o comandante
					}

				} else if (pComando != null
						&& pComando.getStPassagemComando().getCd_sitrm() == 1
						&& pComando.getComandanteOut() != getTripulante()) {

					tripulanteEmbarque = new Tripulante();
					embarqueTripulante = new Embarque();

					throw new ServiceException(
							"Passagem de Comando Pendente !");
					/*
					 * FacesContext.getCurrentInstance().addMessage( null, new
					 * FacesMessage(FacesMessage.SEVERITY_INFO,
					 * "Erro :: Passagem de Comando Pendente !", null));
					 */
					
				} else if (pComando == null) {

					tripulanteEmbarque = new Tripulante();
					embarqueTripulante = new Embarque();
					
					throw new ServiceException(
							"Favor gerar Passagem de Comando !");
/*					
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_INFO,
											"Erro :: Favor Gerar Passagem de Comando !",
											null));
*/
				}
			}

			if ((tripulanteEmbarque != null)
					&& (embarqueTripulante.getLocalembarque().equals(null) == false)) {

				embarque.setRebocador(getRebocador());
				embarque.setUsuario(tripulanteEmbarque.getUsuario());
				embarque.setEmbarque(getData());
				embarque.setHrembarque(embarqueTripulante.getHrembarque());
				embarque.setLocalembarque(embarqueTripulante.getLocalembarque());
				embarque.setDesenbarque(embarqueTripulante.getDesenbarque());
				embarque.setLocaldesenbarque(embarqueTripulante
						.getLocaldesenbarque());
				embarque.setHrdesenbarque(embarqueTripulante.getHrdesenbarque());

				embarqueService.salvar(embarque);

				// verifica se embarcou novo tripulante
				if (tripulanteEmbarque.getFcmaritimo().getCd_categoria() == 1) {
					try {
						FacesContext fc = FacesContext.getCurrentInstance();
						HttpSession session = (HttpSession) fc
								.getExternalContext().getSession(false);

						fc.getExternalContext().redirect("index.xhtml");

						session.invalidate();
					} catch (Exception e) {

						FacesContext
								.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_INFO,
												"Erro :: Ao efetuar logof de Comandante !",
												null));
					}

				}

			} else if (embarqueFuncionario.getUsuario() != null) {

				embarque.setRebocador(getRebocador());
				embarque.setUsuario(embarqueFuncionario.getUsuario());
				embarque.setEmbarque(getData());
				embarque.setLocalembarque(embarqueFuncionario
						.getLocalembarque());
				embarque.setHrembarque(embarqueFuncionario.getHrembarque());
				embarque.setDesenbarque(embarqueFuncionario.getDesenbarque());
				embarque.setLocaldesenbarque(embarqueFuncionario
						.getLocaldesenbarque());
				embarque.setHrdesenbarque(embarqueFuncionario
						.getHrdesenbarque());

			}

			// salva embarque

			// verifica se o tripulante embarcado he comandante para efetuar o
			// logof doa anterior
			embarque = new Embarque();
			embarqueFuncionario = new Embarque();
			embarqueTripulante = new Embarque();
			tripulanteEmbarque = new Tripulante();

			embarqueList = embarqueService.buscaTodosRebocador(getRebocador());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Embarque realizado com sucesso !", null));

		} catch (NullPointerException | ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro :: "
							+ e.getMessage(), null));
			// codigo que envia msg de erro pra tela
			e.printStackTrace();
		}
	}

	public void cancelar() {

		embarque = new Embarque();
		embarqueFuncionario = new Embarque();
		embarqueTripulante = new Embarque();
		tripulanteEmbarque = new Tripulante();
	}

	public void listasEmbarque() {
		getEmbarqueList();
		embarqueFuncionarioList.clear();
		embarqueTripulanteList.clear();

		for (Embarque emb : embarqueList) {

			try {
				Tripulante t = null;

				t = tripulanteService.buscarPorId(emb.getUsuario()
						.getMatricula());
				if (t.getUsuario() != null)
					embarqueTripulanteList.add(emb);

			}

			catch (Exception e) {
				embarqueFuncionarioList.add(emb);

				System.out.println("Erro LIsta tirpulante" + e.getMessage());
			}

			/*
			 * 
			 * if (t.equals(null)) { embarqueFuncionarioList.add(emb);
			 * 
			 * } else if (t!= null) { }
			 * 
			 * }
			 */

		}

	}

	public void desenbarqueTripulante() {

		setTripulanteEmbarque(tripulanteService.buscarPorId(embarqueTripulante
				.getUsuario().getMatricula()));
		embarque.setCd_embarque(embarqueTripulante.getCd_embarque());
		embarqueTripulante.setDesenbarque(getData());

		if (tripulanteEmbarque.getFcmaritimo().getCd_categoria() == 1) {
			cancelar();

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Favor Embarcar Muda Comandante!!!!", null));
		}

	}

	public Embarque getEmbarque() {
		return embarque;
	}

	public void setEmbarque(Embarque embarque) {
		this.embarque = embarque;
	}

	public EmbarqueService getEmbarqueService() {
		return embarqueService;
	}

	public void setEmbarqueService(EmbarqueService embarqueService) {
		this.embarqueService = embarqueService;
	}

	public List<Embarque> getEmbarqueList() {

		embarqueList = embarqueService.buscaTodosRebocador(getRebocador());

		return embarqueList;
	}

	public void setEmbarqueList(List<Embarque> embarqueList) {
		this.embarqueList = embarqueList;
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

		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	public Tripulante getTripulante() {

		tripulante = tripulanteService.buscarPorId(getLogado().getMatricula());

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

	@PostConstruct
	public void init() {

		// embarqueList = embarqueService.buscaTodos();

	}

	public List<Tripulante> getTripulanteList() throws ServiceException {
		// contem relacao dos tripulantes por base

		try {
			tripulanteList = tripulanteService.buscaTodosBase(logado.getBase());
		} catch (ServiceException causa) {
			// TODO Auto-generated catch block
			throw new ServiceException(
					"Lista tripulante vazia (EmbarqueBean) !", causa);
		}

		return tripulanteList;
	}

	public void setTripulanteList(List<Tripulante> tripulanteList) {
		this.tripulanteList = tripulanteList;
	}

	public String getData() {

		Calendar c = Calendar.getInstance();
		Date dt = c.getTime();
		DateFormat f = DateFormat.getDateInstance();
		String data = f.format(dt);

		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Usuario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Usuario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Usuario> getFuncionarioList() {

		funcionarioList = usuarioService.buscarApenasUsuariosBase(logado
				.getBase());

		return funcionarioList;
	}

	public void setFuncionarioList(List<Usuario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

	public Embarque getEmbarqueFuncionario() {
		return embarqueFuncionario;
	}

	public void setEmbarqueFuncionario(Embarque embarqueFuncionario) {
		this.embarqueFuncionario = embarqueFuncionario;
	}

	public Embarque getEmbarqueOutros() {
		return embarqueOutros;
	}

	public void setEmbarqueOutros(Embarque embarqueOutros) {
		this.embarqueOutros = embarqueOutros;
	}

	public Tripulante getTripulanteEmbarque() {
		return tripulanteEmbarque;
	}

	public void setTripulanteEmbarque(Tripulante tripulanteEmbarque) {
		this.tripulanteEmbarque = tripulanteEmbarque;
	}

	public List<Embarque> getEmbarqueTripulanteList() {

		listasEmbarque();

		return embarqueTripulanteList;
	}

	public void setEmbarqueTripulanteList(List<Embarque> embarqueTripulanteList) {
		this.embarqueTripulanteList = embarqueTripulanteList;
	}

	public List<Embarque> getEmbarqueFuncionarioList() {
		return embarqueFuncionarioList;
	}

	public void setEmbarqueFuncionarioList(
			List<Embarque> embarqueFuncionarioList) {
		this.embarqueFuncionarioList = embarqueFuncionarioList;
	}

	public Embarque getEmbarqueTripulante() {
		return embarqueTripulante;
	}

	public void setEmbarqueTripulante(Embarque embarqueTripulante) {
		this.embarqueTripulante = embarqueTripulante;
	}

	public List<Usuario> getTripulanteDisponivelList() {

		// getTripulanteList();
		getEmbarqueList();

		for (Tripulante tr : tripulanteList) {

			for (Embarque em : embarqueList) {
				if (em.getUsuario() == tr.getUsuario()) {
					tripulanteList.remove(tr);
				}
				tripulanteDisponivelList.add(tr.getUsuario());
			}
		}

		return tripulanteDisponivelList;
	}

	public void setTripulanteDisponivelList(
			List<Usuario> tripulanteDisponivelList) {
		this.tripulanteDisponivelList = tripulanteDisponivelList;
	}

	public Rebocador getRebocador() {
		rebocador = getTripulante().getTripulacao().getRebocador();
		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public PassagemComando getpComando() {
		return pComando;
	}

	public void setpComando(PassagemComando pComando) {
		this.pComando = pComando;
	}

}
