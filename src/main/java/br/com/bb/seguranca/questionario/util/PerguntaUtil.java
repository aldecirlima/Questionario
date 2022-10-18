package br.com.bb.seguranca.questionario.util;

import br.com.bb.seguranca.questionario.modelo.perguntas.Pergunta;
import br.com.bb.seguranca.questionario.modelo.perguntas.PerguntaMultOpcUmResp;
import br.com.bb.seguranca.questionario.modelo.perguntas.PerguntaSimNao;
import br.com.bb.seguranca.questionario.modelo.perguntas.PerguntaSimNaoNSeAplica;

public class PerguntaUtil {

	public static PerguntaSimNao getPerguntaSimNao(Pergunta pergunta) {
		if (PerguntaUtil.ifPerguntaSimNao(pergunta)) {
			return (PerguntaSimNao) pergunta;
		}
		return null;
	}

	public static PerguntaSimNaoNSeAplica getPerguntaSimNaoNSeAplica(Pergunta pergunta) {
		if (PerguntaUtil.ifPerguntaSimNaoNSeAplica(pergunta)) {
			return (PerguntaSimNaoNSeAplica) pergunta;
		}
		return null;
	}

	public static PerguntaMultOpcUmResp getPerguntaMultOpcUmResp(Pergunta pergunta) {
		if (PerguntaUtil.ifPerguntaMultOpcUmResp(pergunta)) {
			return (PerguntaMultOpcUmResp) pergunta;
		}
		return null;
	}

	public static Boolean ifPerguntaSimNao(Pergunta pergunta) {

		return pergunta instanceof PerguntaSimNao;

	}

	public static Boolean ifPerguntaSimNaoNSeAplica(Pergunta pergunta) {

		return pergunta instanceof PerguntaSimNaoNSeAplica;

	}

	public static Boolean ifPerguntaMultOpcUmResp(Pergunta pergunta) {

		return pergunta instanceof PerguntaMultOpcUmResp;

	}

}
