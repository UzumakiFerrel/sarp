package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.service.CentroCustoService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class CentroCustoBean {

	private CentroCusto centroCusto = new CentroCusto(); // recebe os dados do objeto na

	@Inject
	CentroCustoService centroCustoService;

	List<CentroCusto> centroCustoList;

	public void salvar() {
		try {
			centroCustoService.salvar(centroCusto);
			centroCusto = new CentroCusto();
			centroCustoList = centroCustoService.buscaTodos();
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
		centroCustoList = centroCustoService.buscaTodos();

	}

	public void excluir(){
		centroCustoService.excluir(centroCusto);
		centroCustoList = centroCustoService.buscaTodos();
		//limpando instancia do centroCusto
		centroCusto=new CentroCusto();
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	public CentroCustoService getCentroCustoService() {
		return centroCustoService;
	}

	public void setCentroCustoService(CentroCustoService centroCustoService) {
		this.centroCustoService = centroCustoService;
	}

	public List<CentroCusto> getCentroCustoList() {
		return centroCustoList;
	}

	public void setCentroCustoList(List<CentroCusto> centroCustoList) {
		this.centroCustoList = centroCustoList;
	}

	
	
}
