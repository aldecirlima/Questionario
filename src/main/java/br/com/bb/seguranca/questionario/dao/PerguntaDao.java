package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.bb.seguranca.questionario.modelo.base.perguntas.Pergunta;

public class PerguntaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Pergunta persistir(Pergunta pergunta) {
		return manager.merge(pergunta);
	}

	public void salvar(Pergunta pergunta) {
		manager.merge(pergunta);
	}

	public Pergunta findById(Long id) {
		String jpql = "SELECT p FROM Pergunta p WHERE p.idPergunta = :id";
		TypedQuery<Pergunta> query = manager.createQuery(jpql, Pergunta.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

}
