package br.com.bb.seguranca.questionario.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bb.seguranca.questionario.dao.PerguntaDao;
import br.com.bb.seguranca.questionario.modelo.base.perguntas.Pergunta;

public class PerguntaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	PerguntaDao perguntaDao;
	
	/**
	 * Salva uma pergunta no banco de dados
	 * @param Pergunta
	 */

	@Transactional
	public void salvarPergunta(Pergunta pergunta) {
		perguntaDao.salvar(pergunta);
	}

	/**
	 * Persiste o objeto e devolve o objeto com ID
	 * 
	 * @param Pergunta
	 * @return Pergunta
	 */
	@Transactional
	public Pergunta persistePergunta(Pergunta pergunta) {
		return perguntaDao.persistir(pergunta);
	}
	
	/**
	 * Busca um objeto pelo ID
	 * 
	 * @param Long
	 * @return Pergunta
	 */

	public Pergunta findById(Long id) {
		return perguntaDao.findById(id);
	}

}
