package br.com.caelum.auron.modelo;

import java.util.Collections;
import java.util.List;

public class Sorteador {

	private Sorteio sorteio;
	private List<Participante> participantes;
	private Integer totalDeParticipantes;

	public Sorteador(Sorteio sorteio, List<Participante> participantes) throws SorteioException {
		if (participantes == null) {
			throw new SorteioException("Por favor insira uma lista de participantes!");
		}

		this.sorteio = sorteio;
		this.participantes = participantes;
		this.totalDeParticipantes = participantes.size();
	}

	public void sortear() throws SorteioException {

		verificaTamanhoDaListaDeParticipantes();
		embaralhaParticipantes();

		for (Integer indiceAtual = 0; indiceAtual < totalDeParticipantes; indiceAtual++) {
			if (participanteAtualEhOUltimo(indiceAtual)) {
				criaEAdicionaOParNoSorteio(sorteio, indiceAtual, 0);
				break;
			}
			criaEAdicionaOParNoSorteio(sorteio, indiceAtual, indiceAtual + 1);
		}

	}

	private void embaralhaParticipantes() {
		Collections.shuffle(participantes);
	}

	private void verificaTamanhoDaListaDeParticipantes() throws SorteioException {
		if (totalDeParticipantes < 2) {
			throw new SorteioException(
					"Por favor insira uma lista de participantes com no mínimo " + "dois participantes");
		}
	}

	private boolean participanteAtualEhOUltimo(Integer indiceAtual) {
		return indiceAtual == totalDeParticipantes - 1;
	}

	private void criaEAdicionaOParNoSorteio(Sorteio sorteio, Integer indiceAtual, Integer indiceFinal) {
		Par par = new Par(participantes.get(indiceAtual), participantes.get(indiceFinal), sorteio);
		sorteio.adicionaPar(par);
	}

}
