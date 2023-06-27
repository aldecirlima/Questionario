package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import br.com.bb.seguranca.questionario.modelo.base.PerguntaBase;
import br.com.bb.seguranca.questionario.modelo.base.SecaoBase;
import br.com.bb.seguranca.questionario.modelo.form.Avaliacao;
import br.com.bb.seguranca.questionario.modelo.form.Pergunta;
import br.com.bb.seguranca.questionario.modelo.form.Resposta;
import br.com.bb.seguranca.questionario.modelo.form.Secao;
import br.com.bb.seguranca.questionario.service.AvaliacaoService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@ViewScoped
public class AvaliacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ApplicationBean applicationBean;

	@Inject
	private AvaliacaoService avaliacaoService;

	private Avaliacao avaliacao;

	@PostConstruct
	private void init() {
		criaNovaAvaliacao();
	}

	public void criaNovaAvaliacao() {
		avaliacao = new Avaliacao();

		avaliacao.setDataAvaliacao(new Date());
		avaliacao.setMatriculaAvaliacao("F0394519");
		avaliacao.setDespacho(0);

//		Buscando questionário
		avaliacao.setQuestionarioBase(applicationBean.getQuestionarioBase());

		if (avaliacao.getQuestionarioBase() == null) {

			return;
		}

		List<SecaoBase> secoesBase = avaliacao.getQuestionarioBase().getSecoes();

		avaliacao.setSecoes(new ArrayList<>());

//		System.out.println("Avaliacao :" + avaliacao);
//		System.out.println("Questionário :" + avaliacao.getQuestionarioBase());

		for (SecaoBase secaoBase : secoesBase) {
			Secao secao = new Secao();
			secao.setSecaoBase(secaoBase);
			secao.setAvaliacao(avaliacao);

			if (secao.getSecaoBase().getPerguntas() != null && secao.getSecaoBase().getPerguntas().size() > 0) {

//				Buscando as perguntas da seção
				secao.setPerguntasForm(new ArrayList<>());
				// Perguntas N1
				for (PerguntaBase perguntaBaseN1 : secaoBase.getPerguntas()) {

					Pergunta perguntaN1 = new Pergunta();
					perguntaN1.setPergunta(perguntaBaseN1);
					perguntaN1.setSecao(secao);
					perguntaN1.setSubPerguntas(new ArrayList<>());

					Resposta respostaN1 = new Resposta();
					perguntaN1.setResposta(respostaN1);

					// Perguntas N2
					for (PerguntaBase perguntaBaseN2 : perguntaBaseN1.getSubPerguntas()) {

						Pergunta perguntaN2 = new Pergunta();
						perguntaN2.setPergunta(perguntaBaseN2);
						perguntaN2.setPerguntaMae(perguntaN1);
						perguntaN2.setSubPerguntas(new ArrayList<>());

						Resposta respostaN2 = new Resposta();
						perguntaN2.setResposta(respostaN2);

						// Perguntas N3
						for (PerguntaBase perguntaBaseN3 : perguntaBaseN2.getSubPerguntas()) {
							Pergunta perguntaN3 = new Pergunta();
							perguntaN3.setPergunta(perguntaBaseN3);
							perguntaN3.setPerguntaMae(perguntaN2);
							perguntaN2.getSubPerguntas().add(perguntaN3);

							Resposta respostaN3 = new Resposta();
							perguntaN3.setResposta(respostaN3);

						} // Fim do for PerguntaBase N3

						perguntaN1.getSubPerguntas().add(perguntaN2);

					} // Fim do for PerguntaBase N2

					secao.getPerguntas().add(perguntaN1);

				} // Fim do for PerguntaBase N1

			}

			avaliacao.getSecoes().add(secao);
//			try {
//				avaliacao = avaliacaoService.persisteAvaliacao(avaliacao);
//			} catch (Exception e) {
//				System.out.println("Erro ao salvar avaliação. " + e);
//			}

		} // Fim do for Seção

	}

	public void salvaAvaliacao() {

		avaliacao.setDataAvaliacao(new Date());
		avaliacao.setMatriculaAvaliacao("F0394519");

		try {
			if (avaliacao.getDataAvaliacao() == null) {
				avaliacaoService.persisteAvaliacao(avaliacao);				
			} else {
				avaliacaoService.salvarAvaliacao(avaliacao);
			}
			FacesMessages.info("Respostas enviadas com sucesso.");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("avaliacao", avaliacao);
			FacesContext.getCurrentInstance().getExternalContext().redirect("consultaResposta.xhtml");
		} catch (Exception e) {
			FacesMessages.error("Erro ao enviar respostas. " + e);
		}

	}

	public void imprimeQuestoes() {
		try {
			criaNovaAvaliacao();
		} catch (NoResultException nre) {
			FacesMessages.error(nre.getMessage());
			return;
		} catch (Exception e) {
			FacesMessages.error("Erro ao consultar questionário " + e.getMessage());
			return;
		}

		System.out.println("Avaliação ID: " + avaliacao.getIdAvaliacao());

		System.out.println("QST ID: " + avaliacao.getQuestionarioBase().getIdQuestionario() + " NOME: "
				+ avaliacao.getQuestionarioBase().getNomeQuestionario());

		for (Secao secao : avaliacao.getSecoes()) {
			System.out.print("    Seção ID: " + secao.getSecaoBase().getIdSecaoBase());
			System.out.println(" Nome: " + secao.getSecaoBase().getNomeSecao());

			for (Pergunta perguntaN1 : secao.getPerguntas()) {
				System.out.print("        PerguntaBase N1 ID: " + perguntaN1.getPergunta().getIdPerguntaBase());
				System.out.println(" Rótulo: " + perguntaN1.getPergunta().getTextoPergunta());

				for (Pergunta perguntaN2 : perguntaN1.getSubPerguntas()) {
					System.out.print("            PerguntaBase N2 ID: " + perguntaN2.getPergunta().getIdPerguntaBase());
					System.out.println(" Rótulo: " + perguntaN2.getPergunta().getTextoPergunta());

					for (Pergunta perguntaN3 : perguntaN2.getSubPerguntas()) {
						System.out.print(
								"                PerguntaBase N3 ID: " + perguntaN3.getPergunta().getIdPerguntaBase());
						System.out.println(" Rótulo: " + perguntaN3.getPergunta().getTextoPergunta());
					}

				}
			}
		}
	}

	public String redirect(String pagina) {
		return pagina + "?faces-redirect=true";
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
