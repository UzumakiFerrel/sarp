package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.SolicitacaoServico;
import br.com.sarp.model.service.SolicitacaoServicoService;

@Named
public class SolicitacaoServicoConverter implements Converter {

	@Inject
	SolicitacaoServicoService solicitacaoServicoService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		if (id != "") {
			try {

				Integer intid = Integer.parseInt(id);

				SolicitacaoServico solicitacaoServico = solicitacaoServicoService
						.buscarId(intid);
				return solicitacaoServico;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object solicitacaoServico) {

		if (solicitacaoServico.equals(null)) {
			return null;
		} else {

			try {
				SolicitacaoServico e = (SolicitacaoServico) solicitacaoServico;
				if (e.getNum_sserv() == null)
					return null;
				return e.getNum_sserv().toString();
			} catch (ClassCastException erro) {
				erro.printStackTrace();
				return null;
			}
		}
	}
}
