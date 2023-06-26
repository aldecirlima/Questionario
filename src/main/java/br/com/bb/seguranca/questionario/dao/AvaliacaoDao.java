package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;
import java.util.List;

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

	public List<Avaliacao> buscaAvaliacoes() {
		String jpql = "SELECT DISTINCT a FROM Avaliacao a JOIN FETCH a.secoes s";
		TypedQuery<Avaliacao> query = manager.createQuery(jpql, Avaliacao.class);
		query.setMaxResults(50);
		return query.getResultList();
	}

	public Avaliacao findById(Long id) {
		return manager.find(Avaliacao.class, id);
	}

}
