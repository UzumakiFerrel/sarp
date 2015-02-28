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

import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.entidades.Ocorrencias;
import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.entidades.Solicitacoes;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.CentroCustoService;
import br.com.sarp.model.service.OcorrenciasService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.SituacaoRequisicaoService;
import br.com.sarp.model.service.SolicitacoesService;
import br.com.sarp.model.service.TripulanteService;

@Controller
@ViewScoped
public class OcorrenciasBean {

	private Ocorrencias ocorrencias = new Ocorrencias(); // recebe os dados do
															// objeto na tela
	private Tripulante tripulante = new Tripulante();
	private Rebocador rebocador = new Rebocador();
	private Usuario logado = new Usuario();
	private String abertura;
	private String solucao;
	

	List<Ocorrencias> ocorrenciasList;
	List<Ocorrencias> ocorrenciasPendentesList;
	List<Solicitacoes> solicitacoesList;

	@Inject
	OcorrenciasService ocorrenciasService;
	@Inject
	TripulanteService tripulanteService;
	@Inject
	SituacaoRequisicaoService situacaoRequisicaoService;
	@Inject
	SolicitacoesService solicitacoesService;
	@Inject
	CentroCustoService centroCustoService;

	public void salvar() {
		try {

			ocorrencias.setRebocador(getRebocador());
			ocorrencias.setTripulante(getTripulante());
			ocorrencias.setSitOcorrencia(situacaoRequisicaoService.buscarId(1));
			ocorrencias.setAbertura(getAbertura());
			ocorrenciasService.salvar(ocorrencias);
			ocorrencias = new Ocorrencias();
			ocorrenciasList = ocorrenciasService.buscaPendentes(getRebocador());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Salvo com sucesso !", null));

		} catch (ServiceException e) {
			
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Erro :: "
											+ e.getMessage(), null));
			// codigo que envia msg de erro pra tela
			e.printStackTrace();
		}

	}

	@PostConstruct
	public void init() {
		ocorrenciasList = ocorrenciasService.buscaTodos();

	}

	public void excluir() {
		ocorrenciasService.excluir(ocorrencias);
		ocorrenciasList = ocorrenciasService.buscaTodos();
		// limpando instancia do ocorrencias
		ocorrencias = new Ocorrencias();
	}

	public Ocorrencias getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(Ocorrencias ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public Tripulante getTripulante() {

		tripulante = tripulanteService.buscarPorId(getLogado().getMatricula());

		return tripulante;
	}

	public void setTripulante(Tripulante tripulante) {
		this.tripulante = tripulante;
	}

	public Rebocador getRebocador() {

		rebocador = getTripulante().getTripulacao().getRebocador();

		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public OcorrenciasService getOcorrenciasService() {
		return ocorrenciasService;
	}

	public void setOcorrenciasService(OcorrenciasService ocorrenciasService) {
		this.ocorrenciasService = ocorrenciasService;
	}

	public List<Ocorrencias> getOcorrenciasList() {
		return ocorrenciasList;
	}

	public void setOcorrenciasList(List<Ocorrencias> ocorrenciasList) {
		this.ocorrenciasList = ocorrenciasList;
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

	public String getAbertura() {

		Calendar cd = Calendar.getInstance();
		Date data = cd.getTime();
		DateFormat dt = DateFormat.getDateInstance(DateFormat.FULL);
		abertura = dt.format(data);

		return abertura;
	}

	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public List<Ocorrencias> getOcorrenciasPendentesList() {

		ocorrenciasPendentesList = ocorrenciasService
				.buscaPendentes(getRebocador());
		return ocorrenciasPendentesList;
	}

	public void setOcorrenciasPendentesList(
			List<Ocorrencias> ocorrenciasPendentesList) {
		this.ocorrenciasPendentesList = ocorrenciasPendentesList;
	}

	public List<Solicitacoes> getSolicitacoesList() {

		CentroCusto cCusto = new CentroCusto();
		
		cCusto=centroCustoService.buscarRebocador(getRebocador());
		
		solicitacoesList = solicitacoesService.buscarTodoscCusto(cCusto);

		return solicitacoesList;
	}

	public void setSolicitacoesList(List<Solicitacoes> solicitacoesList) {
		this.solicitacoesList = solicitacoesList;
	}

}
