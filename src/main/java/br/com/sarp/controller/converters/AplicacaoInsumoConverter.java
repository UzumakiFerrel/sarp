package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.AplicacaoInsumo;
import br.com.sarp.model.service.AplicacaoInsumoService;

@Named
public class AplicacaoInsumoConverter implements Converter {

	@Inject
	AplicacaoInsumoService aplicacaoInsumoService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			AplicacaoInsumo aplicacaoInsumo = aplicacaoInsumoService.buscarId(intid);
			return aplicacaoInsumo;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object aplicacao) {

		try{
			AplicacaoInsumo e = (AplicacaoInsumo) aplicacao; 
			
			if(e.getCd_aplicacao()==null) return null;
			return e.getCd_aplicacao().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
