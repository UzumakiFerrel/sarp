package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Propulsao;
import br.com.sarp.model.service.PropulsaoService;

@Named
public class PropulsaoConverter implements Converter {

	@Inject
	PropulsaoService propulsaoService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Propulsao propulsao = propulsaoService.buscarId(intid);
			return propulsao;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object propulsao) {

		try{
			Propulsao e = (Propulsao) propulsao; 
			if(e.getCd_propulsao()==null) return null;
			return e.getCd_propulsao().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
