package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Ocorrencias;
import br.com.sarp.model.service.OcorrenciasService;

@Named
public class OcorrenciasConverter implements Converter {

	@Inject
	OcorrenciasService ocorrenciasService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		if (id != "") {
			try {

				Integer intid = Integer.parseInt(id);

				Ocorrencias ocorrencias = ocorrenciasService.buscarId(intid);
				return ocorrencias;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object ocorrencias) {
	
		
		try {
			Ocorrencias e = (Ocorrencias) ocorrencias;
			if (e.getCd_Ocorrencias() == null)
				return null;
			return e.getCd_Ocorrencias().toString();
		} catch (Exception erro) {
			erro.printStackTrace();
			return null;
		}
		}
	
}
