package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Estados;
import br.com.sarp.model.service.EstadosService;

@Named
public class EstadosConverter implements Converter {

	@Inject
	EstadosService estadosService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		try {
			Integer l = Integer.parseInt(id);
			Estados estados = estadosService.buscarId(l);
			System.out.println("Converteu " + estados.getNome());

			return estados;

		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Erro de conversao " + e.getMessage());
			return null;
		}

	}


	public String getAsString(FacesContext context, UIComponent component,
			Object estado) {

		try{
		Estados e = (Estados) estado; 
		if(e.getCd_uf()==null) return null;
		return e.getCd_uf().toString();
		} catch(Exception e){
			e.printStackTrace();
		return null;
		}

	}

	
}
