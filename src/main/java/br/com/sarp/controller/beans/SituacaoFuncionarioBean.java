package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import br.com.sarp.model.entidades.SituacaoFuncionario;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.SituacaoFuncionarioService;

@Controller
@ViewScoped
public class SituacaoFuncionarioBean {

	private SituacaoFuncionario situacaoFuncionario = new SituacaoFuncionario(); // recebe os dados do objeto na

	@Inject
	SituacaoFuncionarioService situacaoFuncionarioService;

	List<SituacaoFuncionario> situacaoFuncionarioList;

	public void salvar() {
		try {
			situacaoFuncionarioService.salvar(situacaoFuncionario);
			situacaoFuncionario = new SituacaoFuncionario();
			situacaoFuncionarioList = situacaoFuncionarioService.buscaTodos();
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
		situacaoFuncionarioList = situacaoFuncionarioService.buscaTodos();

	}

	public void excluir(){
		situacaoFuncionarioService.excluir(situacaoFuncionario);
		situacaoFuncionarioList = situacaoFuncionarioService.buscaTodos();
		//limpando instancia do situacaoFuncionario
		situacaoFuncionario=new SituacaoFuncionario();
	}
	
	
	public SituacaoFuncionario getSituacaoFuncionario() {
		return situacaoFuncionario;
	}

	public void setSituacaoFuncionario(SituacaoFuncionario situacaoFuncionario) {
		this.situacaoFuncionario = situacaoFuncionario;
	}

	public SituacaoFuncionarioService getSituacaoFuncionarioService() {
		return situacaoFuncionarioService;
	}

	public void setSituacaoFuncionarioService(SituacaoFuncionarioService situacaoFuncionarioService) {
		this.situacaoFuncionarioService = situacaoFuncionarioService;
	}

	public List<SituacaoFuncionario> getSituacaoFuncionarioList() {
		return situacaoFuncionarioList;
	}

	public void setSituacaoFuncionarioList(List<SituacaoFuncionario> situacaoFuncionarioList) {
		this.situacaoFuncionarioList = situacaoFuncionarioList;
	}

}
