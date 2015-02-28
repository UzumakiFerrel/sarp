package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Setor;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.SetorService;

@Controller
@ViewScoped
public class SetorBean {

	private Setor setor = new Setor(); // recebe os dados do objeto na

	@Inject
	SetorService setorService;

	List<Setor> setorList;

	public void salvar() {
		try {
			setorService.salvar(setor);
			setor = new Setor();
			setorList = setorService.buscaTodos();
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
		setorList = setorService.buscaTodos();

	}

	public void excluir(){
		setorService.excluir(setor);
		setorList = setorService.buscaTodos();
		//limpando instancia do setor
		setor=new Setor();
	}
	
	
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public SetorService getSetorService() {
		return setorService;
	}

	public void setSetorService(SetorService setorService) {
		this.setorService = setorService;
	}

	public List<Setor> getSetorList() {
		return setorList;
	}

	public void setSetorList(List<Setor> setorList) {
		this.setorList = setorList;
	}

}
