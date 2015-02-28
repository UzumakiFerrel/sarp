package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Embarcacoes;
import br.com.sarp.model.service.EmbarcacoesService;

@Named
public class EmbarcacoesConverter implements Converter {

	@Inject
	EmbarcacoesService embarcacoesService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Embarcacoes embarcacoes = embarcacoesService.buscarId(intid);
			return embarcacoes;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object embarcacoes) {

		try{
			Embarcacoes e = (Embarcacoes) embarcacoes; 
			if(e.getCd_embarcacoes()==null) return null;
			return e.getCd_embarcacoes().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
