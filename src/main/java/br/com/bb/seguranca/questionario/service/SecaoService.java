package br.com.bb.seguranca.questionario.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bb.seguranca.questionario.dao.SecaoDao;
import br.com.bb.seguranca.questionario.modelo.base.SecaoBase;

public class SecaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	SecaoDao secaoDao;

	@Transactional
	public void salvarSecao(SecaoBase secao) {
		secaoDao.salvar(secao);
	}

	/**
	 * Persiste o objeto e devolve o objeto com ID
	 * 
	 * @param SecaoBase
	 * @return SecaoBase
	 */
	@Transactional
	public SecaoBase persisteSecao(SecaoBase secao) {
		return secaoDao.persistir(secao);
	}

	public SecaoBase findById(Long id) {
		return secaoDao.findById(id);
	}

	public SecaoBase buscaPerguntasDaSecao(Long idSecaoBase) {
		return secaoDao.buscaPerguntasDaSecao(idSecaoBase);
	}

}
