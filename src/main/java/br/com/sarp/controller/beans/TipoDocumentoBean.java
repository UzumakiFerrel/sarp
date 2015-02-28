package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.TipoDocumento;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.TipoDocumentoService;

@Controller
@ViewScoped
public class TipoDocumentoBean {

	private TipoDocumento tipoDocumento = new TipoDocumento(); // recebe os dados do objeto na

	@Inject
	TipoDocumentoService tipoDocumentoService;

	List<TipoDocumento> tipoDocumentoList;

	public void salvar() {
		try {
			tipoDocumentoService.salvar(tipoDocumento);
			tipoDocumento = new TipoDocumento();
			tipoDocumentoList = tipoDocumentoService.buscaTodos();
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
		tipoDocumentoList = tipoDocumentoService.buscaTodos();

	}

	public void excluir(){
		tipoDocumentoService.excluir(tipoDocumento);
		tipoDocumentoList = tipoDocumentoService.buscaTodos();
		//limpando instancia do tipoDocumento
		tipoDocumento=new TipoDocumento();
	}
	
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public TipoDocumentoService getTipoDocumentoService() {
		return tipoDocumentoService;
	}

	public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
		this.tipoDocumentoService = tipoDocumentoService;
	}

	public List<TipoDocumento> getTipoDocumentoList() {
		return tipoDocumentoList;
	}

	public void setTipoDocumentoList(List<TipoDocumento> tipoDocumentoList) {
		this.tipoDocumentoList = tipoDocumentoList;
	}

}
