package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Embarcacoes;
import br.com.sarp.model.service.EmbarcacoesService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class EmbarcacoesBean {

	private Embarcacoes embarcacoes = new Embarcacoes(); // recebe os dados do objeto na

	@Inject
	EmbarcacoesService embarcacoesService;

	List<Embarcacoes> embarcacoesList;
	
	List<Embarcacoes> Listaembarcacoes;
	
	
	public void salvar() {
		try {
			embarcacoesService.salvar(embarcacoes);
			embarcacoes = new Embarcacoes();
			embarcacoesList = embarcacoesService.buscaTodos();
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
		embarcacoesList = embarcacoesService.buscaTodos();

	}

	public void excluir(){
		embarcacoesService.excluir(embarcacoes);
		embarcacoesList = embarcacoesService.buscaTodos();
		//limpando instancia do embarcacoes
		embarcacoes=new Embarcacoes();
	}

	public Embarcacoes getEmbarcacoes() {
		return embarcacoes;
	}

	public void setEmbarcacoes(Embarcacoes embarcacoes) {
		this.embarcacoes = embarcacoes;
	}

	public EmbarcacoesService getEmbarcacoesService() {
		return embarcacoesService;
	}

	public void setEmbarcacoesService(EmbarcacoesService embarcacoesService) {
		this.embarcacoesService = embarcacoesService;
	}

	public List<Embarcacoes> getEmbarcacoesList() {
		return embarcacoesList;
	}

	
	
/*	
	public void buscarCidades(ValueChangeEvent evento) { // verifica se usuario definiu estado para ativar o ajax
        //e atualizar as cidades

if (evento.getNewValue() != evento.getOldValue()) {
Estados estado = (Estados) evento.getNewValue();

cidadesList = cidadesService.buscarCidades(estado);
}

}
*/
	
	
	public List<Embarcacoes> buscarEmbarcacoes(String query){
			
			Listaembarcacoes= embarcacoesService.buscaTodos();
		
		return Listaembarcacoes;
	}
	
	
	
	public List<Embarcacoes> getListaembarcacoes() {
		return Listaembarcacoes;
	}

	public void setListaembarcacoes(List<Embarcacoes> listaembarcacoes) {
		Listaembarcacoes = listaembarcacoes;
	}

	public void setEmbarcacoesList(List<Embarcacoes> embarcacoesList) {
		this.embarcacoesList = embarcacoesList;
	}

	
	
}
