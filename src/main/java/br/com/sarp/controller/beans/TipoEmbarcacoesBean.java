package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.TipoEmbarcacoes;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TipoEmbarcacoesService;

@Controller
@ViewScoped
public class TipoEmbarcacoesBean {

	private TipoEmbarcacoes tipoEmbarcacoes = new TipoEmbarcacoes(); // recebe os dados do objeto na

	@Inject
	TipoEmbarcacoesService tipoEmbarcacoesService;

	List<TipoEmbarcacoes> tipoEmbarcacoesList;

	public void salvar() {
		try {
			tipoEmbarcacoesService.salvar(tipoEmbarcacoes);
			tipoEmbarcacoes = new TipoEmbarcacoes();
			tipoEmbarcacoesList = tipoEmbarcacoesService.buscaTodos();
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
		tipoEmbarcacoesList = tipoEmbarcacoesService.buscaTodos();

	}

	public void excluir(){
		tipoEmbarcacoesService.excluir(tipoEmbarcacoes);
		tipoEmbarcacoesList = tipoEmbarcacoesService.buscaTodos();
		//limpando instancia do tipoEmbarcacoes
		tipoEmbarcacoes=new TipoEmbarcacoes();
	}
	
	public TipoEmbarcacoes getTipoEmbarcacoes() {
		return tipoEmbarcacoes;
	}

	public void setTipoEmbarcacoes(TipoEmbarcacoes tipoEmbarcacoes) {
		this.tipoEmbarcacoes = tipoEmbarcacoes;
	}

	public TipoEmbarcacoesService getTipoEmbarcacoesService() {
		return tipoEmbarcacoesService;
	}

	public void setTipoEmbarcacoesService(TipoEmbarcacoesService tipoEmbarcacoesService) {
		this.tipoEmbarcacoesService = tipoEmbarcacoesService;
	}

	public List<TipoEmbarcacoes> getTipoEmbarcacoesList() {
		return tipoEmbarcacoesList;
	}

	public void setTipoEmbarcacoesList(List<TipoEmbarcacoes> tipoEmbarcacoesList) {
		this.tipoEmbarcacoesList = tipoEmbarcacoesList;
	}

}
