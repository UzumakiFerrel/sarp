package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Rebocador;
import br.com.sarp.model.service.RebocadorService;

@Named
public class RebocadorConverter implements Converter {

	@Inject
	RebocadorService rebocadorService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Rebocador rebocador = rebocadorService.buscarId(intid);
			return rebocador;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object rebocador) {

		try{
			Rebocador e = (Rebocador) rebocador; 
			if(e.getCd_rebocador()==null) return null;
			return e.getCd_rebocador().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
