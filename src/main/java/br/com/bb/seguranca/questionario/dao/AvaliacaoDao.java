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

	public void persistir(Avaliacao avaliacao) {
		manager.persist(avaliacao);
	}

	public void salvar(Avaliacao avaliacao) {
		manager.merge(avaliacao);
	}

	public List<Avaliacao> buscaAvaliacoes() {
		String jpql = "SELECT a FROM Avaliacao a ORDER BY a.idAvaliacao DESC";
		TypedQuery<Avaliacao> query = manager.createQuery(jpql, Avaliacao.class);
		query.setMaxResults(50);
		return query.getResultList();
	}

	public Avaliacao findById(Long id) {
		return manager.find(Avaliacao.class, id);
	}

}
