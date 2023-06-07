package br.com.bb.seguranca.questionario.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessages implements Serializable {

	private static final long serialVersionUID = 1L;

	private static void add(String msg, FacesMessage.Severity severity) {
		FacesMessage facesMessage = new FacesMessage(msg);
		facesMessage.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(msg, facesMessage);
	}

	public static void info(String msg) {
		add(msg, FacesMessage.SEVERITY_INFO);
	}
	public static void error(String msg) {
		add(msg, FacesMessage.SEVERITY_ERROR);
	}

}
