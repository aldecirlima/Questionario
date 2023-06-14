package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.bb.seguranca.questionario.modelo.Questionario;
import br.com.bb.seguranca.questionario.modelo.Secao;
import br.com.bb.seguranca.questionario.modelo.perguntas.Pergunta;
import br.com.bb.seguranca.questionario.modelo.perguntas.TipoPergunta;
import br.com.bb.seguranca.questionario.service.PerguntaService;
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

	private Pergunta objSubpergunta;

	@Inject
	private QuestionarioService questionarioService;

	@Inject
	private SecaoService secaoService;

	@Inject
	private PerguntaService perguntaService;

	@PostConstruct
	public void init() {
		objPergunta = new Pergunta();
		objSubpergunta = new Pergunta();
	}

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
			FacesMessages.error("Erro ao atualizar seção " + e.getMessage());
		}
	}

	public void atualizaPergunta() {
		try {
			this.objPergunta = perguntaService.findById(idLong);
		} catch (Exception e) {
			FacesMessages.error("Erro ao atualizar pergunta " + e.getMessage());
		}
	}

	public void preparaPergunta() {
		Pergunta pergunta = new Pergunta();
		pergunta.setDataCadastro(new Date());
		pergunta.setMatriculaCadastro("F0394519");
		pergunta.setPerguntaAtiva(0);
		if (objSecao.getPerguntas() != null) {
			pergunta.setOrdem(objSecao.getPerguntas().size() + 1);
		} else {
			pergunta.setOrdem(0);
		}
		pergunta.setSecao(objSecao);
		objSecao.getPerguntas().add(0, pergunta);
		try {
			objSecao = secaoService.persisteSecao(objSecao);
			FacesMessages.info("Nova pergunta incluída. Utilize a opção editar.");
		} catch (Exception e) {
			FacesMessages.error("Erro ao criar uma pergunta " + e.getMessage());
		}

	}

	public void preparaSubPergunta() {
		Pergunta pergunta = new Pergunta();
		pergunta.setDataCadastro(new Date());
		pergunta.setMatriculaCadastro("F0394519");
		pergunta.setPerguntaAtiva(0);
		if (objSubpergunta.getSubPerguntas() != null) {
			pergunta.setOrdem(objSubpergunta.getSubPerguntas().size() + 1);
		} else {
			pergunta.setOrdem(1);
		}
		pergunta.setPerguntaMae(objPergunta);
		objPergunta.getSubPerguntas().add(0, pergunta);
		try {
			objPergunta = perguntaService.persistePergunta(objPergunta);
			FacesMessages.info("Nova subpergunta incluída. Utilize a opção editar.");
		} catch (Exception e) {
			FacesMessages.error("Erro ao criar uma subpergunta " + e.getMessage());
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
		PrimeFaces.current().executeScript("PF('managePerguntaDialog').hide()");
		PrimeFaces.current().ajax().update("formPerguntas");
	}

	public void salvaSubpergunta() {
		Integer index = objPergunta.getSubPerguntas().indexOf(objSubpergunta);
		objPergunta.getSubPerguntas().set(index, objSubpergunta);
		try {
			perguntaService.salvarPergunta(objPergunta);
			FacesMessages.info("Subpergunta salva com sucesso.");
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar Subpergunta " + e.getMessage());
		}
		PrimeFaces.current().executeScript("PF('managePerguntaDialog').hide()");
		PrimeFaces.current().ajax().update("formPerguntas");
	}

	public void cleanQuestionario() {
		this.idLong = null;
		this.perguntaQuestionario = null;
		this.objSecao = null;
		this.objPergunta = new Pergunta();
		this.objSubpergunta = new Pergunta();
	}

	public TipoPergunta[] getTipoPergunta() {
		return TipoPergunta.values();
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

	public Pergunta getObjSubpergunta() {
		return objSubpergunta;
	}

	public void setObjSubpergunta(Pergunta objSubpergunta) {
		this.objSubpergunta = objSubpergunta;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

}
