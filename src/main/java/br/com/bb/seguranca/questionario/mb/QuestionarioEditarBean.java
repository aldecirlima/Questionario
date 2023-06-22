package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.bb.seguranca.questionario.modelo.base.Questionario;
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
		// Se o questionário estiver sendo ativado, buscar o questionário ativo e
		// baixar.
		if (objQuestionario.getQuestionarioAtivo().equals(1)) {
			objQuestionario.setDataAtivacao(new Date());
			objQuestionario.setMatriculaAtivacao("F0394519");
			try {
				List<Questionario> questionariosAtivos = questionarioService.buscaQuestionariosAtivos();
				if (questionariosAtivos != null && questionariosAtivos.size() > 0) {
					for (Questionario questionario : questionariosAtivos) {
						questionario.setQuestionarioAtivo(3);
						questionario.setDataEncerramento(new Date());
						questionario.setMatriculaEncerramento("F0394519");
						questionarioService.salvarQuestionario(questionario);
					}
				}
			} catch (Exception e) {
				FacesMessages.error("Erro ao salvar questionário!");
				return;
			}
		}

		try {
			objQuestionario = questionarioService.persisteQuestionario(objQuestionario);
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar questionário!");
			return;
		}

		if (objQuestionario.getQuestionarioAtivo().equals(1)) {
			listaQuestionarios.remove(objQuestionario);
		} else {
			int index = listaQuestionarios.indexOf(objQuestionario);
			listaQuestionarios.set(index, objQuestionario);
		}
		FacesMessages.info("Questionário salvo com sucesso!");
		PrimeFaces.current().executeScript("PF('manageQuestionarioDialog').hide()");
		PrimeFaces.current().ajax().update("formCadastroQuestionario");
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
		objQuestionario.setMatriculaEncerramento("F0394519");
		objQuestionario.setDataEncerramento(new Date());
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
