package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.FuncaoMaritimo;
import br.com.sarp.model.service.FuncaoMaritimoService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class FuncaoMaritimoBean {

	private FuncaoMaritimo funcaoMaritimo = new FuncaoMaritimo(); // recebe os dados do objeto na

	@Inject
	FuncaoMaritimoService funcaoMaritimoService;

	List<FuncaoMaritimo> funcaoMaritimoList;


	public void salvar() {
		try {
			funcaoMaritimoService.salvar(funcaoMaritimo);
			funcaoMaritimo = new FuncaoMaritimo();
			funcaoMaritimoList = funcaoMaritimoService.buscaTodos();
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
		funcaoMaritimoList = funcaoMaritimoService.buscaTodos();

	}

	public void excluir() {
		funcaoMaritimoService.excluir(funcaoMaritimo);
		funcaoMaritimoList = funcaoMaritimoService.buscaTodos();
		// limpando instancia do funcaoMaritimo
		funcaoMaritimo = new FuncaoMaritimo();
	}

	public FuncaoMaritimo getFuncaoMaritimo() {
		return funcaoMaritimo;
	}

	public void setFuncaoMaritimo(FuncaoMaritimo funcaoMaritimo) {
		this.funcaoMaritimo = funcaoMaritimo;
	}

	public FuncaoMaritimoService getFuncaoMaritimoService() {
		return funcaoMaritimoService;
	}

	public void setFuncaoMaritimoService(
			FuncaoMaritimoService funcaoMaritimoService) {
		this.funcaoMaritimoService = funcaoMaritimoService;
	}

	public List<FuncaoMaritimo> getFuncaoMaritimoList() {
		return funcaoMaritimoList;
	}

	public void setFuncaoMaritimoList(
			List<FuncaoMaritimo> funcaoMaritimoList) {
		this.funcaoMaritimoList = funcaoMaritimoList;
	}


	
}
