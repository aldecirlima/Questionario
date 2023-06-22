package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.bb.seguranca.questionario.modelo.base.Questionario;
import br.com.bb.seguranca.questionario.modelo.base.Secao;
import br.com.bb.seguranca.questionario.modelo.base.perguntas.Opcao;
import br.com.bb.seguranca.questionario.modelo.base.perguntas.Pergunta;
import br.com.bb.seguranca.questionario.modelo.base.perguntas.TipoPergunta;
import br.com.bb.seguranca.questionario.service.OpcaoService;
import br.com.bb.seguranca.questionario.service.PerguntaService;
import br.com.bb.seguranca.questionario.service.QuestionarioService;
import br.com.bb.seguranca.questionario.service.SecaoService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@ViewScoped
public class PerguntaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Questionario> listaQuestionarios;

	List<Opcao> listaOpcoesDisponiveis;

	Map<Long, Opcao> opcoesMap;

	private Long idLong;

	private Opcao objOpcao;

	private Questionario perguntaQuestionario;

	private Secao objSecao;

	private Pergunta objPerguntaNivelUm;

	private Pergunta objPerguntaNivelDois;

	private Pergunta objPerguntaNivelTres;

	@Inject
	private QuestionarioService questionarioService;

	@Inject
	private SecaoService secaoService;

	@Inject
	private PerguntaService perguntaService;

	@Inject
	private OpcaoService opcaoService;

	@PostConstruct
	public void init() {
		objPerguntaNivelUm = new Pergunta();
		objPerguntaNivelDois = new Pergunta();
		objPerguntaNivelTres = new Pergunta();
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

	public void atualizaPerguntaNivelUm() {
		try {
			this.objPerguntaNivelUm = perguntaService.findById(idLong);
			this.objPerguntaNivelUm.getSubPerguntas().sort(Collections.reverseOrder());
		} catch (Exception e) {
			FacesMessages.error("Erro ao atualizar pergunta " + e.getMessage());
		}
	}

	public void atualizaPerguntaNivelDois() {
		try {
			this.objPerguntaNivelDois = perguntaService.findById(idLong);
			this.objPerguntaNivelDois.getSubPerguntas().sort(Collections.reverseOrder());
		} catch (Exception e) {
			FacesMessages.error("Erro ao atualizar pergunta " + e.getMessage());
		}
	}

	public void preparaPerguntaNivelUm() {
		Pergunta pergunta = novaInstanciaPergunta();
		pergunta.setOrdem(objSecao.getPerguntas().size() + 1);
		pergunta.setSecao(objSecao);
		objSecao.getPerguntas().add(0, pergunta);
		try {
			objSecao = secaoService.persisteSecao(objSecao);
			FacesMessages.info("Pergunta nível um incluída. Utilize a opção editar.");
		} catch (Exception e) {
			FacesMessages.error("Erro ao criar pergunta " + e.getMessage());
		}
	}

	public void preparaPerguntaNivelDois() {
		Pergunta pergunta = novaInstanciaPergunta();
		pergunta.setOrdem(objPerguntaNivelUm.getSubPerguntas().size() + 1);
		pergunta.setPerguntaMae(objPerguntaNivelUm);
		objPerguntaNivelUm.getSubPerguntas().add(0, pergunta);
		try {
			objPerguntaNivelUm = perguntaService.persistePergunta(objPerguntaNivelUm);
			FacesMessages.info("Pergunta nível dois incluída. Utilize a opção editar.");
		} catch (Exception e) {
			FacesMessages.error("Erro ao criar pergunta " + e.getMessage());
		}
	}

	public void preparaPerguntaNivelTres() {
		Pergunta pergunta = novaInstanciaPergunta();
		pergunta.setOrdem(objPerguntaNivelDois.getSubPerguntas().size() + 1);
		pergunta.setPerguntaMae(objPerguntaNivelDois);
		objPerguntaNivelDois.getSubPerguntas().add(0, pergunta);
		try {
			objPerguntaNivelDois = perguntaService.persistePergunta(objPerguntaNivelDois);
			FacesMessages.info("Pergunta nível três incluída. Utilize a opção editar.");
		} catch (Exception e) {
			FacesMessages.error("Erro ao criar pergunta " + e.getMessage());
		}
	}

	public Pergunta novaInstanciaPergunta() {
		Pergunta pergunta = new Pergunta();
		pergunta.setDataCadastro(new Date());
		pergunta.setMatriculaCadastro("F0394519");
		pergunta.setPerguntaAtiva(0);
		return pergunta;
	}

//	Métodos para salvar as perguntas
	public void salvaPerguntaNivelUm() {
		objPerguntaNivelUm = insereListaOpcoes(objPerguntaNivelUm);
		Integer index = objSecao.getPerguntas().indexOf(objPerguntaNivelUm);
		objSecao.getPerguntas().set(index, objPerguntaNivelUm);
		try {
			secaoService.salvarSecao(objSecao);
			FacesMessages.info("Pergunta salva com sucesso.");
			this.fechaDialogPergunta();
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar pergunta " + e.getMessage());
		}
	}

	public void salvaPerguntaNivelDois() {
		objPerguntaNivelDois = insereListaOpcoes(objPerguntaNivelDois);
		Integer index = objPerguntaNivelUm.getSubPerguntas().indexOf(objPerguntaNivelDois);
		objPerguntaNivelUm.getSubPerguntas().set(index, objPerguntaNivelDois);
		try {
			perguntaService.salvarPergunta(objPerguntaNivelUm);
			FacesMessages.info("Pergunta salva com sucesso.");
			this.fechaDialogPergunta();
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar pergunta " + e.getMessage());
		}
	}

	public void salvaPerguntaNivelTres() {
		objPerguntaNivelTres = insereListaOpcoes(objPerguntaNivelTres);
		Integer index = objPerguntaNivelDois.getSubPerguntas().indexOf(objPerguntaNivelTres);
		objPerguntaNivelDois.getSubPerguntas().set(index, objPerguntaNivelTres);

		try {
			perguntaService.salvarPergunta(objPerguntaNivelDois);
			FacesMessages.info("Pergunta salva com sucesso.");
			this.fechaDialogPergunta();
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar pergunta " + e.getMessage());
		}
	}
//	Fim dos métodos para salvar perguntas

	public void fechaDialogPergunta() {
		PrimeFaces.current().executeScript("PF('managePerguntaDialog').hide()");
		PrimeFaces.current().executeScript("PF('manageOpcoesDialog').hide()");
		PrimeFaces.current().ajax().update("formPerguntas");
	}

	public Pergunta insereListaOpcoes(Pergunta pergunta) {
		if (pergunta.getTipoPergunta() == TipoPergunta.S_N) {
			pergunta.setOpcoesParaSelecao(opcaoService.buscaSimNao());
		} else if (pergunta.getTipoPergunta() == TipoPergunta.S_N_NA) {
			pergunta.setOpcoesParaSelecao(opcaoService.buscaSimNaoNaoSeAplica());
		} else if (pergunta.getTipoPergunta() == TipoPergunta.TXT_CRT
				|| pergunta.getTipoPergunta() == TipoPergunta.TXT_LNG) {
			pergunta.setOpcoesParaSelecao(new ArrayList<>());
		}
		return pergunta;
	}

	public void cleanQuestionario() {
		this.idLong = null;
		this.perguntaQuestionario = null;
		this.objSecao = null;
		this.objPerguntaNivelUm = new Pergunta();
		this.objPerguntaNivelDois = new Pergunta();
		this.objPerguntaNivelTres = new Pergunta();
	}

	/**
	 * Parametro de entrada deve corresponder ao nível da pergunta 1, 2, 3...
	 * passado diretamente na Expression Language na pagina .xhtml Ex: nível 1 =
	 * removeOpcaoPergunta(1)
	 * 
	 * @param nivel
	 */
	public void removeOpcaoPergunta(int nivel) {
		switch (nivel) {
		case 1:
			if (objPerguntaNivelUm.getOpcoesParaSelecao().contains(objOpcao)) {
				objPerguntaNivelUm.getOpcoesParaSelecao().remove(objOpcao);
			}
			break;
		case 2:
			if (objPerguntaNivelDois.getOpcoesParaSelecao().contains(objOpcao)) {
				objPerguntaNivelDois.getOpcoesParaSelecao().remove(objOpcao);
			}
			break;
		case 3:
			if (objPerguntaNivelTres.getOpcoesParaSelecao().contains(objOpcao)) {
				objPerguntaNivelTres.getOpcoesParaSelecao().remove(objOpcao);
			}
			break;

		default:
			break;
		}
	}

	public void editarOpcoesSelecionadas() {
		idLong = null;
		if (listaOpcoesDisponiveis == null || listaOpcoesDisponiveis.size() == 0) {
			listaOpcoesDisponiveis = opcaoService.buscaTodasOpcoesAtivas();
			opcoesMap = new HashMap<>();
			for (Opcao opcao : listaOpcoesDisponiveis) {
				opcoesMap.put(opcao.getIdOpcao(), opcao);
			}
		}
	}

	public void insereOpcaoNivelUm() {
		objOpcao = opcoesMap.get(idLong);
		if (!objPerguntaNivelUm.getOpcoesParaSelecao().contains(objOpcao)) {
			objPerguntaNivelUm.getOpcoesParaSelecao().add(objOpcao);
		} else {
			FacesMessages.error("Opção já inserida.");
		}
	}

	public void insereOpcaoNivelDois() {
		objOpcao = opcoesMap.get(idLong);
		if (!objPerguntaNivelDois.getOpcoesParaSelecao().contains(objOpcao)) {
			objPerguntaNivelDois.getOpcoesParaSelecao().add(objOpcao);
		} else {
			FacesMessages.error("Opção já inserida.");
		}
	}

	public void insereOpcaoNivelTres() {
		objOpcao = opcoesMap.get(idLong);
		if (!objPerguntaNivelTres.getOpcoesParaSelecao().contains(objOpcao)) {
			objPerguntaNivelTres.getOpcoesParaSelecao().add(objOpcao);
		} else {
			FacesMessages.error("Opção já inserida.");
		}
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

	public Pergunta getObjPerguntaNivelUm() {
		return objPerguntaNivelUm;
	}

	public void setObjPerguntaNivelUm(Pergunta objPerguntaNivelUm) {
		this.objPerguntaNivelUm = objPerguntaNivelUm;
	}

	public Pergunta getObjPerguntaNivelDois() {
		return objPerguntaNivelDois;
	}

	public void setObjPerguntaNivelDois(Pergunta objPerguntaNivelDois) {
		this.objPerguntaNivelDois = objPerguntaNivelDois;
	}

	public Pergunta getObjPerguntaNivelTres() {
		return objPerguntaNivelTres;
	}

	public void setObjPerguntaNivelTres(Pergunta objPerguntaNivelTres) {
		this.objPerguntaNivelTres = objPerguntaNivelTres;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

	public Opcao getObjOpcao() {
		return objOpcao;
	}

	public void setObjOpcao(Opcao objOpcao) {
		this.objOpcao = objOpcao;
	}

	public List<Opcao> getListaOpcoesDisponiveis() {
		return listaOpcoesDisponiveis;
	}

	public void setListaOpcoesDisponiveis(List<Opcao> listaOpcoesDisponiveis) {
		this.listaOpcoesDisponiveis = listaOpcoesDisponiveis;
	}

}
