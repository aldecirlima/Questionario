package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.base.QuestionarioBase;
import br.com.bb.seguranca.questionario.modelo.base.SecaoBase;
import br.com.bb.seguranca.questionario.service.QuestionarioService;
import br.com.bb.seguranca.questionario.service.SecaoService;

@Named
@ApplicationScoped
public class ApplicationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuestionarioService questionarioService;

	@Inject
	private SecaoService secaoService;

	private QuestionarioBase questionarioBase;

	@PostConstruct
	public void buscaQuestionário() {
		questionarioBase = new QuestionarioBase();

//		Buscando questionário
		try {
			questionarioBase = questionarioService.buscaQuestionarioAtivo();			
		} catch (Exception e) {
			questionarioBase = null;
			return;
		}

		for (int i = 0; i < questionarioBase.getSecoes().size(); i++) {

//			Buscando as perguntas da seção
			SecaoBase newSecao = secaoService.buscaPerguntasDaSecao(questionarioBase.getSecoes().get(i).getIdSecaoBase());
			questionarioBase.getSecoes().set(i, newSecao);
		}
	}

	public QuestionarioBase getQuestionarioBase() {
		return questionarioBase;
	}

	public void setQuestionarioBase(QuestionarioBase questionarioBase) {
		this.questionarioBase = questionarioBase;
	}
}
