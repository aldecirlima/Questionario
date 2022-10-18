package br.com.caelum.auron.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ExibirBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer entrada;

	private Boolean exibe = false;

	public void exibir() {
		if (this.exibe.equals(true)) {
			this.exibe = false;
			System.out.println("If " +  exibe);
		} else {
			this.exibe = true;
			System.out.println("Else " +  exibe);
		}
	}

	public Integer getEntrada() {
		return entrada;
	}

	public void setEntrada(Integer entrada) {
		this.entrada = entrada;
	}

	public Boolean getExibe() {
		return exibe;
	}

	public void setExibe(Boolean exibe) {
		this.exibe = exibe;
	}

}
