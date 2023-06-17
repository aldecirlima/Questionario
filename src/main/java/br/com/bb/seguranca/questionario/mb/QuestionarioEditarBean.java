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
			objQuestionario = questionarioService.persisteQuestionario(objQuestionario);
			int index = listaQuestionarios.indexOf(objQuestionario);
			listaQuestionarios.set(index, objQuestionario);
			FacesMessages.info("Questionário salvo com sucesso!");
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar questionário!");
		}
	}

	public void atualizaListaQuestionarios() {
		try {
			this.listaQuestionarios = questionarioService.buscaQuestionariosNaoAtivosSemFetch();
		} catch (Exception e) {
			FacesMessages.error("Erro na busca por questionários - " + e.getMessage());
		}
	}

	public void deleteQuestionario() {
		objQuestionario.setQuestionarioAtivo(3);
		try {
			objQuestionario = questionarioService.persisteQuestionario(objQuestionario);
			int index = listaQuestionarios.indexOf(objQuestionario);
			listaQuestionarios.remove(index);
			FacesMessages.info("Questionário excluído com sucesso!");
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar questionário!");
		}
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
