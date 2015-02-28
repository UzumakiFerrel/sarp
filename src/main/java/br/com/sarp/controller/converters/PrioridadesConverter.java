package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Prioridades;
import br.com.sarp.model.service.PrioridadesService;

@Named
public class PrioridadesConverter implements Converter {

	@Inject
	PrioridadesService prioridadesService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Prioridades prioridades = prioridadesService.buscarId(intid);
			return prioridades;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object prioridades) {

		try{
			Prioridades e = (Prioridades) prioridades; 
			if(e.getCd_prioridade()==null) return null;
			return e.getCd_prioridade().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
