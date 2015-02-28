package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.TipoEmpresas;
import br.com.sarp.model.service.TipoEmpresasService;

@Named
public class TipoEmpresasConverter implements Converter {

	@Inject
	TipoEmpresasService tipoEmpresasService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			TipoEmpresas tipoEmpresas = tipoEmpresasService.buscarId(intid);
			return tipoEmpresas;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object tipoEmpresas) {

		try{
			TipoEmpresas e = (TipoEmpresas) tipoEmpresas; 
			if(e.getTp_empresa()==null) return null;
			return e.getTp_empresa().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
