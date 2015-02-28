package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.RemessaMaterial;
import br.com.sarp.model.service.RemessaMaterialService;

@Named
public class RemessaMaterialConverter implements Converter {

	@Inject
	RemessaMaterialService remessaMaterialService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		if (id != "") {
			try {

				Integer intid = Integer.parseInt(id);

				RemessaMaterial remessaMaterial = remessaMaterialService.buscarId(intid);
				return remessaMaterial;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object remessaMaterial) {
	
		if (remessaMaterial.equals(null))
		{
			return null;
		} else {

		try {
			RemessaMaterial e = (RemessaMaterial) remessaMaterial;
			if (e.getNum_rem() == null)
				return null;
			return e.getNum_rem().toString();
		} catch (ClassCastException erro) {
			erro.printStackTrace();
			return null;
		}
		}
	}
}
