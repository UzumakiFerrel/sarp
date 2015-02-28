package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Solicitacoes;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.SolicitacoesService;

@Controller
@ViewScoped
public class SolicitacoesBean {

	private Solicitacoes solicitacoes = new Solicitacoes(); // recebe os dados do objeto na

	@Inject
	SolicitacoesService solicitacoesService;

	List<Solicitacoes> solicitacoesList;

	public void salvar() {
		try {
			solicitacoesService.salvar(solicitacoes);
			solicitacoes = new Solicitacoes();
			solicitacoesList = solicitacoesService.buscaTodos();
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
		solicitacoesList = solicitacoesService.buscaTodos();

	}

	public void excluir() {
		solicitacoesService.excluir(solicitacoes);
		solicitacoesList = solicitacoesService.buscaTodos();
		// limpando instancia do solicitacoes
		solicitacoes = new Solicitacoes();
	}

	public Solicitacoes getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(Solicitacoes solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public SolicitacoesService getSolicitacoesService() {
		return solicitacoesService;
	}

	public void setSolicitacoesService(SolicitacoesService solicitacoesService) {
		this.solicitacoesService = solicitacoesService;
	}

	public List<Solicitacoes> getSolicitacoesList() {
		return solicitacoesList;
	}

	public void setSolicitacoesList(List<Solicitacoes> solicitacoesList) {
		this.solicitacoesList = solicitacoesList;
	}

}
