package br.com.bb.seguranca.questionario.service;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.bb.seguranca.questionario.modelo.form.Opcao;

@FacesConverter(forClass = Opcao.class, value = "opcaoConverter")
public class OpcaoConverter implements Converter<Object> {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return getMapaObjetos(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Opcao) {
			Opcao opcao = (Opcao) value;
			adicionarAtributo(component, opcao);
			String chave = String.valueOf(opcao.getIdOpcao());
			return chave;
		}
		return (String) value;
	}

	protected Map<String, Object> getMapaObjetos(UIComponent component) {
		return component.getAttributes();
	}

	protected void adicionarAtributo(UIComponent component, Opcao opcao) {
		String chave = String.valueOf(opcao.getIdOpcao());
		getMapaObjetos(component).put(chave, opcao);
	}
}
