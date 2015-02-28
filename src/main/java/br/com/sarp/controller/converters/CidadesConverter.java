package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Cidades;
import br.com.sarp.model.service.CidadesService;

@Named
public class CidadesConverter implements Converter {

	@Inject
	CidadesService cidadesService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		try {
			Cidades cidades = cidadesService.buscarId(Integer.parseInt(id));
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
			Cidades e = (Cidades) cidade;
			if (e.getCd_cid() == null || e.getCd_cid().toString() == "")
				return null;
			return e.getCd_cid().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
