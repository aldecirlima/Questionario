package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.bb.seguranca.questionario.modelo.base.SecaoBase;

public class SecaoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public SecaoBase persistir(SecaoBase secaoBase) {
		return manager.merge(secaoBase);
	}

	public void salvar(SecaoBase secaoBase) {
		manager.merge(secaoBase);
	}

	public SecaoBase findById(Long id) {
		return manager.find(SecaoBase.class, id);
	}

	public SecaoBase buscaPerguntasDaSecao(Long idSecaoBase) {
		String jpql = "SELECT s FROM SecaoBase s JOIN FETCH s.perguntas p " + "WHERE s.idSecaoBase = :idSecaoBase";
		TypedQuery<SecaoBase> query = manager.createQuery(jpql, SecaoBase.class);
		query.setParameter("idSecaoBase", idSecaoBase);
		return query.getSingleResult();
	}

}
