package br.com.bb.seguranca.questionario.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bb.seguranca.questionario.dao.AvaliacaoDao;
import br.com.bb.seguranca.questionario.modelo.form.Avaliacao;

public class AvaliacaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	AvaliacaoDao avaliacaoDao;

	/**
	 * Salva uma Avaliacao no banco de dados
	 * 
	 * @param Avaliacao
	 */

	@Transactional
	public void salvarAvaliacao(Avaliacao avaliacao) {
		avaliacaoDao.salvar(avaliacao);
	}

	/**
	 * Persiste o objeto e devolve o objeto com ID
	 * 
	 * @param Avaliacao
	 * @return Avaliacao
	 */
	@Transactional
	public Avaliacao persisteAvaliacao(Avaliacao avaliacao) {
		return avaliacaoDao.persistir(avaliacao);
	}

	/**
	 * Busca um objeto pelo ID
	 * 
	 * @param Long
	 * @return Avaliacao
	 */

	public Avaliacao findById(Long id) {
		return avaliacaoDao.findById(id);
	}

	public List<Avaliacao> buscaAvaliacoes() {
		return avaliacaoDao.buscaAvaliacoes();
	}

}
