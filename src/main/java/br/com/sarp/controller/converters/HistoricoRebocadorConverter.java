package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.HistoricoRebocador;
import br.com.sarp.model.service.HistoricoRebocadorService;

@Named
public class HistoricoRebocadorConverter implements Converter {

	@Inject
	HistoricoRebocadorService historicoRebocadorService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		if (id != "") {
			try {

				Integer intid = Integer.parseInt(id);

				HistoricoRebocador historicoRebocador = historicoRebocadorService.buscarId(intid);
				return historicoRebocador;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object historicoRebocador) {
	
		if (historicoRebocador.equals(null))
		{
			return null;
		} else {

		try {
			HistoricoRebocador e = (HistoricoRebocador) historicoRebocador;
			if (e.getCd_historico() == null)
				return null;
			return e.getCd_historico().toString();
		} catch (ClassCastException erro) {
			erro.printStackTrace();
			return null;
		}
		}
	}
}
