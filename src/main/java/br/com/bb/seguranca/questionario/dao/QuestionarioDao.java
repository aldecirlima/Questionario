package br.com.bb.seguranca.questionario.dao;

import javax.persistence.EntityManager;

import br.com.bb.seguranca.questionario.modelo.Questionario;
import br.com.bb.seguranca.questionario.util.JPAUtil;

public class QuestionarioDao {
	
	

	public void inserir(Questionario questionario) {
		EntityManager em = JPAUtil.getEntityManagerMysql();
		em.getTransaction().begin();;
		em.persist(questionario);
		em.getTransaction().commit();
		em.close();
	}
	public void merge(Questionario questionario) {
		EntityManager em = JPAUtil.getEntityManagerMysql();
		em.getTransaction().begin();;
		em.merge(questionario);
		em.getTransaction().commit();
		em.close();
	}
	
}
