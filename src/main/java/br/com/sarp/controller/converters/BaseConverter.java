package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Base;
import br.com.sarp.model.service.BaseService;

@Named
public class BaseConverter implements Converter {

	@Inject
	BaseService baseService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		if (id != "") {
			try {

				Integer intid = Integer.parseInt(id);

				Base base = baseService.buscarId(intid);
				return base;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object base) {
	
		try {
			Base e = (Base) base;
			if (e.getCd_base()== null)
				return null;
			return e.getCd_base().toString();
		} catch (Exception erro) {
			erro.printStackTrace();
			return null;
		}
		}


	
}
