package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.AplicacaoServicos;
import br.com.sarp.model.service.AplicacaoServicosService;

@Named
public class AplicacaoServicosConverter implements Converter {

	@Inject
	AplicacaoServicosService aplicacaoServicosService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			AplicacaoServicos aplicacaoServicos = aplicacaoServicosService.buscarId(intid);
			return aplicacaoServicos;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object aplicacao) {

		try{
			AplicacaoServicos e = (AplicacaoServicos) aplicacao; 
			
			if(e.getCd_aplicacao()==null) return null;
			return e.getCd_aplicacao().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
