package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.bb.seguranca.questionario.modelo.Secao;

public class SecaoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Secao persistir(Secao secao) {
		return manager.merge(secao);
	}

	public void salvar(Secao secao) {
		manager.merge(secao);

	}

	public Secao findById(Long id) {
		String jpql = "SELECT s FROM Secao s WHERE s.idSecao = :id";

		TypedQuery<Secao> query = manager.createQuery(jpql, Secao.class);
		query.setParameter("id", id);

		return query.getSingleResult();
	}

}
