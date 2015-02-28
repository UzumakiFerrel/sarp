package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Embarque;
import br.com.sarp.model.service.EmbarqueService;

@Named
public class EmbarqueConverter implements Converter {

	@Inject
	EmbarqueService embarqueService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Embarque embarque = embarqueService.buscarId(intid);
			return embarque;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object embarque) {

		try{
			Embarque e = (Embarque) embarque; 
			if(e.getCd_embarque()==null) return null;
			return e.getCd_embarque().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
