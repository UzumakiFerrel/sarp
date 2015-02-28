package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Unidades;
import br.com.sarp.model.service.UnidadesService;

@Named
public class UnidadesConverter implements Converter {

	@Inject
	UnidadesService cidadesService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		try {
			Unidades cidades = cidadesService.buscarId(Integer.parseInt(id));
			return cidades;

		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Erro !"+ e.getMessage());
			return null;
		}

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object cidade) {

		
		try {
			Unidades e = (Unidades) cidade;
			if (e.getCd_unidades() == null || e.getCd_unidades().toString() == "")
				return null;
			return e.getCd_unidades().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
