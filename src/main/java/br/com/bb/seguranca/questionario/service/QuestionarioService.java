package br.com.bb.seguranca.questionario.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bb.seguranca.questionario.dao.QuestionarioDao;
import br.com.bb.seguranca.questionario.modelo.Questionario;

public class QuestionarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	QuestionarioDao questionarioDao;
	
	@Transactional
	public void salvarQuestionario(Questionario questionario ) {
		questionarioDao.salvar(questionario);
	}
	
	/**
	 * Persiste o objeto e devolve o objeto com ID
	 * 
	 * @param Questionario 
	 * @return Questionario
	 */
	@Transactional
	public Questionario persisteQuestionario(Questionario questionario) {
		
		return questionarioDao.persistir(questionario);
	}

}