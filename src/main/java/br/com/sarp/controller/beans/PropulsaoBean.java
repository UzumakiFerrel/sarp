package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Propulsao;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.PropulsaoService;

@Controller
@ViewScoped
public class PropulsaoBean {

	private Propulsao propulsao = new Propulsao(); // recebe os dados do objeto na

	@Inject
	PropulsaoService propulsaoService;

	List<Propulsao> propulsaoList;

	public void salvar() {
		try {
			propulsaoService.salvar(propulsao);
			propulsao = new Propulsao();
			propulsaoList = propulsaoService.buscaTodos();
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
		propulsaoList = propulsaoService.buscaTodos();

	}

	public void excluir(){
		propulsaoService.excluir(propulsao);
		propulsaoList = propulsaoService.buscaTodos();
		//limpando instancia do propulsao
		propulsao=new Propulsao();
	}
	
	
	public Propulsao getPropulsao() {
		return propulsao;
	}

	public void setPropulsao(Propulsao propulsao) {
		this.propulsao = propulsao;
	}

	public PropulsaoService getPropulsaoService() {
		return propulsaoService;
	}

	public void setPropulsaoService(PropulsaoService propulsaoService) {
		this.propulsaoService = propulsaoService;
	}

	public List<Propulsao> getPropulsaoList() {
		return propulsaoList;
	}

	public void setPropulsaoList(List<Propulsao> propulsaoList) {
		this.propulsaoList = propulsaoList;
	}

}
