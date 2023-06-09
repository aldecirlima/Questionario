package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.Questionario;
import br.com.bb.seguranca.questionario.modelo.Secao;
import br.com.bb.seguranca.questionario.service.QuestionarioService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@ViewScoped
public class PerguntaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Questionario> listaQuestionarios;
	
	private Long idLong;

	private Questionario perguntaQuestionario;

	private Secao objSecao;

	@Inject
	private QuestionarioService questionarioService;

	public void buscaQuestionariosNaoAtivos() {
		if (this.perguntaQuestionario == null) {
			this.perguntaQuestionario = new Questionario();
		}
		if (this.listaQuestionarios == null) {
			this.atualizaListaQuestionarios();
		}

	}

	public void atualizaListaQuestionarios() {
		try {
			this.listaQuestionarios = questionarioService.buscaQuestionariosNaoAtivos();
		} catch (Exception e) {
			FacesMessages.error("Erro na busca por questionários - " + e.getMessage());
		}
	}

	public void atualizaQuestionario() {
		try {
			perguntaQuestionario = questionarioService.findById(idLong);
			System.out.println(perguntaQuestionario);
		} catch (Exception e) {
			FacesMessages.error("Erro ao atualizar questionário " + e.getMessage());
		}
		

	}

	public List<Questionario> getListaQuestionarios() {
		return listaQuestionarios;
	}

	public Questionario getPerguntaQuestionario() {
		return perguntaQuestionario;
	}

	public void setListaQuestionarios(List<Questionario> listaQuestionarios) {
		this.listaQuestionarios = listaQuestionarios;
	}

	public void setPerguntaQuestionario(Questionario perguntaQuestionario) {
		this.perguntaQuestionario = perguntaQuestionario;
	}

	public Secao getObjSecao() {
		return objSecao;
	}

	public void setObjSecao(Secao objSecao) {
		this.objSecao = objSecao;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

}
