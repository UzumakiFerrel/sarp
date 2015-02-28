package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.ServiceException;
import br.com.sarp.model.service.UsuarioService;

@Controller
@ViewScoped
public class ItemRequisicaoBean {

	private Usuario usuario = new Usuario(); // recebe os dados do objeto na

	@Inject
	UsuarioService usuarioService;

	List<Usuario> usuarioList;

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
