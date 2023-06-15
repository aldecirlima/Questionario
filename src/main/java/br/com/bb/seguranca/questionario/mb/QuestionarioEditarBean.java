package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.Questionario;
import br.com.bb.seguranca.questionario.service.QuestionarioService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@ViewScoped
public class QuestionarioEditarBean implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Questionario> listaQuestionarios;

	private Long idLong;

	@Inject
	private QuestionarioService questionarioService;

	private Questionario objQuestionario;

	@PostConstruct
	public void init() {
		this.atualizaListaQuestionarios();
		this.novoQuestionario();
	}

	public void novoQuestionario() {
		objQuestionario = new Questionario();
	}

	public void salvaQuestionario() {
		try {
			questionarioService.salvarQuestionario(objQuestionario);
			atualizaListaQuestionarios();
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

	public void deleteQuestionario() {
		this.objQuestionario.setQuestionarioAtivo(3);
		questionarioService.salvarQuestionario(objQuestionario);
		this.atualizaListaQuestionarios();
		FacesMessages.info("Questionário excluído com sucesso!");
	}

	public String redirect(String pagina) {
		return pagina + "?faces-redirect=true";
	}

	public String redirectInternal(String pagina) {
		return pagina;
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

	public List<Questionario> getListaQuestionarios() {
		return listaQuestionarios;
	}

	public void setListaQuestionarios(List<Questionario> listaQuestionarios) {
		this.listaQuestionarios = listaQuestionarios;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

}
