package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.bb.seguranca.questionario.modelo.form.Avaliacao;

public class AvaliacaoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Avaliacao persistir(Avaliacao avaliacao) {
		return manager.merge(avaliacao);
	}

	public void salvar(Avaliacao avaliacao) {
		manager.merge(avaliacao);
	}

	public Avaliacao findById(Long id) {
		String jpql = "SELECT a FROM Avaliacao a WHERE p.idAvaliacao = :id";
		TypedQuery<Avaliacao> query = manager.createQuery(jpql, Avaliacao.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

}
