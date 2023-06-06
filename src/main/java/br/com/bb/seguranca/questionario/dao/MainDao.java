package br.com.bb.seguranca.questionario.dao;

import javax.persistence.EntityManager;

public class MainDao {
	
	public void salvar(Object objeto, EntityManager entityManager) {
		entityManager.getTransaction().begin();
		entityManager.merge(objeto);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
