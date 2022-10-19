package br.com.bb.seguranca.questionario.modelo;

import java.util.List;

import br.com.bb.seguranca.questionario.modelo.perguntas.Opcao;

public class Resposta {

//	Id

	private Long idResposta;

//	Objects

	private List<Opcao> opcoesSelecionadas;

	private Opcao opcaoUnicaSelecionada;

	public Resposta() {

	}

	public Long getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}

	public List<Opcao> getOpcoesSelecionadas() {
		return opcoesSelecionadas;
	}

	public void setOpcoesSelecionadas(List<Opcao> opcoesSelecionadas) {
		this.opcoesSelecionadas = opcoesSelecionadas;
	}

	public Opcao getOpcaoUnicaSelecionada() {
		return opcaoUnicaSelecionada;
	}

	public void setOpcaoUnicaSelecionada(Opcao opcaoUnicaSelecionada) {
		this.opcaoUnicaSelecionada = opcaoUnicaSelecionada;
	}

}
