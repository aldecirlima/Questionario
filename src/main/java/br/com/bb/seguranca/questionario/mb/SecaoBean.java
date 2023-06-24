package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.base.QuestionarioBase;
import br.com.bb.seguranca.questionario.modelo.base.SecaoBase;
import br.com.bb.seguranca.questionario.service.QuestionarioService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@ViewScoped
public class SecaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<QuestionarioBase> listaQuestionarios;

	private Map<Long, QuestionarioBase> mapQuestionarios;

	private QuestionarioBase secaoQuestionario;

	private Long idLong;

	private SecaoBase objSecaoBase;

	@Inject
	private QuestionarioService questionarioService;

	public void buscaQuestionariosNaoAtivos() {
		if (this.secaoQuestionario == null) {
			this.secaoQuestionario = new QuestionarioBase();
		}
		if (this.listaQuestionarios == null) {
			this.atualizaListaQuestionarios();
		}
	}

	public void atualizaListaQuestionarios() {
		try {
			this.listaQuestionarios = questionarioService.buscaQuestionariosNaoAtivos();
			mapQuestionarios = new HashMap<>();
			for (QuestionarioBase questionario : listaQuestionarios) {
				if (!mapQuestionarios.containsKey(questionario.getIdQuestionario())) {
					mapQuestionarios.put(questionario.getIdQuestionario(), questionario);
				}
			}
		} catch (Exception e) {
			FacesMessages.error("Erro na busca por questionários - " + e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	public void preparaSecao() {
		if (secaoQuestionario == null) {
			FacesMessages.error("Nenhum questionário selecionado!");
			return;
		}
		objSecaoBase = new SecaoBase();
		objSecaoBase.setMatriculaGravacao("F0394519");
		objSecaoBase.setOrdemSecao(secaoQuestionario.getSecoes().size() + 1);
		objSecaoBase.setDataGravacao(new Date());
		objSecaoBase.setSecaoAtiva(0);
		objSecaoBase.setQuestionario(secaoQuestionario);
		secaoQuestionario.getSecoes().add(objSecaoBase);
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
			Integer indice = secaoQuestionario.getSecoes().indexOf(objSecaoBase);
			secaoQuestionario.getSecoes().set(indice, objSecaoBase);
			try {
				secaoQuestionario = questionarioService.persisteQuestionario(secaoQuestionario);
				mapQuestionarios.replace(secaoQuestionario.getIdQuestionario(), secaoQuestionario);
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

	public void atualizaQuestionario() {
		try {
			secaoQuestionario = mapQuestionarios.get(idLong);
//			if (secaoQuestionario.getSecoes() == null)  {
//				secaoQuestionario.setSecoes(new ArrayList<SecaoBase>());
//			}
		} catch (Exception e) {
			FacesMessages.error("Erro ao atualizar questionário " + e.getMessage());
		}
	}

	public void cleanQuestionario() {
		this.secaoQuestionario = null;
		this.idLong = null;
	}

	public List<QuestionarioBase> getListaQuestionarios() {
		return listaQuestionarios;
	}

	public QuestionarioBase getSecaoQuestionario() {
		return secaoQuestionario;
	}

	public void setListaQuestionarios(List<QuestionarioBase> listaQuestionarios) {
		this.listaQuestionarios = listaQuestionarios;
	}

	public void setSecaoQuestionario(QuestionarioBase secaoQuestionario) {
		this.secaoQuestionario = secaoQuestionario;
	}

	public SecaoBase getObjSecaoBase() {
		return objSecaoBase;
	}

	public void setObjSecaoBase(SecaoBase objSecaoBase) {
		this.objSecaoBase = objSecaoBase;
	}

	public Map<Long, QuestionarioBase> getMapQuestionarios() {
		return mapQuestionarios;
	}

	public void setMapQuestionarios(Map<Long, QuestionarioBase> mapQuestionarios) {
		this.mapQuestionarios = mapQuestionarios;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

}
