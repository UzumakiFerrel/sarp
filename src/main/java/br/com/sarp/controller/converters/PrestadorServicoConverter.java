package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.PrestadorServico;
import br.com.sarp.model.service.PrestadorServicoService;

@Named
public class PrestadorServicoConverter implements Converter {

	@Inject
	PrestadorServicoService prestadorServicoService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		try {
			PrestadorServico prestadorServico = prestadorServicoService.buscarId(Integer.parseInt(id));
			return prestadorServico;

		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Erro !"+ e.getMessage());
			return null;
		}

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object cidade) {

		
		try {
			PrestadorServico e = (PrestadorServico) cidade;
			if (e.getCd_prestador() == null || e.getCd_prestador().toString() == "")
				return null;
			return e.getCd_prestador().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
