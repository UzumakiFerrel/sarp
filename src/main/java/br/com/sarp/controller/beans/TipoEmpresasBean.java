package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.TipoEmpresas;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TipoEmpresasService;

@Controller
@ViewScoped
public class TipoEmpresasBean {

	private TipoEmpresas tipoEmpresas = new TipoEmpresas(); // recebe os dados
															// do objeto na

	@Inject
	TipoEmpresasService tipoEmpresasService;

	List<TipoEmpresas> tipoEmpresasList;

	public void salvar() {
		try {
			tipoEmpresasService.salvar(tipoEmpresas);
			tipoEmpresas = new TipoEmpresas();
			tipoEmpresasList = tipoEmpresasService.buscaTodos();
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
		tipoEmpresasList = tipoEmpresasService.buscaTodos();

	}

	public void excluir() {
		tipoEmpresasService.excluir(tipoEmpresas);
		tipoEmpresasList = tipoEmpresasService.buscaTodos();
		// limpando instancia do tipoEmpresas
		tipoEmpresas = new TipoEmpresas();
	}

	public TipoEmpresas getTipoEmpresas() {
		return tipoEmpresas;
	}

	public void setTipoEmpresas(TipoEmpresas tipoEmpresas) {
		this.tipoEmpresas = tipoEmpresas;
	}

	public TipoEmpresasService getTipoEmpresasService() {
		return tipoEmpresasService;
	}

	public void setTipoEmpresasService(TipoEmpresasService tipoEmpresasService) {
		this.tipoEmpresasService = tipoEmpresasService;
	}

	public List<TipoEmpresas> getTipoEmpresasList() {
		return tipoEmpresasList;
	}

	public void setTipoEmpresasList(List<TipoEmpresas> tipoEmpresasList) {
		this.tipoEmpresasList = tipoEmpresasList;
	}

}
