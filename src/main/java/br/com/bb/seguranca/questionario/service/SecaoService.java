package br.com.bb.seguranca.questionario.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bb.seguranca.questionario.dao.SecaoDao;
import br.com.bb.seguranca.questionario.modelo.Secao;

public class SecaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	SecaoDao secaoDao;

	@Transactional
	public void salvarSecao(Secao secao) {
		secaoDao.salvar(secao);
	}

	/**
	 * Persiste o objeto e devolve o objeto com ID
	 * 
	 * @param Secao
	 * @return Secao
	 */
	@Transactional
	public Secao persisteSecao(Secao secao) {
		return secaoDao.persistir(secao);
	}

	public Secao findById(Long id) {
		return secaoDao.findById(id);
	}

}
