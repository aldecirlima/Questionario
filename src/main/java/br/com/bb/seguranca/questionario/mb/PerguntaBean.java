package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.primefaces.PrimeFaces;

import br.com.bb.seguranca.questionario.modelo.base.PerguntaBase;
import br.com.bb.seguranca.questionario.modelo.base.QuestionarioBase;
import br.com.bb.seguranca.questionario.modelo.base.SecaoBase;
import br.com.bb.seguranca.questionario.modelo.enuns.TipoPergunta;
import br.com.bb.seguranca.questionario.modelo.form.Opcao;
import br.com.bb.seguranca.questionario.service.OpcaoService;
import br.com.bb.seguranca.questionario.service.PerguntaService;
import br.com.bb.seguranca.questionario.service.QuestionarioService;
import br.com.bb.seguranca.questionario.service.SecaoService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@ViewScoped
public class PerguntaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	List<QuestionarioBase> listaQuestionarios;

	List<Opcao> listaOpcoesDisponiveis;

//	Map<Long, Opcao> opcoesMap;

	private Long idLong;

	private Opcao objOpcao;

	private QuestionarioBase perguntaQuestionario;

	private SecaoBase objSecaoBase;

	private PerguntaBase objPerguntaNivelUm;

	private PerguntaBase objPerguntaNivelDois;

	private PerguntaBase objPerguntaNivelTres;

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
		objPerguntaNivelUm = new PerguntaBase();
		objPerguntaNivelDois = new PerguntaBase();
		objPerguntaNivelTres = new PerguntaBase();
	}

	public void buscaQuestionariosNaoAtivos() {
		if (this.perguntaQuestionario == null) {
			this.perguntaQuestionario = new QuestionarioBase();
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
		idLong = null;
	}

	public void atualizaSecao() {
		try {
			this.objSecaoBase = secaoService.findById(idLong);
		} catch (Exception e) {
			FacesMessages.error("Erro ao atualizar seção " + e.getMessage());
		}
		idLong = null;
	}

	public void atualizaPerguntaNivelUm() {
		try {
			this.objPerguntaNivelUm = perguntaService.findById(idLong);
			this.objPerguntaNivelUm.getSubPerguntas().sort(Collections.reverseOrder());
//			System.out.println("Opção: " + objPerguntaNivelUm.getOpcoesParaSelecao().get(0).getNomeOpcao());
		} catch (Exception e) {
			FacesMessages.error("Erro ao atualizar pergunta " + e.getMessage());
		}
		idLong = null;
	}

	public void atualizaPerguntaNivelDois() {
		try {
			this.objPerguntaNivelDois = perguntaService.findById(idLong);
			this.objPerguntaNivelDois.getSubPerguntas().sort(Collections.reverseOrder());
		} catch (Exception e) {
			FacesMessages.error("Erro ao atualizar pergunta " + e.getMessage());
		}
		idLong = null;
	}

	public void preparaPerguntaNivelUm() {
		PerguntaBase pergunta = novaInstanciaPergunta();
		pergunta.setOrdem(objSecaoBase.getPerguntas().size() + 1);
		pergunta.setSecaoBase(objSecaoBase);
		objSecaoBase.getPerguntas().add(0, pergunta);
		try {
			objSecaoBase = secaoService.persisteSecao(objSecaoBase);
			FacesMessages.info("Pergunta nível um incluída. Utilize a opção editar.");
		} catch (Exception e) {
			FacesMessages.error("Erro ao criar pergunta " + e.getMessage());
		}

	}

	public void preparaPerguntaNivelDois() {
		PerguntaBase pergunta = novaInstanciaPergunta();
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
		PerguntaBase pergunta = novaInstanciaPergunta();
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

	public PerguntaBase novaInstanciaPergunta() {
		PerguntaBase pergunta = new PerguntaBase();
		pergunta.setDataCadastro(new Date());
		pergunta.setMatriculaCadastro("F0394519");
		pergunta.setPerguntaAtiva(0);
		return pergunta;
	}

//	Métodos para salvar as perguntas
	public void salvaPerguntaNivelUm() {
		objPerguntaNivelUm = insereListaOpcoes(objPerguntaNivelUm);
		Integer index = objSecaoBase.getPerguntas().indexOf(objPerguntaNivelUm);
		objSecaoBase.getPerguntas().set(index, objPerguntaNivelUm);
		try {
			secaoService.salvarSecao(objSecaoBase);
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

	public PerguntaBase insereListaOpcoes(PerguntaBase pergunta) {
		if (pergunta.getTipoPergunta() == TipoPergunta.S_N) {
			pergunta.setOpcoesParaSelecao(opcaoService.buscaSimNao());
		} else if (pergunta.getTipoPergunta() == TipoPergunta.S_N_NA) {
			pergunta.setOpcoesParaSelecao(opcaoService.buscaSimNaoNaoSeAplica());
		} else if (pergunta.getTipoPergunta() == TipoPergunta.TXT_CRT
				|| pergunta.getTipoPergunta() == TipoPergunta.TXT_LNG) {
			pergunta.setOpcoesParaSelecao(new ArrayList<>());
		}
		if (pergunta.getPerguntaVisivel() != null && pergunta.getPerguntaVisivel() == 1) {
			pergunta.setOpcaoVisivel(null);
		}
		return pergunta;
	}

	public void cleanQuestionario() {
		this.idLong = null;
		this.perguntaQuestionario = null;
		this.objSecaoBase = null;
		this.objPerguntaNivelUm = new PerguntaBase();
		this.objPerguntaNivelDois = new PerguntaBase();
		this.objPerguntaNivelTres = new PerguntaBase();
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

	public void editarOpcoesSelecionadasNivelUm() {
		objOpcao = null;
		try {
			PerguntaBase perguntaBase = perguntaService.findOpcoesParaSelecao(objPerguntaNivelUm);
			objPerguntaNivelUm = perguntaBase;
		} catch (NoResultException e) {
			objPerguntaNivelUm.setOpcoesParaSelecao(new ArrayList<>());
		}
		atualizaListaOpcoes();

	}

	public void editarOpcoesSelecionadasNivelDois() {
		objOpcao = null;
		try {
			PerguntaBase perguntaBase = perguntaService.findOpcoesParaSelecao(objPerguntaNivelDois);
			objPerguntaNivelDois = perguntaBase;
		} catch (NoResultException e) {
			objPerguntaNivelDois.setOpcoesParaSelecao(new ArrayList<>());
		}
		atualizaListaOpcoes();

	}

	public void editarOpcoesSelecionadasNivelTres() {
		objOpcao = null;
		try {
			PerguntaBase perguntaBase = perguntaService.findOpcoesParaSelecao(objPerguntaNivelTres);
			objPerguntaNivelTres = perguntaBase;
		} catch (NoResultException e) {
			objPerguntaNivelTres.setOpcoesParaSelecao(new ArrayList<>());
		}
		atualizaListaOpcoes();

	}

	public void atualizaListaOpcoes() {
		if (listaOpcoesDisponiveis == null) {
			listaOpcoesDisponiveis = opcaoService.buscaTodasOpcoesAtivas();
		}
	}

	public void insereOpcaoNivelUm() {
		if (!objPerguntaNivelUm.getOpcoesParaSelecao().contains(objOpcao)) {
			objPerguntaNivelUm.getOpcoesParaSelecao().add(objOpcao);
		} else {
			FacesMessages.error("Opção já inserida.");
		}
	}

	public void insereOpcaoNivelDois() {
		if (!objPerguntaNivelDois.getOpcoesParaSelecao().contains(objOpcao)) {
			objPerguntaNivelDois.getOpcoesParaSelecao().add(objOpcao);
		} else {
			FacesMessages.error("Opção já inserida.");
		}
	}

	public void insereOpcaoNivelTres() {
		if (!objPerguntaNivelTres.getOpcoesParaSelecao().contains(objOpcao)) {
			objPerguntaNivelTres.getOpcoesParaSelecao().add(objOpcao);
		} else {
			FacesMessages.error("Opção já inserida.");
		}
	}

	public TipoPergunta[] getTipoPergunta() {
		return TipoPergunta.values();
	}

	public List<QuestionarioBase> getListaQuestionarios() {
		return listaQuestionarios;
	}

	public QuestionarioBase getPerguntaQuestionario() {
		return perguntaQuestionario;
	}

	public void setListaQuestionarios(List<QuestionarioBase> listaQuestionarios) {
		this.listaQuestionarios = listaQuestionarios;
	}

	public void setPerguntaQuestionario(QuestionarioBase perguntaQuestionario) {
		this.perguntaQuestionario = perguntaQuestionario;
	}

	public SecaoBase getObjSecaoBase() {
		return objSecaoBase;
	}

	public void setObjSecaoBase(SecaoBase objSecaoBase) {
		this.objSecaoBase = objSecaoBase;
	}

	public PerguntaBase getObjPerguntaNivelUm() {
		return objPerguntaNivelUm;
	}

	public void setObjPerguntaNivelUm(PerguntaBase objPerguntaNivelUm) {
		this.objPerguntaNivelUm = objPerguntaNivelUm;
	}

	public PerguntaBase getObjPerguntaNivelDois() {
		return objPerguntaNivelDois;
	}

	public void setObjPerguntaNivelDois(PerguntaBase objPerguntaNivelDois) {
		this.objPerguntaNivelDois = objPerguntaNivelDois;
	}

	public PerguntaBase getObjPerguntaNivelTres() {
		return objPerguntaNivelTres;
	}

	public void setObjPerguntaNivelTres(PerguntaBase objPerguntaNivelTres) {
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
