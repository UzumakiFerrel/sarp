package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.SituacaoFuncionario;
import br.com.sarp.model.service.SituacaoFuncionarioService;

@Named
public class SituacaoFuncionarioConverter implements Converter {

	@Inject
	SituacaoFuncionarioService situacaoFuncionarioService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			SituacaoFuncionario situacaoFuncionario = situacaoFuncionarioService.buscarId(intid);
			return situacaoFuncionario;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
		
		
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object situacaoFuncionario) {
		try{
		SituacaoFuncionario e = (SituacaoFuncionario) situacaoFuncionario; 
		if(e.getCd_sit()==null) return null;
		return e.getCd_sit().toString();
		} catch(Exception e){
			e.printStackTrace();
		return null;
		}
	}

}
