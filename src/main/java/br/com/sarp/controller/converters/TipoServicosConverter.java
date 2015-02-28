package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.TipoServicos;
import br.com.sarp.model.service.TipoServicosService;

@Named
public class TipoServicosConverter implements Converter {

	@Inject
	TipoServicosService tipoServicosService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			TipoServicos tipoServicos = tipoServicosService.buscarId(intid);
			return tipoServicos;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object tipoServicos) {

		try{
			TipoServicos e = (TipoServicos) tipoServicos; 
			if(e.getCd_tpservicos()==null) return null;
			return e.getCd_tpservicos().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
