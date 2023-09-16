package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.form.Avaliacao;
import br.com.bb.seguranca.questionario.modelo.form.Opcao;
import br.com.bb.seguranca.questionario.modelo.form.Pergunta;
import br.com.bb.seguranca.questionario.modelo.form.Secao;
import br.com.bb.seguranca.questionario.service.AvaliacaoService;
import br.com.bb.seguranca.questionario.service.PerguntaService;
import br.com.bb.seguranca.questionario.service.SecaoService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@SessionScoped
public class ConsultaAvaliacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ApplicationBean applicationBean;

	@Inject
	private AvaliacaoService avaliacaoService;

	@Inject
	private SecaoService secaoService;

	@Inject
	private PerguntaService perguntaService;

	private Map<Long, Avaliacao> mapAvaliacoes;

	private Long idLong = null;

	private Avaliacao objAvaliacao;

	private Boolean parametroPassagem;

	private List<Avaliacao> listAvaliacoes;

	@PostConstruct
	private void init() {
		parametroPassagem = true;
		objAvaliacao = new Avaliacao();
		listAvaliacoes = avaliacaoService.buscaAvaliacoes();
		if (applicationBean.getOpcoesMap() == null) {
			applicationBean.buscaOpcoes();			
		}
	}

	public void carregarPagina() {
		listAvaliacoes = avaliacaoService.buscaAvaliacoes();
	}

	public void carregaAvaliacao() {

		if (idLong == null) {
			return;
		}

		if (mapAvaliacoes == null) {
			mapAvaliacoes = new HashMap<>();
		}

		if (mapAvaliacoes.get(idLong) != null) {
			objAvaliacao = mapAvaliacoes.get(idLong);
		} else {

			objAvaliacao = avaliacaoService.findById(idLong);

			for (Secao secao : objAvaliacao.getSecoes()) {
				secao = secaoService.findSecaoId(secao.getIdSecao());
				for (Pergunta pergunta : secao.getPerguntas()) {
					pergunta = perguntaService.findPerguntaId(pergunta.getIdPergunta());

					if (pergunta.getPergunta().getOpcoesParaSelecao() != null) {
						List<Opcao> list01 = new ArrayList<>();
						for (Opcao opcao : pergunta.getPergunta().getOpcoesParaSelecao()) {
							opcao = applicationBean.getOpcoesMap().get(opcao.getIdOpcao());
							list01.add(opcao);
						}
						pergunta.getPergunta().setOpcoesParaSelecao(list01);
					}

					if (pergunta.getResposta().getOpcoesSelecionadas() != null) {
						List<Opcao> list01 = new ArrayList<>();
						for (Opcao opcao : pergunta.getResposta().getOpcoesSelecionadas()) {
							opcao = applicationBean.getOpcoesMap().get(opcao.getIdOpcao());
							list01.add(opcao);
						}
						pergunta.getResposta().setOpcoesSelecionadas(list01);
					}
					if (pergunta.getPergunta().getOpcoesParaSelecao() != null) {
						List<Opcao> list01 = new ArrayList<>();
						for (Opcao opcao : pergunta.getPergunta().getOpcoesParaSelecao()) {
							opcao = applicationBean.getOpcoesMap().get(opcao.getIdOpcao());
							list01.add(opcao);
						}
						pergunta.getPergunta().setOpcoesParaSelecao(list01);
					}

					for (Pergunta perguntaN2 : pergunta.getSubPerguntas()) {
						perguntaN2 = perguntaService.findPerguntaId(perguntaN2.getIdPergunta());

						if (perguntaN2.getPergunta().getOpcoesParaSelecao() != null) {
							List<Opcao> list01 = new ArrayList<>();
							for (Opcao opcao : perguntaN2.getPergunta().getOpcoesParaSelecao()) {
								opcao = applicationBean.getOpcoesMap().get(opcao.getIdOpcao());
								list01.add(opcao);
							}
							perguntaN2.getPergunta().setOpcoesParaSelecao(list01);
						}

						if (perguntaN2.getResposta().getOpcoesSelecionadas() != null) {
							List<Opcao> list01 = new ArrayList<>();
							for (Opcao opcao : perguntaN2.getResposta().getOpcoesSelecionadas()) {
								opcao = applicationBean.getOpcoesMap().get(opcao.getIdOpcao());
								list01.add(opcao);
							}
							perguntaN2.getResposta().setOpcoesSelecionadas(list01);
						}

						if (perguntaN2.getPergunta().getOpcoesParaSelecao() != null) {
							List<Opcao> list01 = new ArrayList<>();
							for (Opcao opcao : perguntaN2.getPergunta().getOpcoesParaSelecao()) {
								opcao = applicationBean.getOpcoesMap().get(opcao.getIdOpcao());
								list01.add(opcao);
							}
							perguntaN2.getPergunta().setOpcoesParaSelecao(list01);
						}

						for (Pergunta perguntaN3 : perguntaN2.getSubPerguntas()) {
							perguntaN3 = perguntaService.findPerguntaId(perguntaN3.getIdPergunta());
							if (perguntaN3.getPergunta().getOpcoesParaSelecao() != null) {
								List<Opcao> list01 = new ArrayList<>();
								for (Opcao opcao : perguntaN3.getPergunta().getOpcoesParaSelecao()) {
									opcao = applicationBean.getOpcoesMap().get(opcao.getIdOpcao());
									list01.add(opcao);
								}
								perguntaN3.getPergunta().setOpcoesParaSelecao(list01);
							}
							if (perguntaN3.getResposta().getOpcoesSelecionadas() != null) {
								List<Opcao> list01 = new ArrayList<>();
								for (Opcao opcao : perguntaN3.getResposta().getOpcoesSelecionadas()) {
									opcao = applicationBean.getOpcoesMap().get(opcao.getIdOpcao());
									list01.add(opcao);
								}
								perguntaN3.getResposta().setOpcoesSelecionadas(list01);
							}

							if (perguntaN3.getPergunta().getOpcoesParaSelecao() != null) {
								List<Opcao> list01 = new ArrayList<>();
								for (Opcao opcao : perguntaN3.getPergunta().getOpcoesParaSelecao()) {
									opcao = applicationBean.getOpcoesMap().get(opcao.getIdOpcao());
									list01.add(opcao);
								}
								perguntaN3.getPergunta().setOpcoesParaSelecao(list01);
							}

						}
					}
				}
			}
		}

		if (mapAvaliacoes.get(objAvaliacao.getIdAvaliacao()) != objAvaliacao) {
			mapAvaliacoes.put(objAvaliacao.getIdAvaliacao(), objAvaliacao);
		}

		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("avaliacao", objAvaliacao);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("passagem", parametroPassagem);
			FacesContext.getCurrentInstance().getExternalContext().redirect("consultaResposta.xhtml");
		} catch (Exception e) {
			FacesMessages.error("Não foi possível carregar a visualização");
		}
	}

	public void voltaPaginaConsultas() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash();
			FacesContext.getCurrentInstance().getExternalContext().redirect("consultas.xhtml");
		} catch (Exception e) {
			FacesMessages.error("Não foi possível retornar à pagina anterior.");
		}
	}

	public List<Avaliacao> getListAvaliacoes() {
		return listAvaliacoes;
	}

	public void setListAvaliacoes(List<Avaliacao> listAvaliacoes) {
		this.listAvaliacoes = listAvaliacoes;
	}

	public Avaliacao getObjAvaliacao() {
		return objAvaliacao;
	}

	public void setObjAvaliacao(Avaliacao objAvaliacao) {
		this.objAvaliacao = objAvaliacao;
	}

	public Boolean getParametroPassagem() {
		return parametroPassagem;
	}

	public void setParametroPassagem(Boolean parametroPassagem) {
		this.parametroPassagem = parametroPassagem;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

	public Map<Long, Avaliacao> getMapAvaliacoes() {
		return mapAvaliacoes;
	}

	public void setMapAvaliacoes(Map<Long, Avaliacao> mapAvaliacoes) {
		this.mapAvaliacoes = mapAvaliacoes;
	}

}
