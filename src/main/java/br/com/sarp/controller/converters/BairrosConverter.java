package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Bairros;
import br.com.sarp.model.service.BairrosService;

@Named
public class BairrosConverter implements Converter {

	@Inject
	BairrosService bairrosService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Bairros bairros = bairrosService.buscarId(intid);
			return bairros;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object bairros) {

		try{
			Bairros e = (Bairros) bairros; 
			if(e.getCd_bair()==null) return null;
			return e.getCd_bair().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
