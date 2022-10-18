package br.com.bb.seguranca.questionario.modelo.perguntas;

/**
 * @author - Aldecir Neves de lima
 * 
 *         Utilizada para obter respostas sim, não e não se aplica
 * 
 *         Respostas não=0, sim=1, não se aplica=3
 * 
 *         Tipo pergunta = 2
 * 
 */

public class PerguntaSimNaoNSeAplica extends Pergunta {

//  Respostas não=0, sim=1, não se aplica=3
	private Integer resposta;

	public PerguntaSimNaoNSeAplica() {
		this.setTipoPergunta(2);
	}

	@Override
	public String toString() {
		return "PerguntaSimNaoNSeAplica [respostaSimNaoNaoSeAplica=" + resposta + ", getIdPergunta()=" + getIdPergunta()
				+ ", getTextoPergunta()=" + getTextoPergunta() + ", getSecao()=" + getSecao().getNomeSecao() + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getResposta() {
		return (T) this.resposta;
	}

	@Override
	public <T> void setResposta(T resposta) {
		this.resposta = (Integer) resposta;
	}

}
