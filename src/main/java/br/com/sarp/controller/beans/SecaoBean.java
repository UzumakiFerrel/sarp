package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Secao;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.SecaoService;

@Controller
@ViewScoped
public class SecaoBean {

	private Secao secao = new Secao(); // recebe os dados do objeto na

	@Inject
	SecaoService secaoService;

	List<Secao> secaoList;

	public void salvar() {
		try {
			secaoService.salvar(secao);
			secao = new Secao();
			secaoList = secaoService.buscaTodos();
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
		secaoList = secaoService.buscaTodos();

	}

	public void excluir(){
		secaoService.excluir(secao);
		secaoList = secaoService.buscaTodos();
		//limpando instancia do secao
		secao=new Secao();
	}
	
	
	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public SecaoService getSecaoService() {
		return secaoService;
	}

	public void setSecaoService(SecaoService secaoService) {
		this.secaoService = secaoService;
	}

	public List<Secao> getSecaoList() {
		return secaoList;
	}

	public void setSecaoList(List<Secao> secaoList) {
		this.secaoList = secaoList;
	}

}
