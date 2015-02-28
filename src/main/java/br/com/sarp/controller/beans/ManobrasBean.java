package br.com.sarp.controller.beans;

import java.text.DateFormat;
import java.text.ParseException;
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

import br.com.sarp.model.entidades.Manobras;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.EmbarqueService;
import br.com.sarp.model.service.ManobrasService;
import br.com.sarp.model.service.RebocadorService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TripulanteService;

@Controller
@ViewScoped
public class ManobrasBean {

	private Manobras manobras = new Manobras(); // recebe os dados do objeto na
	private Usuario logado = new Usuario();
	private Tripulante tripulante = new Tripulante();
	private Rebocador rebocador = new Rebocador();
	private Rebocador rbRelatorio = new Rebocador();
	private String hrinicio, hrfim;
	private String data, dtinicio, dtfim;
	private Date dataCalendario, dataInicio, dataFim;
	private boolean alterar = false;

	@Inject
	ManobrasService manobrasService;
	@Inject
	TripulanteService tripulanteService;
	@Inject
	RebocadorService rebocadorService;
	@Inject
	EmbarqueService embarqueService;

	List<Manobras> manobrasList;
	List<Manobras> manobrasDiaList;
	List<Manobras> manobrasDiaRbList;
	List<Manobras> manobrasPeriodoList;
	List<Manobras> manobrasPeriodoRbList;

	public void salvar() {
		try {

			if (alterar == false) {// salvando uma manobra nova

				Integer mes, ano, dia;
				Calendar calendar = Calendar.getInstance();
				dia = calendar.get(Calendar.DAY_OF_MONTH);
				mes = calendar.get(Calendar.MONTH);
				ano = calendar.get(Calendar.YEAR);

				manobras.setAno(ano);
				manobras.setDia(dia);
				manobras.setMes(mes);
				manobras.setRebocador(getRebocador());

				Integer manobra = 0;

				manobra = manobrasService.buscarNumeroManobra(
						manobras.getMes(), manobras.getAno());
				manobra = manobra + 1;
				manobras.setCd_manobras(manobra);
			}

			manobrasService.salvar(manobras);
			listaManobrasDiaRb();
			manobras = new Manobras();
			manobrasList = manobrasService.buscaTodos();
			alterar = false;
			getManobras();
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

	@PostConstruct
	public void init() {
		manobrasList = manobrasService.buscaTodos();
		Calendar calendar = Calendar.getInstance();
		dataCalendario = calendar.getTime();
		
	}

	public void excluir() {
		manobrasService.excluir(manobras);
		manobrasList = manobrasService.buscaTodos();
		// limpando instancia do manobras
		manobras = new Manobras();
	}

	public void cancelar() {
		alterar = false;
		manobras = new Manobras();
		getManobras();

	}

	public void alterarManobras() {

		setAlterar(true);

	}

	public Manobras getManobras() {

		return manobras;

	}

	public void listaManobrasDiaRb() {

		Integer dia, mes, ano;
		Calendar calendar = Calendar.getInstance();
		Date d = dataCalendario;
		calendar.setTime(d);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		mes = calendar.get(Calendar.MONTH);
		ano = calendar.get(Calendar.YEAR);

		manobrasDiaRbList = manobrasService.buscarManobraDiaRb(dia, mes, ano,
				getRebocador());

	}

	public void listaManobrasDia() throws ParseException {

		Integer dia, mes, ano;
		Calendar calendar = Calendar.getInstance();
		Date d = dataCalendario;
		calendar.setTime(d);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		mes = calendar.get(Calendar.MONTH);
		ano = calendar.get(Calendar.YEAR);

		manobrasDiaList = manobrasService.buscarManobraDia(dia, mes, ano);

		if (manobrasDiaList == null) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Nao existe MANOBRAS para Este Dia ! ", null));

		}
	}

	public void listaManobrasPeriodo() throws ParseException {

		Integer iniciodia, iniciomes, inicioano, fimdia, fimmes, fimano;
		Calendar calendar = Calendar.getInstance();
		Date d = dataInicio;
		calendar.setTime(d);
		iniciodia = calendar.get(Calendar.DAY_OF_MONTH);
		iniciomes = calendar.get(Calendar.MONTH);
		inicioano = calendar.get(Calendar.YEAR);
		d = dataFim;
		calendar.setTime(d);
		fimdia = calendar.get(Calendar.DAY_OF_MONTH);
		fimmes = calendar.get(Calendar.MONTH);
		fimano = calendar.get(Calendar.YEAR);

		manobrasPeriodoList = manobrasService.buscarManobraPeriodo(iniciodia,
				iniciomes, inicioano, fimdia, fimmes, fimano);
	}

	public void listaManobrasPeriodoRb() {

		Integer iniciodia, iniciomes, inicioano, fimdia, fimmes, fimano;
		Calendar calendar = Calendar.getInstance();
		Date d = dataInicio;
		calendar.setTime(d);
		iniciodia = calendar.get(Calendar.DAY_OF_MONTH);
		iniciomes = calendar.get(Calendar.MONTH);
		inicioano = calendar.get(Calendar.YEAR);
		d = dataFim;
		calendar.setTime(d);
		fimdia = calendar.get(Calendar.DAY_OF_MONTH);
		fimmes = calendar.get(Calendar.MONTH);
		fimano = calendar.get(Calendar.YEAR);

		manobrasPeriodoRbList = manobrasService.buscarManobraPeriodoRb(
				iniciodia, iniciomes, inicioano, fimdia, fimmes, fimano,
				getRbRelatorio());

	}

	public void setManobras(Manobras manobras) {
		this.manobras = manobras;
	}

	public ManobrasService getManobrasService() {
		return manobrasService;
	}

	public void setManobrasService(ManobrasService manobrasService) {
		this.manobrasService = manobrasService;
	}

	public List<Manobras> getManobrasList() {
		return manobrasList;
	}

	public void setManobrasList(List<Manobras> manobrasList) {
		this.manobrasList = manobrasList;
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

	public Rebocador getRebocador() {

		rebocador = embarqueService.buscarRebocadorEmbarque(getLogado());

		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public String getHrinicio() {

		Calendar calendar = Calendar.getInstance();
		Date data = calendar.getTime();

		DateFormat dt = DateFormat.getTimeInstance();

		hrinicio = dt.format(data);

		return hrinicio;
	}

	public void setHrinicio(String hrinicio) {
		this.hrinicio = hrinicio;
	}

	public String getHrfim() {

		return hrfim;
	}

	public void setHrfim(String hrfim) {
		this.hrfim = hrfim;
	}

	public TripulanteService getTripulanteService() {
		return tripulanteService;
	}

	public void setTripulanteService(TripulanteService tripulanteService) {
		this.tripulanteService = tripulanteService;
	}

	public RebocadorService getRebocadorService() {
		return rebocadorService;
	}

	public void setRebocadorService(RebocadorService rebocadorService) {
		this.rebocadorService = rebocadorService;
	}

	public EmbarqueService getEmbarqueService() {
		return embarqueService;
	}

	public void setEmbarqueService(EmbarqueService embarqueService) {
		this.embarqueService = embarqueService;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Manobras> getManobrasDiaList() throws ParseException {

		Integer dia, mes, ano;
		Calendar calendar = Calendar.getInstance();
		Date d = getDataCalendario();
		calendar.setTime(d);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		mes = calendar.get(Calendar.MONTH);
		ano = calendar.get(Calendar.YEAR);

		manobrasDiaList = manobrasService.buscarManobraDia(dia, mes, ano);

		return manobrasDiaList;
	}

	public List<Manobras> getManobrasPeriodoList() {
		return manobrasPeriodoList;
	}

	public void setManobrasPeriodoList(List<Manobras> manobrasPeriodoList) {
		this.manobrasPeriodoList = manobrasPeriodoList;
	}

	public void setManobrasDiaList(List<Manobras> manobrasDiaList) {
		this.manobrasDiaList = manobrasDiaList;
	}

	public String getDtfim() {
		return dtfim;
	}

	public void setDtfim(String dtfim) {
		this.dtfim = dtfim;
	}

	public String getDtinicio() {
		return dtinicio;
	}

	public void setDtinicio(String dtinicio) {
		this.dtinicio = dtinicio;
	}

	public Date getDataCalendario() {
		return dataCalendario;
	}

	public void setDataCalendario(Date dataCalendario) {
		this.dataCalendario = dataCalendario;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Rebocador getRbRelatorio() {
		return rbRelatorio;
	}

	public void setRbRelatorio(Rebocador rbRelatorio) {
		this.rbRelatorio = rbRelatorio;
	}

	public List<Manobras> getManobrasPeriodoRbList() {
		return manobrasPeriodoRbList;
	}

	public void setManobrasPeriodoRbList(List<Manobras> manobrasPeriodoRbList) {
		this.manobrasPeriodoRbList = manobrasPeriodoRbList;
	}

	public List<Manobras> getManobrasDiaRbList() {
		return manobrasDiaRbList;
	}

	public void setManobrasDiaRbList(List<Manobras> manobrasDiaRbList) {
		this.manobrasDiaRbList = manobrasDiaRbList;
	}

}
