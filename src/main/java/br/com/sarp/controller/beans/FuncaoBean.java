package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Funcao;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.FuncaoService;

@Controller
@ViewScoped
public class FuncaoBean {

	private Funcao funcao = new Funcao(); // recebe os dados do objeto na

	@Inject
	FuncaoService funcaoService;

	List<Funcao> funcaoList;

	public void salvar() {
		try {
			funcaoService.salvar(funcao);
			funcao = new Funcao();
			funcaoList = funcaoService.buscaTodos();
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
		funcaoList = funcaoService.buscaTodos();

	}

	public void excluir(){
		funcaoService.excluir(funcao);
		funcaoList = funcaoService.buscaTodos();
		//limpando instancia do funcao
		funcao=new Funcao();
	}
	
	
	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public FuncaoService getFuncaoService() {
		return funcaoService;
	}

	public void setFuncaoService(FuncaoService funcaoService) {
		this.funcaoService = funcaoService;
	}

	public List<Funcao> getFuncaoList() {
		return funcaoList;
	}

	public void setFuncaoList(List<Funcao> funcaoList) {
		this.funcaoList = funcaoList;
	}

}
