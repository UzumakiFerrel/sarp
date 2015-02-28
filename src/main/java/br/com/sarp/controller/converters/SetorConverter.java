package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Setor;
import br.com.sarp.model.service.SetorService;

@Named
public class SetorConverter implements Converter {

	@Inject
	SetorService setorService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			Setor setor = setorService.buscarId(intid);
			return setor;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
		
		
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object setor) {

		try{
		Setor e = (Setor) setor; 
		if(e.getCd_setor()==null) return null;
		return e.getCd_setor().toString();
		} catch(Exception e){
			e.printStackTrace();
		return null;
		}
		
	}

}
