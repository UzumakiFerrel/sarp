package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.PassagemComando;
import br.com.sarp.model.service.PassagemComandoService;

@Named
public class PassagemComandoConverter implements Converter {

	@Inject
	PassagemComandoService passagemComandoService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		if (id != "") {
			try {

				Integer intid = Integer.parseInt(id);

				PassagemComando passagemComando = passagemComandoService.buscarId(intid);
				return passagemComando;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object passagemComando) {
		
		try {
			PassagemComando e = (PassagemComando) passagemComando;
			if (e.getCd_passagemComando() == null)
				return null;
			return e.getCd_passagemComando().toString();
		} catch (Exception erro) {
			erro.printStackTrace();
			return null;
		}
		}
	
}
