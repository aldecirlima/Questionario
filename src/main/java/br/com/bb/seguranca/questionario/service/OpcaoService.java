package br.com.bb.seguranca.questionario.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bb.seguranca.questionario.dao.OpcaoDao;
import br.com.bb.seguranca.questionario.modelo.perguntas.Opcao;

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

	public List<Opcao> buscaSimNaoNaoSeAplica() {
		return opcaoDao.buscaSimNaoNaoSeAplica();
	}

}
