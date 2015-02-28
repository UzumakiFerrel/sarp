package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Locais;
import br.com.sarp.model.service.LocaisService;

@Named
public class LocaisConverter implements Converter {

	@Inject
	LocaisService locaisService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Locais locais = locaisService.buscarId(intid);
			return locais;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object locais) {

		try{
			Locais e = (Locais) locais; 
			if(e.getCd_local()==null) return null;
			return e.getCd_local().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
