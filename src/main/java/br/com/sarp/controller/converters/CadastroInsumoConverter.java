package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.CadastroInsumo;
import br.com.sarp.model.service.CadastroInsumoService;

@Named
public class CadastroInsumoConverter implements Converter {

	@Inject
	CadastroInsumoService cadastroInsumoService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		try {
			CadastroInsumo cadastroInsumo = cadastroInsumoService.buscarId(Integer.parseInt(id));
			return cadastroInsumo;

		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Erro !"+ e.getMessage());
			return null;
		}

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object cadastroInsumo) {

		
		try {
			CadastroInsumo e = (CadastroInsumo) cadastroInsumo;
			if (e.getCd_insumo() == null || e.getCd_insumo().toString() == "")
				return null;
			return e.getCd_insumo().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
