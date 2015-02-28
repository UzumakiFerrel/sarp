package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Tripulacao;
import br.com.sarp.model.service.TripulacaoService;

@Named
public class TripulacaoConverter implements Converter {

	@Inject
	TripulacaoService tripulacaoService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Tripulacao tripulacao = tripulacaoService.buscarPorId(intid);
			return tripulacao;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object tripulacao) {

		try{
			Tripulacao e = (Tripulacao) tripulacao; 
			if(e.getCd_tripulacao()==null) return null;
			return e.getCd_tripulacao().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}
	}

}
