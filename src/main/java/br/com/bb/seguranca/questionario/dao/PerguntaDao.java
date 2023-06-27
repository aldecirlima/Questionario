package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.bb.seguranca.questionario.modelo.base.PerguntaBase;
import br.com.bb.seguranca.questionario.modelo.form.Pergunta;

public class PerguntaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public PerguntaBase persistir(PerguntaBase pergunta) {
		return manager.merge(pergunta);
	}

	public void salvar(PerguntaBase pergunta) {
		manager.merge(pergunta);
	}

	public PerguntaBase findById(Long id) {
		return manager.find(PerguntaBase.class, id);
	}
	
	public Pergunta findPerguntaId(Long id) {
		return manager.find(Pergunta.class, id);
	}

}
