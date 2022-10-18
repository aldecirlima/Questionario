package br.com.bb.seguranca.questionario.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.bb.seguranca.questionario.modelo.Questionario;
import br.com.bb.seguranca.questionario.modelo.Secao;
import br.com.bb.seguranca.questionario.modelo.perguntas.Opcao;
import br.com.bb.seguranca.questionario.modelo.perguntas.Pergunta;
import br.com.bb.seguranca.questionario.modelo.perguntas.PerguntaMultOpcUmResp;
import br.com.bb.seguranca.questionario.modelo.perguntas.PerguntaSimNao;
import br.com.bb.seguranca.questionario.modelo.perguntas.PerguntaSimNaoNSeAplica;
import br.com.bb.seguranca.questionario.util.PerguntaUtil;

public class ProgramTest {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws ParseException {

		Questionario questionario = geraQuestionario();
		Secao secao1 = geraSecao(questionario, "Seção 01", 1L);
		Secao secao2 = geraSecao(questionario, "Secao 02", 2L);

//		System.out.println(secao1);
//		System.out.println(secao2);

		questionario.setSecoes(new ArrayList<Secao>());
		questionario.getSecoes().add(secao1);
		questionario.getSecoes().add(secao2);

//		System.out.println(questionario);

		PerguntaSimNao p1 = new PerguntaSimNao();
		PerguntaSimNaoNSeAplica p2 = new PerguntaSimNaoNSeAplica();
		PerguntaMultOpcUmResp p3 = new PerguntaMultOpcUmResp();
		PerguntaMultOpcUmResp p4 = new PerguntaMultOpcUmResp();

		p1.setIdPergunta(1L);
		p1.setSecao(secao1);
		p1.setTextoPergunta("Pergunta 01");
		p1.setPerguntaAtiva(1);
		p1.setResposta(1);

		p2.setIdPergunta(2L);
		p2.setSecao(secao2);
		p2.setTextoPergunta("Pergunta 02");
		p2.setPerguntaAtiva(0);
		p2.setResposta(0);

		List<Opcao> opcoes = geraOpcoes();

		p3.setIdPergunta(3L);
		p3.setSecao(secao2);
		p3.setTextoPergunta("Pergunta 03");
		p3.setPerguntaAtiva(1);
		p3.setResposta(opcoes.get(2));

		p4.setIdPergunta(4L);
		p4.setSecao(secao1);
		p4.setTextoPergunta("Pergunta 04");
		p4.setPerguntaAtiva(0);
		p4.setResposta(opcoes.get(0));

		List<Pergunta> pergSec1 = new ArrayList<>();
		pergSec1.add(p1);
		pergSec1.add(p4);

		List<Pergunta> pergSec2 = new ArrayList<>();

		pergSec2.add(p2);
		pergSec2.add(p3);

		secao1.setPerguntas(pergSec1);
		secao2.setPerguntas(pergSec2);

		for (Secao secao : questionario.getSecoes()) {

			for (Pergunta pergunta : secao.getPerguntas()) {

				if (PerguntaUtil.ifPerguntaMultOpcUmResp(pergunta)) {
					PerguntaMultOpcUmResp p = PerguntaUtil.getPerguntaMultOpcUmResp(pergunta);
					Opcao opcao = p.getResposta();
					System.out.println("Id: " + p.getIdPergunta() + " texto: " + p.getTextoPergunta() + " Resposta "
							+ opcao.getNomeOpcao() + " Tipo: " + p.getClass().getSimpleName());
				} else {
					
					System.out.println("Id: " + pergunta.getIdPergunta() + " texto: " + pergunta.getTextoPergunta() + " Resposta "
							+ pergunta.getResposta() + " Tipo: " + pergunta.getClass().getSimpleName());
				}

			}

		}

	}

	private static List<Opcao> geraOpcoes() {
		Opcao op1 = new Opcao();
		Opcao op2 = new Opcao();
		Opcao op3 = new Opcao();
		Opcao op4 = new Opcao();

		op1.setIdOpcao(1L);
		op1.setIndiceOpcao(3);
		op1.setNomeOpcao("Opcao 03");

		op2.setIdOpcao(2L);
		op2.setIndiceOpcao(2);
		op2.setNomeOpcao("Opcao 02");

		op3.setIdOpcao(3L);
		op3.setIndiceOpcao(1);
		op3.setNomeOpcao("Opcao 01");

		op4.setIdOpcao(4L);
		op4.setIndiceOpcao(4);
		op4.setNomeOpcao("Opcao 04");

		List<Opcao> opcoes = new ArrayList<Opcao>();

		opcoes.add(op1);
		opcoes.add(op2);
		opcoes.add(op3);
		opcoes.add(op4);
		return opcoes;
	}

	public static Secao geraSecao(Questionario questionario, String nomeSecao, Long idSecao) {
		Secao secao = new Secao();
		secao.setQuestionario(questionario);
		secao.setNomeSecao(nomeSecao);
		secao.setIdSecao(idSecao);

		return secao;
	}

	public static Questionario geraQuestionario() throws ParseException {
		Questionario questionario = new Questionario();
		questionario.setIdQuest(1L);
		questionario.setNomeQuest("AVS 01");
		questionario.setMatriculaGravacao("F0394519");
		questionario.setDataGravacao(SDF.parse("25/05/2022"));
		questionario.setQuestionatioAtivo(1);

		return questionario;
	}

}
