package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.Date;
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
public class SecaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Questionario> listaQuestionarios;

	private Questionario secaoQuestionario;

	private Secao objSecao;

	@Inject
	private QuestionarioService questionarioService;

	public void buscaQuestionariosNaoAtivos() {
		if (this.secaoQuestionario == null) {
			this.secaoQuestionario = new Questionario();
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

	public void preparaSecao() {

		if (secaoQuestionario == null) {
			FacesMessages.error("Nenhum questionário selecionado!");
			return;
		}
		objSecao = new Secao();
		objSecao.setMatriculaGravacao("F0394519");
		objSecao.setOrdemSecao(secaoQuestionario.getSecoes().size() + 1);
		objSecao.setDataGravacao(new Date());
		objSecao.setSecaoAtiva(0);
		objSecao.setQuestionario(secaoQuestionario);
		secaoQuestionario.getSecoes().add(objSecao);
		try {
			secaoQuestionario = questionarioService.persisteQuestionario(secaoQuestionario);
			atualizaListaQuestionarios();
			FacesMessages.info("Nova seção incluída. Utilize a opção editar.");
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar questionário. " + e.getMessage());
		}

	}

	public void salvarSecao() {
		if (secaoQuestionario != null) {
			Integer indice = secaoQuestionario.getSecoes().indexOf(objSecao);
			secaoQuestionario.getSecoes().set(indice, objSecao);
			try {
				secaoQuestionario = questionarioService.persisteQuestionario(secaoQuestionario);
				atualizaListaQuestionarios();
				FacesMessages.info("Seção salva com sucesso!");

			} catch (Exception e) {
				FacesMessages.error("Erro ao salvar seção. " + e.getMessage());
			}
		}
	}

	public void verSecoes() {
		if (secaoQuestionario == null) {
			FacesMessages.error("Nenhum questionário selecionado!");
		}
	}

	public List<Questionario> getListaQuestionarios() {
		return listaQuestionarios;
	}

	public Questionario getSecaoQuestionario() {
		return secaoQuestionario;
	}

	public void setListaQuestionarios(List<Questionario> listaQuestionarios) {
		this.listaQuestionarios = listaQuestionarios;
	}

	public void setSecaoQuestionario(Questionario secaoQuestionario) {
		this.secaoQuestionario = secaoQuestionario;
	}

	public Secao getObjSecao() {
		return objSecao;
	}

	public void setObjSecao(Secao objSecao) {
		this.objSecao = objSecao;
	}

}
