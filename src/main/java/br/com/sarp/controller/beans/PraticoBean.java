package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Pratico;
import br.com.sarp.model.service.PraticoService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class PraticoBean {

	private Pratico pratico = new Pratico(); // recebe os dados do objeto na

	@Inject
	PraticoService praticoService;
	@Inject
	LoginBean loginBean;

	List<Pratico> praticoList;
	List<Pratico> praticoBaseList;

	public void salvar() {
		try {
			praticoService.salvar(pratico);
			pratico = new Pratico();
			praticoList = praticoService.buscaTodos();
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
		praticoList = praticoService.buscaTodos();

	}

	public void excluir() {
		praticoService.excluir(pratico);
		praticoList = praticoService.buscaTodos();
		// limpando instancia do pratico
		pratico = new Pratico();
	}

	public Pratico getPratico() {
		return pratico;
	}

	public void setPratico(Pratico pratico) {
		this.pratico = pratico;
	}

	public PraticoService getPraticoService() {
		return praticoService;
	}

	public void setPraticoService(PraticoService praticoService) {
		this.praticoService = praticoService;
	}

	public List<Pratico> getPraticoList() {
		return praticoList;
	}

	public void setPraticoList(List<Pratico> praticoList) {
		this.praticoList = praticoList;
	}

	public List<Pratico> getPraticoBaseList() {
praticoBaseList = praticoService.buscaTodosBase(loginBean.getLogado().getBase());
		
		return praticoBaseList;
	}

	public void setPraticoBaseList(List<Pratico> praticoBaseList) {
		this.praticoBaseList = praticoBaseList;
	}

}
