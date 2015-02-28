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
import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.BairrosService;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.UsuarioService;

@Controller
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario(); // recebe os dados do objeto na tela

	@Inject
	UsuarioService usuarioService;

	List<Usuario> usuarioList;
	
	private List<Bairros> bairrosList;

	@Inject
	private BairrosService bairrosService;

	
	public void buscarBairros(ValueChangeEvent evento) { // verifica se usuario definiu estado para ativar o ajax
		                                                 //e atualizar as cidades

		if (evento.getNewValue() != evento.getOldValue()) {
			Cidades cidade = (Cidades) evento.getNewValue();

			bairrosList = bairrosService.buscarBairros(cidade);
		}

	}

	
	public List<Bairros> getBairrosList() {
		return bairrosList;
	}


	public void setBairrosList(List<Bairros> bairrosList) {
		this.bairrosList = bairrosList;
	}

	public BairrosService getBairrosService() {
		return bairrosService;
	}

	public void setBairrosService(BairrosService bairrosService) {
		this.bairrosService = bairrosService;
	}

	public void salvar() {
		try {
			usuarioService.salvar(usuario);
			usuario = new Usuario();
			usuarioList = usuarioService.buscaTodos();
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
		usuarioList = usuarioService.buscaTodos();

	}

	public void excluir(){
		usuarioService.excluir(usuario);
		usuarioList = usuarioService.buscaTodos();
		//limpando instancia do usuario
		usuario=new Usuario();
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

}
