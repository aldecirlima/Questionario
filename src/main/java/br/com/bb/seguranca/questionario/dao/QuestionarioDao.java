package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.bb.seguranca.questionario.modelo.base.QuestionarioBase;

public class QuestionarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public QuestionarioBase persistir(QuestionarioBase questionario) {
		return manager.merge(questionario);
	}

	public void salvar(QuestionarioBase questionario) {
		manager.merge(questionario);
	}

	public QuestionarioBase findById(Long id) {
		String jpql = "SELECT q FROM QuestionarioBase q WHERE q.idQuestionario = :id";
		TypedQuery<QuestionarioBase> query = manager.createQuery(jpql, QuestionarioBase.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	public List<QuestionarioBase> buscaQuestionariosNaoAtivos() {
		String jpql = "SELECT q FROM QuestionarioBase q " + "WHERE q.questionarioAtivo = 0 ORDER BY q.idQuestionario DESC";
		TypedQuery<QuestionarioBase> query = manager.createQuery(jpql, QuestionarioBase.class);
		return query.getResultList();
	}

	public List<QuestionarioBase> buscaQuestionariosAtivos() {
		String jpql = "SELECT q FROM QuestionarioBase q WHERE q.questionarioAtivo = 1";
		TypedQuery<QuestionarioBase> query = manager.createQuery(jpql, QuestionarioBase.class);
		return query.getResultList();
	}

	public QuestionarioBase buscaQuestionarioAtivo() {
		String jpql = "SELECT q FROM QuestionarioBase q " + "WHERE q.questionarioAtivo = 1";
		TypedQuery<QuestionarioBase> query = manager.createQuery(jpql, QuestionarioBase.class);
		return query.getSingleResult();
	}

	public List<QuestionarioBase> buscaQuestionariosNaoAtivosSemFetch() {
		String jpql = "SELECT q FROM QuestionarioBase q WHERE q.questionarioAtivo = 0 ORDER BY q.idQuestionario DESC";
		TypedQuery<QuestionarioBase> query = manager.createQuery(jpql, QuestionarioBase.class);
		return query.getResultList();
	}

}
