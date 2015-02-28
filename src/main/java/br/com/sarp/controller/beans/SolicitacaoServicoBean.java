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

import br.com.sarp.model.dao.DAOException;
import br.com.sarp.model.entidades.Servicos;
import br.com.sarp.model.entidades.SolicitacaoServico;
import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.CentroCustoService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.ServicosService;
import br.com.sarp.model.service.SituacaoRequisicaoService;
import br.com.sarp.model.service.SolicitacaoServicoService;
import br.com.sarp.model.service.TipoMoveService;
import br.com.sarp.model.service.TripulanteService;

@Controller
@ViewScoped
public class SolicitacaoServicoBean {

	private Usuario logado = new Usuario();
	private Tripulante tripulante = new Tripulante();
	private SolicitacaoServico solicitacaoServico = new SolicitacaoServico();
	private List<SolicitacaoServico> solicitacaoServicoList;
	private List<Servicos> servicosList;

	@Inject
	ServicosService servicosService;
	@Inject
	SolicitacaoServicoService solicitacaoServicoService;
	@Inject
	TripulanteService tripulanteService;
	@Inject
	CentroCustoService centroCustoService;
	@Inject
	TipoMoveService tipoMoveService;
	@Inject
	SituacaoRequisicaoService situacaoRequisicaoService;

	public void salvar() throws ServiceException {

		solicitacaoServico = new SolicitacaoServico();
		solicitacaoServicoList = solicitacaoServicoService.buscaTodos();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Salvo com sucesso !", null));

	}

	/*
	 * public void salvaItemRequisicao() {
	 * 
	 * // esta funcao salva os item da requisicao em uma lista // os itens serao
	 * salvos no banco ao salvar a requisicao
	 * 
	 * System.out.println("Item :" + getInsumo().getCd_insumo());
	 * System.out.println("Descricao item :" + getInsumo().getDescricao());
	 * 
	 * Calendar c = Calendar.getInstance(); Date data = c.getTime(); DateFormat
	 * f = DateFormat.getDateInstance(); String abertura = f.format(data);
	 * numItem = numItem + 1;
	 * 
	 * itens.setData(abertura); //itens.setRequisicao(getSolicitacaoServico());
	 * itens.setNumItem(numItem);
	 * itens.setSitInsumo(tipoMoveService.buscarId(1));
	 * itens.setPendente(itens.getQuantidade()); //
	 * itens.setInsumo(getInsumo()); System.out.println("Itens Id :" +
	 * itens.getiD()); System.out.println("Itens Data :" + itens.getData());
	 * System.out.println("Itens Requisicao :" +
	 * itens.getRequisicao().getNum_req()); System.out.println("Itens Insumo :"
	 * + itens.getInsumo().getCd_insumo());
	 * System.out.println("Itens quantidade :" + itens.getQuantidade());
	 * 
	 * // impede que duplique os item no list if
	 * (itemRequisicaoList.contains(itens) == true) {
	 * itemRequisicaoList.remove(itens); }
	 * 
	 * itemRequisicaoList.add(itens); itens = new ItemRequisicao(); insumo = new
	 * CadastroInsumo();
	 * 
	 * }
	 * 
	 * 
	 * public void CancelaItens() {
	 * 
	 * itemRequisicaoList.clear(); numItem = 0; }
	 * 
	 * public void ExcluirItem() {
	 * 
	 * itemRequisicaoList.remove(itens); // atualisar numitem itens = new
	 * ItemRequisicao(); insumo = new CadastroInsumo();
	 * 
	 * }
	 * 
	 * public List<CadastroInsumo> listInsumo(String query) { // metodo de
	 * pesquisa // do autocomplete
	 * 
	 * insumoList = cadastroInsumoService.buscaQueryDescricao(query);
	 * 
	 * return insumoList;
	 * 
	 * }
	 */

	@PostConstruct
	public void init() {
		// cria o cabecalho da requisicao

	}

	public void excluir() {
		solicitacaoServicoService.excluir(solicitacaoServico);
		solicitacaoServicoList = solicitacaoServicoService.buscaTodos();
		// limpando instancia do solicitacaoServico
		solicitacaoServico = new SolicitacaoServico();
	}

	public void cancelarSolicitacaoServico() throws DAOException,

	ServiceException {

		solicitacaoServicoService
				.cancelarSolicitacaoServico(solicitacaoServico);
	}

	public List<Servicos> listServico(String query) {

		servicosList = servicosService.buscaQueryDescricao(query);

		return servicosList;

	}

	public SolicitacaoServico getSolicitacaoServico() {

		Integer numreq = solicitacaoServicoService.buscarUltimo();
		if (numreq == null) {
			numreq = 0;
		}
		numreq = numreq + 1;

		Calendar c = Calendar.getInstance();
		@SuppressWarnings("unused")
		Date data = c.getTime();
		@SuppressWarnings("unused")
		DateFormat f = DateFormat.getDateInstance();
		// String abertura = f.format(data);

		solicitacaoServico.setNum_sserv(numreq);// definindo o numero da
												// requisicao

		// verificar qual usuario se
		// for maritmo pegar rebocador
		// senao pegar setor

		if (tripulante.getTripulacao().getRebocador() != null) {

			solicitacaoServico
					.setcCustoOrigem(centroCustoService
							.buscarRebocador(tripulante.getTripulacao()
									.getRebocador()));
		} 
		/*
		else {
			solicitacaoServico.setcCustoOrigem(centroCustoService
					.buscarSetor(logado.getSetor()));
		}
*/
		solicitacaoServico.setSolicitante(logado);
		solicitacaoServico.setSit(situacaoRequisicaoService.buscarId(1));

		return solicitacaoServico;
	}

	public void setSolicitacaoServico(SolicitacaoServico solicitacaoServico) {
		this.solicitacaoServico = solicitacaoServico;
	}

	public SolicitacaoServicoService getSolicitacaoServicoService() {
		return solicitacaoServicoService;
	}

	public void setSolicitacaoServicoService(
			SolicitacaoServicoService solicitacaoServicoService) {
		this.solicitacaoServicoService = solicitacaoServicoService;
	}

	public List<SolicitacaoServico> getSolicitacaoServicoList() {
		return solicitacaoServicoList;
	}

	public void setSolicitacaoServicoList(
			List<SolicitacaoServico> solicitacaoServicoList) {
		this.solicitacaoServicoList = solicitacaoServicoList;
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

		tripulante = tripulanteService.buscarPorId(logado.getMatricula());
		return tripulante;
	}

}
