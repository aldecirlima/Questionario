package br.com.bb.seguranca.questionario.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bb.seguranca.questionario.dao.QuestionarioDao;
import br.com.bb.seguranca.questionario.modelo.base.QuestionarioBase;

public class QuestionarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	QuestionarioDao questionarioDao;

	@Transactional
	public void salvarQuestionario(QuestionarioBase questionario) {
		questionarioDao.salvar(questionario);
	}

	/**
	 * Persiste o objeto e devolve o objeto com ID
	 * 
	 * @param QuestionarioBase
	 * @return QuestionarioBase
	 */
	@Transactional
	public QuestionarioBase persisteQuestionario(QuestionarioBase questionario) {

		return questionarioDao.persistir(questionario);
	}

	@Transactional
	public List<QuestionarioBase> buscaQuestionariosNaoAtivos() {
		return questionarioDao.buscaQuestionariosNaoAtivos();
	}
	
	public List<QuestionarioBase> buscaQuestionariosAtivos() {
		return questionarioDao.buscaQuestionariosAtivos();
	}
	
	public QuestionarioBase buscaQuestionarioAtivo() {
		return questionarioDao.buscaQuestionarioAtivo();
	}

	public List<QuestionarioBase> buscaQuestionariosNaoAtivosSemFetch() {
		return questionarioDao.buscaQuestionariosNaoAtivosSemFetch();
	}

	public QuestionarioBase findById(Long id) {
		return questionarioDao.findById(id);
	}

}
