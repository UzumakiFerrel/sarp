package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Bairros;
import br.com.sarp.model.entidades.Cidades;
import br.com.sarp.model.entidades.Estados;
import br.com.sarp.model.service.BairrosService;
import br.com.sarp.model.service.CidadesService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class BairrosBean {

	private Bairros bairros = new Bairros(); // recebe os dados do objeto na

	@Inject
	BairrosService bairrosService;

	List<Bairros> bairrosList;

	private List<Cidades> cidadesList;

	@Inject
	private CidadesService cidadesService;

	public void buscarCidades(ValueChangeEvent evento) { // verifica se usuario definiu estado para ativar o ajax
		                                                 //e atualizar as cidades

		if (evento.getNewValue() != evento.getOldValue()) {
			Estados estado = (Estados) evento.getNewValue();

			cidadesList = cidadesService.buscarCidades(estado);
		}

	}

	public void salvar() {
		try {
			bairrosService.salvar(bairros);
			bairros = new Bairros();
			bairrosList = bairrosService.buscaTodos();
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
		bairrosList = bairrosService.buscaTodos();

	}

	public void excluir(){
		bairrosService.excluir(bairros);
		bairrosList = bairrosService.buscaTodos();
		//limpando instancia do bairros
		bairros=new Bairros();
	}

	public Bairros getBairros() {
		return bairros;
	}

	public void setBairros(Bairros bairros) {
		this.bairros = bairros;
	}

	public BairrosService getBairrosService() {
		return bairrosService;
	}

	public void setBairrosService(BairrosService bairrosService) {
		this.bairrosService = bairrosService;
	}

	public List<Bairros> getBairrosList() {
		return bairrosList;
	}

	public void setBairrosList(List<Bairros> bairrosList) {
		this.bairrosList = bairrosList;
	}

	public List<Cidades> getCidadesList() {
		return cidadesList;
	}

	public void setCidadesList(List<Cidades> cidadesList) {
		this.cidadesList = cidadesList;
	}

	public CidadesService getCidadesService() {
		return cidadesService;
	}

	public void setCidadesService(CidadesService cidadesService) {
		this.cidadesService = cidadesService;
	}
	
}
