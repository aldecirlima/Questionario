package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.Questionario;
import br.com.bb.seguranca.questionario.modelo.Secao;
import br.com.bb.seguranca.questionario.service.QuestionarioService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@SessionScoped
public class QuestionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuestionarioService questionarioService;

	private Questionario objQuestionario;

	@PostConstruct
	public void init() {
		objQuestionario = new Questionario();
	}

	public void preparaQuestionario() {

		populaQuestionario();
		try {
			this.objQuestionario = questionarioService.persisteQuestionario(objQuestionario);
			FacesMessages.info("Questionário salvo com sucesso!");
			System.out.println("ID: " + objQuestionario.getIdQuestionario());
			System.out.println("Titulo: " + objQuestionario.getNomeQuestionario());
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar questionário!");
		}

	}

	public void salvaQuestionario() {

		if (this.objQuestionario.getIdQuestionario() == null) {
			this.preparaQuestionario();
			return;
		}
		try {
			questionarioService.salvarQuestionario(objQuestionario);
			FacesMessages.info("Questionário salvo com sucesso!");
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar questionário!");
		}

	}

	private void populaQuestionario() {
		this.objQuestionario.setSecoes(new ArrayList<Secao>());
		this.objQuestionario.setMatriculaGravacao("F0394519");
		this.objQuestionario.setDataGravacao(new Date());
	}

	public String redirect(String pagina) {
		return pagina + "?faces-redirect=true";
	}

	public Questionario getObjQuestionario() {
		return objQuestionario;
	}

	public void setObjQuestionario(Questionario objQuestionario) {
		this.objQuestionario = objQuestionario;
	}

	public QuestionarioService getQuestionarioService() {
		return questionarioService;
	}

	public void setQuestionarioService(QuestionarioService questionarioService) {
		this.questionarioService = questionarioService;
	}

}
