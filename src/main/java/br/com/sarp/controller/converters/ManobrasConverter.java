package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Manobras;
import br.com.sarp.model.service.ManobrasService;

@Named
public class ManobrasConverter implements Converter {

	@Inject
	ManobrasService manobrasService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Manobras manobras = manobrasService.buscarId(intid);
			return manobras;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object manobras) {

		try{
			Manobras e = (Manobras) manobras; 
			if(e.getCd_manobras()==null) return null;
			return e.getCd_manobras().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
