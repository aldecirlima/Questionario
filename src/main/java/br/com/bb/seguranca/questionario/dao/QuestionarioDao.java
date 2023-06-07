package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.bb.seguranca.questionario.modelo.Questionario;

public class QuestionarioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Questionario persistir(Questionario questionario) {
		return manager.merge(questionario);

	}

	public void salvar(Questionario questionario) {
		manager.merge(questionario);

	}

}
