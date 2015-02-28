package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Tripulante;
import br.com.sarp.model.service.TripulanteService;

@Named
public class TripulanteConverter implements Converter {

	@Inject
	TripulanteService tripulanteService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Tripulante tripulante = tripulanteService.buscarPorId(intid);
			return tripulante;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object tripulante) {

		try{
			Tripulante e = (Tripulante) tripulante; 
			if(e.getUsuario().getMatricula()==null) return null;
			return e.getUsuario().getMatricula().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}
	}

}
