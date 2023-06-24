package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.bb.seguranca.questionario.modelo.form.Resposta;

public class RespostaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Resposta findById(Long id) {
		return manager.find(Resposta.class, id);
	}

}
