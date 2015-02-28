package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.ItemRequisicao;
import br.com.sarp.model.service.ItemRequisicaoService;

@Named
public class ItemRequisicaoConverter implements Converter {

	@Inject
	ItemRequisicaoService itemRequisicaoService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
	
		try{
			Integer	intid = Integer.parseInt(id);
			
			ItemRequisicao itemRequisicao = itemRequisicaoService.buscarId(intid);
			return itemRequisicao;
						
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public String getAsString(FacesContext context, UIComponent component,
			Object itemRequisicao) {

		try{
			ItemRequisicao e = (ItemRequisicao) itemRequisicao; 
			if(e.getInsumo().getCd_insumo()==null) return null;
			return e.getInsumo().getCd_insumo().toString();
			} catch(Exception e){
				e.printStackTrace();
			return null;
			}

	}

}
