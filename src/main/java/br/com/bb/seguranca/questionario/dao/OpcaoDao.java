package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.bb.seguranca.questionario.modelo.perguntas.Opcao;

public class OpcaoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Opcao persistir(Opcao opcao) {
		return manager.merge(opcao);
	}

	public void salvar(Opcao opcao) {
		manager.merge(opcao);
	}

	public Opcao findById(Long id) {
		String jpql = "SELECT o FROM Opcao o WHERE o.idOpcao = :id";
		TypedQuery<Opcao> query = manager.createQuery(jpql, Opcao.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	public List<Opcao> buscaTodasOpcoes() {
		String jpql = "SELECT o FROM Opcao o";
		TypedQuery<Opcao> query = manager.createQuery(jpql, Opcao.class);
		return query.getResultList();
	}
	
	public List<Opcao> buscaTodasOpcoesAtivas() {
		String jpql = "SELECT o FROM Opcao o WHERE o.ativa = 1";
		TypedQuery<Opcao> query = manager.createQuery(jpql, Opcao.class);
		return query.getResultList();
	}

	public List<Opcao> buscaSimNao() {
		String jpql = "SELECT o FROM Opcao o WHERE o.idOpcao IN (1,2)";
		TypedQuery<Opcao> query = manager.createQuery(jpql, Opcao.class);
		return query.getResultList();
	}

	public List<Opcao> buscaSimNaoNaoSeAplica() {
		String jpql = "SELECT o FROM Opcao o WHERE o.idOpcao IN (1,2,3)";
		TypedQuery<Opcao> query = manager.createQuery(jpql, Opcao.class);
		return query.getResultList();
	}

}
