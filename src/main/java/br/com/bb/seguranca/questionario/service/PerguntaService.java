package br.com.bb.seguranca.questionario.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bb.seguranca.questionario.dao.PerguntaDao;
import br.com.bb.seguranca.questionario.modelo.base.PerguntaBase;
import br.com.bb.seguranca.questionario.modelo.form.Pergunta;

public class PerguntaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	PerguntaDao perguntaDao;
	
	/**
	 * Salva uma pergunta no banco de dados
	 * @param PerguntaBase
	 */

	@Transactional
	public void salvarPergunta(PerguntaBase pergunta) {
		perguntaDao.salvar(pergunta);
	}

	/**
	 * Persiste o objeto e devolve o objeto com ID
	 * 
	 * @param PerguntaBase
	 * @return PerguntaBase
	 */
	@Transactional
	public PerguntaBase persistePergunta(PerguntaBase pergunta) {
		return perguntaDao.persistir(pergunta);
	}
	
	/**
	 * Busca um objeto pelo ID
	 * 
	 * @param Long
	 * @return PerguntaBase
	 */

	public PerguntaBase findById(Long id) {
		return perguntaDao.findById(id);
	}
	
	public Pergunta findPerguntaId(Long id) {
		return perguntaDao.findPerguntaId(id);
	}

}
