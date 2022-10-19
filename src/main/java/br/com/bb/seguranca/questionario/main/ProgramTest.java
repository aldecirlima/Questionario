package br.com.bb.seguranca.questionario.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.bb.seguranca.questionario.modelo.Questionario;
import br.com.bb.seguranca.questionario.modelo.Secao;
import br.com.bb.seguranca.questionario.modelo.perguntas.Opcao;
import br.com.bb.seguranca.questionario.modelo.perguntas.Pergunta;

public class ProgramTest {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws ParseException {

		Questionario questionario = geraQuestionario();
		Secao secao1 = geraSecao("Seção 01", 1L);
		Secao secao2 = geraSecao("Secao 02", 2L);

		secao1.setPerguntas(geraPerguntas(3));
		secao2.setPerguntas(geraPerguntas(4));

		List<Secao> secoes = new ArrayList<>();
		secoes.add(secao1);
		secoes.add(secao2);

		questionario.setSecoes(secoes);
		
		System.out.println("Questionário: " + questionario.getNomeQuest());
		
		for (Secao secao : questionario.getSecoes()) {
			
			System.out.println("\nSeção: " + secao.getNomeSecao());
			
			for (Pergunta pergunta : secao.getPerguntas()) {
				System.out.println("\n  Pergunta ID: " + pergunta.getIdPergunta() + " Texto: " + pergunta.getTextoPergunta());
				System.out.println("    Opçoes pergunta: " + pergunta.getIdPergunta());
				for (Opcao opcao : pergunta.getOpcoesParaSelecao()) {
					System.out.println("      Indice: " + opcao.getIndiceOpcao() + " Título: " + opcao.getNomeOpcao());
				}
			}
			
		}

//		

	}

	public static List<Pergunta> geraPerguntas(Integer quant) {
		List<Pergunta> perguntas = new ArrayList<>();

		for (int i = 1; i <= quant; i++) {
			Pergunta p = new Pergunta();
			p.setIdPergunta(Long.parseLong("" + i));
			p.setTextoPergunta("Texto exemplo " + i);
			p.setOpcoesParaSelecao(geraOpcoes(i + 1));
			perguntas.add(p);

		}

		return perguntas;
	}

	public static List<Opcao> geraOpcoes(Integer quant) {

		List<Opcao> opcoes = new ArrayList<>();

		for (int i = 1; i <= quant; i++) {
			Opcao op = new Opcao();
			op.setIdOpcao(Long.parseLong("" + i));
			op.setIndiceOpcao(i);
			op.setNomeOpcao("Opcao " + i);
			opcoes.add(op);
		}

		return opcoes;
	}

	public static Secao geraSecao(String nomeSecao, Long idSecao) {
		Secao secao = new Secao();
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
