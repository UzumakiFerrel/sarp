package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.TipoHistoricoRebocador;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TipoHistoricoRebocadorService;

@Controller
@ViewScoped
public class TipoHistoricoRebocadorBean {

	private TipoHistoricoRebocador tipoHistoricoRebocador = new TipoHistoricoRebocador(); // recebe os dados do objeto na

	@Inject
	TipoHistoricoRebocadorService tipoHistoricoRebocadorService;

	List<TipoHistoricoRebocador> tipoHistoricoRebocadorList;

	public void salvar() {
		try {
			tipoHistoricoRebocadorService.salvar(tipoHistoricoRebocador);
			tipoHistoricoRebocador = new TipoHistoricoRebocador();
			tipoHistoricoRebocadorList = tipoHistoricoRebocadorService.buscaTodos();
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
		tipoHistoricoRebocadorList = tipoHistoricoRebocadorService.buscaTodos();

	}

	public void excluir(){
		tipoHistoricoRebocadorService.excluir(tipoHistoricoRebocador);
		tipoHistoricoRebocadorList = tipoHistoricoRebocadorService.buscaTodos();
		//limpando instancia do tipoHistoricoRebocador
		tipoHistoricoRebocador=new TipoHistoricoRebocador();
	}
	
	public TipoHistoricoRebocador getTipoHistoricoRebocador() {
		return tipoHistoricoRebocador;
	}

	public void setTipoHistoricoRebocador(TipoHistoricoRebocador tipoHistoricoRebocador) {
		this.tipoHistoricoRebocador = tipoHistoricoRebocador;
	}

	public TipoHistoricoRebocadorService getTipoHistoricoRebocadorService() {
		return tipoHistoricoRebocadorService;
	}

	public void setTipoHistoricoRebocadorService(TipoHistoricoRebocadorService tipoHistoricoRebocadorService) {
		this.tipoHistoricoRebocadorService = tipoHistoricoRebocadorService;
	}

	public List<TipoHistoricoRebocador> getTipoHistoricoRebocadorList() {
		return tipoHistoricoRebocadorList;
	}

	public void setTipoHistoricoRebocadorList(List<TipoHistoricoRebocador> tipoHistoricoRebocadorList) {
		this.tipoHistoricoRebocadorList = tipoHistoricoRebocadorList;
	}

}
