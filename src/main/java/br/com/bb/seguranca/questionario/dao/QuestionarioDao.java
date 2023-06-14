package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	
	public Questionario findById(Long id) {
		String jpql = "SELECT q FROM Questionario q WHERE q.idQuestionario = :id";
		TypedQuery<Questionario> query = manager.createQuery(jpql, Questionario.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public List<Questionario> buscaQuestionariosNaoAtivos(){
		String jpql = "SELECT q FROM Questionario q WHERE q.questionarioAtivo = 0 ORDER BY q.idQuestionario DESC";
		TypedQuery<Questionario> query = manager.createQuery(jpql, Questionario.class);
		return query.getResultList();
	}
	
	
	

}
