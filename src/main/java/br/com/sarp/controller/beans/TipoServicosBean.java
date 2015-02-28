package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.TipoServicos;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TipoServicosService;

@Controller
@ViewScoped
public class TipoServicosBean {

	private TipoServicos tipoServicos = new TipoServicos(); // recebe os dados do objeto na

	@Inject
	TipoServicosService tipoServicosService;

	List<TipoServicos> tipoServicosList;

	public void salvar() {
		try {
			tipoServicosService.salvar(tipoServicos);
			tipoServicos = new TipoServicos();
			tipoServicosList = tipoServicosService.buscaTodos();
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
		tipoServicosList = tipoServicosService.buscaTodos();

	}

	public void excluir(){
		tipoServicosService.excluir(tipoServicos);
		tipoServicosList = tipoServicosService.buscaTodos();
		//limpando instancia do tipoServicos
		tipoServicos=new TipoServicos();
	}
	
	public TipoServicos getTipoServicos() {
		return tipoServicos;
	}

	public void setTipoServicos(TipoServicos tipoServicos) {
		this.tipoServicos = tipoServicos;
	}

	public TipoServicosService getTipoServicosService() {
		return tipoServicosService;
	}

	public void setTipoServicosService(TipoServicosService tipoServicosService) {
		this.tipoServicosService = tipoServicosService;
	}

	public List<TipoServicos> getTipoServicosList() {
		return tipoServicosList;
	}

	public void setTipoServicosList(List<TipoServicos> tipoServicosList) {
		this.tipoServicosList = tipoServicosList;
	}

}
