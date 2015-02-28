package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.RequisicaoMaterial;
import br.com.sarp.model.service.RequisicaoMaterialService;

@Named
public class RequisicaoMaterialConverter implements Converter {

	@Inject
	RequisicaoMaterialService requisicaoMaterialService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		if (id != "") {
			try {

				Integer intid = Integer.parseInt(id);

				RequisicaoMaterial requisicaoMaterial = requisicaoMaterialService.buscarId(intid);
				return requisicaoMaterial;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object requisicaoMaterial) {
	
		if (requisicaoMaterial.equals(null))
		{
			return null;
		} else {

		try {
			RequisicaoMaterial e = (RequisicaoMaterial) requisicaoMaterial;
			if (e.getNum_req() == null)
				return null;
			return e.getNum_req().toString();
		} catch (ClassCastException erro) {
			erro.printStackTrace();
			return null;
		}
		}
	}
}
