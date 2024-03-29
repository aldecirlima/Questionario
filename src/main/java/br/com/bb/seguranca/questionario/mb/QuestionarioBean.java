package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.base.QuestionarioBase;
import br.com.bb.seguranca.questionario.modelo.base.SecaoBase;
import br.com.bb.seguranca.questionario.service.QuestionarioService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@ViewScoped
public class QuestionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	List<QuestionarioBase> listaQuestionarios;

	private Long idLong;

	@Inject
	private QuestionarioService questionarioService;

	private QuestionarioBase objQuestionario;

	@PostConstruct
	public void init() {
		this.novoQuestionario();
	}

	public void novoQuestionario() {
		objQuestionario = new QuestionarioBase();
	}

	public void preparaQuestionario() {
		populaQuestionario();
		try {
			this.objQuestionario = questionarioService.persisteQuestionario(objQuestionario);
			FacesMessages.info("Questionário salvo com sucesso! ID: " + objQuestionario.getIdQuestionario());
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar questionário!");
		}
	}

	public void salvaQuestionario() {
		if (this.objQuestionario.getIdQuestionario() == null) {
			this.preparaQuestionario();
			this.objQuestionario = new QuestionarioBase();
			return;
		}
		try {
			questionarioService.salvarQuestionario(objQuestionario);
			FacesMessages.info("Questionário salvo com sucesso!");
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar questionário!");
		}
	}

	public void atualizaListaQuestionarios() {
		try {
			this.listaQuestionarios = questionarioService.buscaQuestionariosNaoAtivos();
		} catch (Exception e) {
			FacesMessages.error("Erro na busca por questionários - " + e.getMessage());
		}
	}

	private void populaQuestionario() {
		this.objQuestionario.setSecoes(new ArrayList<SecaoBase>());
		this.objQuestionario.setMatriculaGravacao("F0394519");
		this.objQuestionario.setQuestionarioAtivo(0);
		this.objQuestionario.setDataGravacao(new Date());
	}

	public String redirect(String pagina) {
		return pagina + "?faces-redirect=true";
	}

	public String redirectInternal(String pagina) {
		return pagina;
	};

	public QuestionarioBase getObjQuestionario() {
		return objQuestionario;
	}

	public void setObjQuestionario(QuestionarioBase objQuestionario) {
		this.objQuestionario = objQuestionario;
	}

	public QuestionarioService getQuestionarioService() {
		return questionarioService;
	}

	public void setQuestionarioService(QuestionarioService questionarioService) {
		this.questionarioService = questionarioService;
	}

	public List<QuestionarioBase> getListaQuestionarios() {
		return listaQuestionarios;
	}

	public void setListaQuestionarios(List<QuestionarioBase> listaQuestionarios) {
		this.listaQuestionarios = listaQuestionarios;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

}
