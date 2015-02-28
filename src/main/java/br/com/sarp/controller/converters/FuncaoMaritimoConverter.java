package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.FuncaoMaritimo;
import br.com.sarp.model.service.FuncaoMaritimoService;

@Named
public class FuncaoMaritimoConverter implements Converter {

	@Inject
	FuncaoMaritimoService funcaoMaritimoService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			FuncaoMaritimo funcaoMaritimo = funcaoMaritimoService.buscarId(intid);
			return funcaoMaritimo;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String getAsString(FacesContext context, UIComponent component,
			Object funcaoMaritimo) {

		try{
		FuncaoMaritimo e = (FuncaoMaritimo) funcaoMaritimo; 
		if(e.getCd_categoria()==null) return null;
		return e.getCd_categoria().toString();
		} catch(Exception e){
			e.printStackTrace();
		return null;
		}

	
	}

}
