package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Secao;
import br.com.sarp.model.service.SecaoService;

@Named
public class SecaoConverter implements Converter {

	@Inject
	SecaoService secaoService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		try {
			Integer intid = Integer.parseInt(id);
			Secao secao = secaoService.buscarId(intid);
			return secao;

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object secao) {

		try {
			Secao e = (Secao) secao;
			if (e.getCd_secao()== null)
				return null;
			return e.getCd_secao().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
