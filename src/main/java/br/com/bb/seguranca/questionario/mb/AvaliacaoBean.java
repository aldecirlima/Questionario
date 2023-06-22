package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.base.Secao;
import br.com.bb.seguranca.questionario.modelo.base.perguntas.Pergunta;
import br.com.bb.seguranca.questionario.modelo.form.Avaliacao;
import br.com.bb.seguranca.questionario.modelo.form.PerguntaForm;
import br.com.bb.seguranca.questionario.modelo.form.SecaoForm;
import br.com.bb.seguranca.questionario.service.QuestionarioService;
import br.com.bb.seguranca.questionario.service.SecaoService;

@Named
@ViewScoped
public class AvaliacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuestionarioService questionarioService;

	@Inject
	private SecaoService secaoService;

	private Avaliacao avaliacao;

	@PostConstruct
	private void init() {
		testAvaliacao();
	}

	public void testAvaliacao() {
		avaliacao = new Avaliacao();

//		Buscando questionário
		avaliacao.setQuestionario(questionarioService.buscaQuestionarioAtivo());

		List<Secao> secoes = avaliacao.getQuestionario().getSecoes();

		avaliacao.setSecoesForm(new ArrayList<>());

		for (Secao secao : secoes) {
			SecaoForm secaoForm = new SecaoForm();
			secaoForm.setSecao(secao);

			if (secaoForm.getSecao().getPerguntas() != null && secaoForm.getSecao().getPerguntas().size() > 0) {

//				Buscando as perguntas da seção
				Secao secaoNova = secaoService.buscaPerguntasDaSecao(secaoForm.getSecao().getIdSecao());
				secaoForm.setPerguntasForm(new ArrayList<>());
				// Perguntas N1
				for (Pergunta perguntaN1 : secaoNova.getPerguntas()) {
					PerguntaForm prgFormN1 = new PerguntaForm();
					prgFormN1.setPergunta(perguntaN1);

					prgFormN1.setSubPerguntasForm(new ArrayList<>());

					// Perguntas N2
					for (Pergunta perguntaN2 : perguntaN1.getSubPerguntas()) {

						PerguntaForm prgFormN2 = new PerguntaForm();
						prgFormN2.setPergunta(perguntaN2);
						prgFormN2.setSubPerguntasForm(new ArrayList<>());

						// Perguntas N3
						for (Pergunta perguntaN3 : perguntaN2.getSubPerguntas()) {
							PerguntaForm prgFormN3 = new PerguntaForm();
							prgFormN3.setPergunta(perguntaN3);

							prgFormN2.getSubPerguntasForm().add(prgFormN3);

						} // Fim do for Pergunta N3

						prgFormN1.getSubPerguntasForm().add(prgFormN2);

					} // Fim do for Pergunta N2

					secaoForm.getPerguntasForm().add(prgFormN1);

				} // Fim do for Pergunta N1

			}

			avaliacao.getSecoesForm().add(secaoForm);

		} // Fim do for Seção

	}

	public void imprimeQuestoes() {

		System.out.println("QST ID: " + avaliacao.getQuestionario().getIdQuestionario() + " NOME: "
				+ avaliacao.getQuestionario().getNomeQuestionario());

		for (SecaoForm secaoForm : avaliacao.getSecoesForm()) {
			System.out.print("    Seção ID: " + secaoForm.getSecao().getIdSecao());
			System.out.println(" Nome: " + secaoForm.getSecao().getNomeSecao());

			for (PerguntaForm perguntaFormN1 : secaoForm.getPerguntasForm()) {
				System.out.print("        Pergunta N1 ID: " + perguntaFormN1.getPergunta().getIdPergunta());
				System.out.println(" Rótulo: " + perguntaFormN1.getPergunta().getTextoPergunta());

				for (PerguntaForm perguntaFormN2 : perguntaFormN1.getSubPerguntasForm()) {
					System.out.print("            Pergunta N2 ID: " + perguntaFormN2.getPergunta().getIdPergunta());
					System.out.println(" Rótulo: " + perguntaFormN2.getPergunta().getTextoPergunta());

					for (PerguntaForm perguntaFormN3 : perguntaFormN2.getSubPerguntasForm()) {
						System.out.print("                Pergunta N3 ID: " + perguntaFormN3.getPergunta().getIdPergunta());
						System.out.println(" Rótulo: " + perguntaFormN3.getPergunta().getTextoPergunta());
					}

				}
			}
		}
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
