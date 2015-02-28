package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Solicitacoes;
import br.com.sarp.model.service.SolicitacoesService;

@Named
public class SolicitacoesConverter implements Converter {

	@Inject
	SolicitacoesService solicitacoesService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			Solicitacoes solicitacoes = solicitacoesService.buscarId(intid);
			return solicitacoes;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object solicitacoes) {

		try{
			Solicitacoes e = (Solicitacoes) solicitacoes; 
			if(e.getCd_solicitacoes()==null) return null;
			return e.getCd_solicitacoes().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}
	}

}
