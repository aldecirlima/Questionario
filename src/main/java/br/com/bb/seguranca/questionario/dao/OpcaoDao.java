package br.com.bb.seguranca.questionario.dao;

import javax.persistence.EntityManager;

import br.com.bb.seguranca.questionario.modelo.perguntas.Opcao;
import br.com.bb.seguranca.questionario.util.JPAUtil;

public class OpcaoDao {

	EntityManager em = JPAUtil.getEntityManagerMysql();
	
	MainDao mainDao = new MainDao();
	
	public void salvar(Opcao opcao) {
		mainDao.salvar(opcao, em);
	}

}
