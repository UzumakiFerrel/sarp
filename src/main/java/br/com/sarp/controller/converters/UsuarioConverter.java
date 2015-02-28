package br.com.sarp.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sarp.model.entidades.Usuario;
import br.com.sarp.model.service.UsuarioService;

@Named
public class UsuarioConverter implements Converter {

	@Inject
	UsuarioService usuarioService;

	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {

		if (id != "") {
			try {

				Integer intid = Integer.parseInt(id);

				Usuario usuario = usuarioService.buscarId(intid);
				return usuario;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;

	}

	public String getAsString(FacesContext context, UIComponent component,
			Object usuario) {
	
		
		try {
			Usuario e = (Usuario) usuario;
			if (e.getMatricula() == null)
				return null;
			return e.getMatricula().toString();
		} catch (Exception erro) {
			erro.printStackTrace();
			return null;
		}
		}
	
}
