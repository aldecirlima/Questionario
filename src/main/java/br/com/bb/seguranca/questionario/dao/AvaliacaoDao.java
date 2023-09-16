package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Avaliacao> criteriaQuery = criteriaBuilder.createQuery(Avaliacao.class);
		Root<Avaliacao> root = criteriaQuery.from(Avaliacao.class);
		criteriaQuery.select(root);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("idAvaliacao")));
		TypedQuery<Avaliacao> query = manager.createQuery(criteriaQuery);
		query.setMaxResults(200);
		return query.getResultList();
	}

	public Avaliacao findById(Long id) {
		return manager.find(Avaliacao.class, id);
	}

}
