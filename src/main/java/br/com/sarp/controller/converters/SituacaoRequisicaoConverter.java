package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.SituacaoRequisicao;
import br.com.sarp.model.service.SituacaoRequisicaoService;

@Named
public class SituacaoRequisicaoConverter implements Converter {

	@Inject
	SituacaoRequisicaoService situacaoRequisicaoService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		if (id != "") {
			try {

				Integer intid = Integer.parseInt(id);

				SituacaoRequisicao situacaoRequisicao = situacaoRequisicaoService.buscarId(intid);
				return situacaoRequisicao;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object situacaoRequisicao) {
	
		if (situacaoRequisicao.equals(null))
		{
			return null;
		} else {

		try {
			SituacaoRequisicao e = (SituacaoRequisicao) situacaoRequisicao;
			if (e.getCd_sitrm() == null)
				return null;
			return e.getCd_sitrm().toString();
		} catch (ClassCastException erro) {
			erro.printStackTrace();
			return null;
		}
		}
	}
}
