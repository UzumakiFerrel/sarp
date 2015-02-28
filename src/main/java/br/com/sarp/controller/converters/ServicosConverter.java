package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Servicos;
import br.com.sarp.model.service.ServicosService;

@Named
public class ServicosConverter implements Converter {

	@Inject
	ServicosService servicosService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		if (id != "") {
			try {

				Integer intid = Integer.parseInt(id);

				Servicos servicos = servicosService.buscarId(intid);
				return servicos;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object servicos) {
	
		try {
			Servicos e = (Servicos) servicos;
			if (e.getCd_servicos()== null)
				return null;
			return e.getCd_servicos().toString();
		} catch (Exception erro) {
			erro.printStackTrace();
			return null;
		}
		}


	
}
