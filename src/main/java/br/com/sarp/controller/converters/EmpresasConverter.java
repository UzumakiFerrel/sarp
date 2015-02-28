package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Empresas;
import br.com.sarp.model.service.EmpresasService;

@Named
public class EmpresasConverter implements Converter {

	@Inject
	EmpresasService empresasService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Empresas empresas = empresasService.buscarId(intid);
			return empresas;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object empresas) {

		try{
			Empresas e = (Empresas) empresas; 
			if(e.getCd_empresa()==null) return null;
			return e.getCd_empresa().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
