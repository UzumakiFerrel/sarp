package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Empresas;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.EmpresasService;

@Controller
@ViewScoped
public class EmpresasBean {

	private Empresas empresas = new Empresas(); // recebe os dados do objeto na

	@Inject
	EmpresasService empresasService;

	List<Empresas> empresasList;

	public void salvar() {
		try {
			empresasService.salvar(empresas);
			empresas = new Empresas();
			empresasList = empresasService.buscaTodos();
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
		empresasList = empresasService.buscaTodos();

	}

	public void excluir(){
		empresasService.excluir(empresas);
		empresasList = empresasService.buscaTodos();
		//limpando instancia do empresas
		empresas=new Empresas();
	}
	
	
	public Empresas getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Empresas empresas) {
		this.empresas = empresas;
	}

	public EmpresasService getEmpresasService() {
		return empresasService;
	}

	public void setEmpresasService(EmpresasService empresasService) {
		this.empresasService = empresasService;
	}

	public List<Empresas> getEmpresasList() {
		return empresasList;
	}

	public void setEmpresasList(List<Empresas> empresasList) {
		this.empresasList = empresasList;
	}

}
