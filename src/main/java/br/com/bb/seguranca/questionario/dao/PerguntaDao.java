package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

	public PerguntaBase findOpcoesParaSelecao(PerguntaBase perguntaBase) {
		String jpql = "SELECT p FROM PerguntaBase p "
				+ "JOIN FETCH p.opcoesParaSelecao o "
				+ "WHERE p.idPerguntaBase = :id "
				+ "ORDER BY p.idPerguntaBase DESC";
		TypedQuery<PerguntaBase> query = manager.createQuery(jpql, PerguntaBase.class);
		query.setParameter("id", perguntaBase.getIdPerguntaBase());
		return query.getSingleResult();
	}

	public Pergunta findPerguntaId(Long id) {
		return manager.find(Pergunta.class, id);
	}

}
