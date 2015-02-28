package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.TipoManobras;
import br.com.sarp.model.service.TipoManobrasService;

@Named
public class TipoManobrasConverter implements Converter {

	@Inject
	TipoManobrasService tipoManobrasService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			TipoManobras tipoManobras = tipoManobrasService.buscarId(intid);
			return tipoManobras;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object tipoManobras) {

		try{
			TipoManobras e = (TipoManobras) tipoManobras; 
			if(e.getCd_tpmanobras()==null) return null;
			return e.getCd_tpmanobras().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
