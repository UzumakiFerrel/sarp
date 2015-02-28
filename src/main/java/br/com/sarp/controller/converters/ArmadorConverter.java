package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Armador;
import br.com.sarp.model.service.ArmadorService;

@Named
public class ArmadorConverter implements Converter {

	@Inject
	ArmadorService armadorService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Armador armador = armadorService.buscarId(intid);
			return armador;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object armador) {

		try{
			Armador e = (Armador) armador; 
			if(e.getCd_armador()==null) return null;
			return e.getCd_armador().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
