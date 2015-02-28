package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.SituacaoRebocador;
import br.com.sarp.model.service.SituacaoRebocadorService;

@Named
public class SituacaoRebocadorConverter implements Converter {

	@Inject
	SituacaoRebocadorService situacaoRebocadorService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			SituacaoRebocador situacaoRebocador = situacaoRebocadorService.buscarId(intid);
			return situacaoRebocador;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object situacaoRebocador) {

		try{
			SituacaoRebocador e = (SituacaoRebocador) situacaoRebocador; 
			if(e.getCd_sitrb()==null) return null;
			return e.getCd_sitrb().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
