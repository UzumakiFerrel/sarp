package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.CentroCusto;
import br.com.sarp.model.service.CentroCustoService;

@Named
public class CentroCustoConverter implements Converter {

	@Inject
	CentroCustoService centroCustoService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			if (id.equalsIgnoreCase("Selecione")==false){
			Integer	intid = Integer.parseInt(id);
			
			CentroCusto centroCusto = centroCustoService.buscarId(intid);
			return centroCusto;
			} else return null;
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object centroCusto) {

		try{
			CentroCusto e = (CentroCusto) centroCusto; 
			if(e.getCd_custo()==null) return null;
			return e.getCd_custo().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
