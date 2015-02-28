package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Funcao;
import br.com.sarp.model.service.FuncaoService;

@Named
public class FuncaoConverter implements Converter {

	@Inject
	FuncaoService funcaoService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			Funcao funcao = funcaoService.buscarId(intid);
			return funcao;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
		
		
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object funcao) {

		try{
		Funcao e = (Funcao) funcao; 
		if(e.getCd_funcao()==null) return null;
		return e.getCd_funcao().toString();
		} catch(Exception e){
			e.printStackTrace();
		return null;
		}
	}
}
