package br.com.bb.seguranca.questionario.modelo.perguntas;

import java.util.Collections;
import java.util.List;

/**
 * @author - Aldecir Neves de lima
 * 
 *         Utilizada para obter uma resposta entre diversas opções
 * 
 *         Respostas índice 0..n resposta
 * 
 *         Tipo pergunta = 3
 * 
 */

public class PerguntaMultOpcUmResp extends Pergunta {

	private Opcao resposta;

	List<Opcao> opcoes;

	public PerguntaMultOpcUmResp() {
		this.setTipoPergunta(3);
	}

	public List<Opcao> getOpcoes() {
		Collections.sort(opcoes);
		return opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getResposta() {
		// TODO Auto-generated method stub
		return (T) this.resposta;
	}

	@Override
	public <T> void setResposta(T resposta) {
		this.resposta = (Opcao) resposta;

	}

}
