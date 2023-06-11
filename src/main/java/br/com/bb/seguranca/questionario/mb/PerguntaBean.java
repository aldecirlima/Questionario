package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.Questionario;
import br.com.bb.seguranca.questionario.modelo.Secao;
import br.com.bb.seguranca.questionario.modelo.perguntas.Pergunta;
import br.com.bb.seguranca.questionario.service.QuestionarioService;
import br.com.bb.seguranca.questionario.service.SecaoService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@ViewScoped
public class PerguntaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Questionario> listaQuestionarios;

	private Long idLong;

	private Questionario perguntaQuestionario;

	private Secao objSecao;
	
	private Pergunta objPergunta;

	@Inject
	private QuestionarioService questionarioService;

	@Inject
	private SecaoService secaoService;


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
		} catch (Exception e) {
			FacesMessages.error("Erro ao atualizar questionário " + e.getMessage());
		}
	}

	public void atualizaSecao() {
		try {
			this.objSecao = secaoService.findById(idLong);
		} catch (Exception e) {
			FacesMessages.error("Erro ao atualizar seções " + e.getMessage());
		}
	}
	
	public void preparaPergunta() {
		Pergunta pergunta = new Pergunta();
		pergunta.setDataCadastro(new Date());
		pergunta.setMatriculaCadastro("F0394519");
		pergunta.setPerguntaAtiva(0);
		pergunta.setOrdem(objSecao.getPerguntas().size() + 1);
		pergunta.setSecao(objSecao);
		objSecao.getPerguntas().add(0, pergunta);
		try {
			objSecao = secaoService.persisteSecao(objSecao);
			FacesMessages.info("Nova pergunta incluída. Utilize a opção editar.");
		} catch (Exception e) {
			FacesMessages.error("Erro ao criar uma pergunta " + e.getMessage());
		}
		
	}
	
	public void salvaPergunta() {
		Integer index = objSecao.getPerguntas().indexOf(objPergunta);
		objSecao.getPerguntas().set(index, objPergunta);
		try {
			secaoService.salvarSecao(objSecao);
			FacesMessages.info("Pergunta salva com sucesso.");
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar pergunta " + e.getMessage());
		}
	}

	public void cleanQuestionario() {
		this.idLong = null;
		this.perguntaQuestionario = null;
		this.objSecao = null;
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

	public Pergunta getObjPergunta() {
		return objPergunta;
	}

	public void setObjPergunta(Pergunta objPergunta) {
		this.objPergunta = objPergunta;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

}
