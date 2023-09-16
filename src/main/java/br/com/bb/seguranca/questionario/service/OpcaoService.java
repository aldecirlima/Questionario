package br.com.bb.seguranca.questionario.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bb.seguranca.questionario.dao.OpcaoDao;
import br.com.bb.seguranca.questionario.modelo.form.Opcao;

public class OpcaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	OpcaoDao opcaoDao;

	@Transactional
	public void salvarOpcao(Opcao opcao) {
		opcaoDao.salvar(opcao);
	}

	/**
	 * Persiste o objeto e devolve o objeto com ID
	 * 
	 * @param Opcao
	 * @return Opcao
	 */
	@Transactional
	public Opcao persisteOpcao(Opcao opcao) {
		return opcaoDao.persistir(opcao);
	}

	public List<Opcao> buscaTodasOpcoes() {
		return opcaoDao.buscaTodasOpcoes();
	}

	public List<Opcao> buscaTodasOpcoesAtivas() {
		return opcaoDao.buscaTodasOpcoesAtivas();
	}

	public Opcao findById(Long id) {
		return opcaoDao.findById(id);
	}

	public List<Opcao> buscaSimNao() {
		return opcaoDao.buscaSimNao();
	}

	public List<Opcao> findAll() {
		return opcaoDao.findAll();
	}

	/**
	 * Método que retorna um Map com chave id da Opcao e como valor a Opcao
	 * 
	 * @return {@link Map}<{Long, Opcao}>
	 * 
	 * */
	
	public Map<Long, Opcao> findAllMap() {
		List<Opcao> opcoes = this.findAll();
		Map<Long, Opcao> map = new HashMap<>();
		for (Opcao opcao : opcoes) {
			map.put(opcao.getIdOpcao(), opcao);
		}
		return map;
	}

	public List<Opcao> buscaSimNaoNaoSeAplica() {
		return opcaoDao.buscaSimNaoNaoSeAplica();
	}

}
