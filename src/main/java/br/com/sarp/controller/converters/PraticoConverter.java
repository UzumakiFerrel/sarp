package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Pratico;
import br.com.sarp.model.service.PraticoService;

@Named
public class PraticoConverter implements Converter {

	@Inject
	PraticoService praticosService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Pratico praticos = praticosService.buscarId(intid);
			return praticos;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object praticos) {

		try{
			Pratico e = (Pratico) praticos; 
			if(e.getCd_pratico()==null) return null;
			return e.getCd_pratico().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
