package br.com.sarp.controller.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.sarp.model.entidades.CategoriaMaritimo;
import br.com.sarp.model.service.CategoriaMaritimoService;
import br.com.sarp.model.service.ServiceException;

@Controller
@ViewScoped
public class CategoriaMaritimoBean {

	private CategoriaMaritimo categoriaMaritimo = new CategoriaMaritimo(); // recebe os dados do objeto na

	@Inject
	CategoriaMaritimoService categoriaMaritimoService;

	List<CategoriaMaritimo> categoriaMaritimoList;


	public void salvar() {
		try {
			categoriaMaritimoService.salvar(categoriaMaritimo);
			categoriaMaritimo = new CategoriaMaritimo();
			categoriaMaritimoList = categoriaMaritimoService.buscaTodos();
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
		categoriaMaritimoList = categoriaMaritimoService.buscaTodos();

	}

	public void excluir() {
		categoriaMaritimoService.excluir(categoriaMaritimo);
		categoriaMaritimoList = categoriaMaritimoService.buscaTodos();
		// limpando instancia do categoriaMaritimo
		categoriaMaritimo = new CategoriaMaritimo();
	}

	public CategoriaMaritimo getCategoriaMaritimo() {
		return categoriaMaritimo;
	}

	public void setCategoriaMaritimo(CategoriaMaritimo categoriaMaritimo) {
		this.categoriaMaritimo = categoriaMaritimo;
	}

	public CategoriaMaritimoService getCategoriaMaritimoService() {
		return categoriaMaritimoService;
	}

	public void setCategoriaMaritimoService(
			CategoriaMaritimoService categoriaMaritimoService) {
		this.categoriaMaritimoService = categoriaMaritimoService;
	}

	public List<CategoriaMaritimo> getCategoriaMaritimoList() {
		return categoriaMaritimoList;
	}

	public void setCategoriaMaritimoList(
			List<CategoriaMaritimo> categoriaMaritimoList) {
		this.categoriaMaritimoList = categoriaMaritimoList;
	}


	
}
