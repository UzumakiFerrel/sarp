package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.TipoEmbarcacoes;
import br.com.sarp.model.service.TipoEmbarcacoesService;

@Named
public class TipoEmbarcacoesConverter implements Converter {

	@Inject
	TipoEmbarcacoesService tipoEmbarcacoesService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			TipoEmbarcacoes tipoEmbarcacoes = tipoEmbarcacoesService.buscarId(intid);
			return tipoEmbarcacoes;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object tipoEmbarcacoes) {

		try{
			TipoEmbarcacoes e = (TipoEmbarcacoes) tipoEmbarcacoes; 
			if(e.getCd_tpemb()==null) return null;
			return e.getCd_tpemb().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
