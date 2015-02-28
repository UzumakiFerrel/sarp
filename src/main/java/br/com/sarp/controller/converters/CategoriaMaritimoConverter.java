package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.CategoriaMaritimo;
import br.com.sarp.model.service.CategoriaMaritimoService;

@Named
public class CategoriaMaritimoConverter implements Converter {

	@Inject
	CategoriaMaritimoService categoriaMaritimoService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			CategoriaMaritimo categoriaMaritimo = categoriaMaritimoService.buscarId(intid);
			return categoriaMaritimo;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
		
		
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object categoriaMaritimo) {

		try{
		CategoriaMaritimo e = (CategoriaMaritimo) categoriaMaritimo; 
		if(e.getCd_categoria()==null) return null;
		return e.getCd_categoria().toString();
		} catch(Exception e){
			e.printStackTrace();
		return null;
		}

	
	}

}
