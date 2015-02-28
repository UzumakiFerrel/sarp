package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.TipoDocumento;
import br.com.sarp.model.service.TipoDocumentoService;

@Named
public class TipoDocumentoConverter implements Converter {

	@Inject
	TipoDocumentoService tipoDocumentoService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			TipoDocumento tipoDocumento = tipoDocumentoService.buscarId(intid);
			return tipoDocumento;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object tipoDocumento) {

		try{
			TipoDocumento e = (TipoDocumento) tipoDocumento; 
			if(e.getCd_tpdocumento()==null) return null;
			return e.getCd_tpdocumento().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
