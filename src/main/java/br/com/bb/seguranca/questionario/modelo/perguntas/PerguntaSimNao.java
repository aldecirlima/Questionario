package br.com.bb.seguranca.questionario.modelo.perguntas;

/**
 * @author - Aldecir Neves de lima
 * 
 *         Utilizada para obter respostas sim ou não
 * 
 *         Respostas não=0, sim=1
 * 
 *         Tipo pergunta = 1
 * 
 */

public class PerguntaSimNao extends Pergunta {

	public Integer resposta;

	public PerguntaSimNao() {
		this.setTipoPergunta(1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getResposta() {
		// TODO Auto-generated method stub
		return (T) this.resposta;
	}

	@Override
	public <T> void setResposta(T resposta) {
		this.resposta = (Integer) resposta;
	}

	@Override
	public String toString() {
		return "PerguntaSimNao [respostaSimNao=" + resposta + ", getIdPergunta()=" + getIdPergunta()
				+ ", getTextoPergunta()=" + getTextoPergunta() + ", getSecao()=" + getSecao().getNomeSecao() + "]";
	}



	

}
