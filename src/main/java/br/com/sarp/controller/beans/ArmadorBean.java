package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Armador;
import br.com.sarp.model.service.ArmadorService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class ArmadorBean {

	private Armador armador = new Armador(); // recebe os dados do objeto na

	@Inject
	ArmadorService armadorService;

	List<Armador> armadorList;

	public void salvar() {
		try {
			armadorService.salvar(armador);
			armador = new Armador();
			armadorList = armadorService.buscaTodos();
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
		armadorList = armadorService.buscaTodos();

	}

	public void excluir(){
		armadorService.excluir(armador);
		armadorList = armadorService.buscaTodos();
		//limpando instancia do armador
		armador=new Armador();
	}

	public Armador getArmador() {
		return armador;
	}

	public void setArmador(Armador armador) {
		this.armador = armador;
	}

	public ArmadorService getArmadorService() {
		return armadorService;
	}

	public void setArmadorService(ArmadorService armadorService) {
		this.armadorService = armadorService;
	}

	public List<Armador> getArmadorList() {
		return armadorList;
	}

	public void setArmadorList(List<Armador> armadorList) {
		this.armadorList = armadorList;
	}

	
	
}
