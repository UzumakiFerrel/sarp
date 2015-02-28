package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.PrestadorServico;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.PrestadorServicoService;

@Controller
@ViewScoped
public class PrestadorServicoBean {

	private PrestadorServico prestadorServico = new PrestadorServico(); // recebe os dados do objeto na

	@Inject
	PrestadorServicoService prestadorServicoService;

	List<PrestadorServico> prestadorServicoList;

	public void salvar() {
		try {
			prestadorServicoService.salvar(prestadorServico);
			prestadorServico = new PrestadorServico();
			prestadorServicoList = prestadorServicoService.buscaTodos();
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
		prestadorServicoList = prestadorServicoService.buscaTodos();

	}

	public void excluir(){
		prestadorServicoService.excluir(prestadorServico);
		prestadorServicoList = prestadorServicoService.buscaTodos();
		//limpando instancia do prestadorServico
		prestadorServico=new PrestadorServico();
	}
	
	
	public PrestadorServico getPrestadorServico() {
		return prestadorServico;
	}

	public void setPrestadorServico(PrestadorServico prestadorServico) {
		this.prestadorServico = prestadorServico;
	}

	public PrestadorServicoService getPrestadorServicoService() {
		return prestadorServicoService;
	}

	public void setPrestadorServicoService(PrestadorServicoService prestadorServicoService) {
		this.prestadorServicoService = prestadorServicoService;
	}

	public List<PrestadorServico> getPrestadorServicoList() {
		return prestadorServicoList;
	}

	public void setPrestadorServicoList(List<PrestadorServico> prestadorServicoList) {
		this.prestadorServicoList = prestadorServicoList;
	}

}
