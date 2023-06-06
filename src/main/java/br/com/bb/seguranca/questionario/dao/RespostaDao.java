package br.com.bb.seguranca.questionario.dao;

import javax.persistence.EntityManager;

import br.com.bb.seguranca.questionario.modelo.perguntas.Resposta;
import br.com.bb.seguranca.questionario.util.JPAUtil;

public class RespostaDao {
	
	EntityManager em = JPAUtil.getEntityManagerMysql();

	public void inserir(Resposta resposta) {
		em.getTransaction().begin();
		em.persist(resposta);
		em.getTransaction().commit();
		em.close();
	}

}
