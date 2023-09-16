package br.com.bb.seguranca.questionario.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.bb.seguranca.questionario.modelo.form.Opcao;

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
		return manager.find(Opcao.class, id);
	}
	
	public List<Opcao> findAll(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Opcao> criteriaQuery = criteriaBuilder.createQuery(Opcao.class);
		Root<Opcao> root = criteriaQuery.from(Opcao.class);
		criteriaQuery.select(root);
		TypedQuery<Opcao> query = manager.createQuery(criteriaQuery);
		return query.getResultList();
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
