package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.base.QuestionarioBase;
import br.com.bb.seguranca.questionario.modelo.base.SecaoBase;
import br.com.bb.seguranca.questionario.modelo.form.Opcao;
import br.com.bb.seguranca.questionario.service.OpcaoService;
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

	@Inject
	private OpcaoService opcaoService;

	private QuestionarioBase questionarioBase;

	private Map<Long, Opcao> opcoesMap;

	@PostConstruct
	public void carregarAplicacao() {
		this.buscaQuestionario();
		this.buscaOpcoes();
	}

	public void buscaOpcoes() {
		if (opcoesMap == null || opcoesMap.size() == 0) {
			opcoesMap = opcaoService.findAllMap();
		}
	}	

	public void buscaQuestionario() {
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
			SecaoBase newSecao = secaoService
					.buscaPerguntasDaSecao(questionarioBase.getSecoes().get(i).getIdSecaoBase());
			questionarioBase.getSecoes().set(i, newSecao);
		}
	}

	public QuestionarioBase getQuestionarioBase() {
		return questionarioBase;
	}

	public void setQuestionarioBase(QuestionarioBase questionarioBase) {
		this.questionarioBase = questionarioBase;
	}

	public Map<Long, Opcao> getOpcoesMap() {
		return opcoesMap;
	}

	public void setOpcoesMap(Map<Long, Opcao> opcoesMap) {
		this.opcoesMap = opcoesMap;
	}
}
