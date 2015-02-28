package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.TipoHistoricoRebocador;
import br.com.sarp.model.service.TipoHistoricoRebocadorService;

@Named
public class TipoHistoricoRebocadorConverter implements Converter {

	@Inject
	TipoHistoricoRebocadorService tipoHistoricoRebocadorService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			TipoHistoricoRebocador tipoHistoricoRebocador = tipoHistoricoRebocadorService.buscarId(intid);
			return tipoHistoricoRebocador;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object tipoHistoricoRebocador) {

		try{
			TipoHistoricoRebocador e = (TipoHistoricoRebocador) tipoHistoricoRebocador; 
			if(e.getCd_tphistoricorb()==null) return null;
			return e.getCd_tphistoricorb().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
