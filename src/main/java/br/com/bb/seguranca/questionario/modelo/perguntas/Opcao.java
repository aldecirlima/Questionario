package br.com.bb.seguranca.questionario.modelo.perguntas;

import java.util.Objects;

public class Opcao implements Comparable<Opcao> {

	private Long idOpcao;

	private String nomeOpcao;

	private Integer indiceOpcao;

	private Pergunta pergunta;

	public Opcao() {

	}

	public Long getIdOpcao() {
		return idOpcao;
	}

	public String getNomeOpcao() {
		return nomeOpcao;
	}

	public Integer getIndiceOpcao() {
		return indiceOpcao;
	}

	public void setIdOpcao(Long idOpcao) {
		this.idOpcao = idOpcao;
	}

	public void setNomeOpcao(String nomeOpcao) {
		this.nomeOpcao = nomeOpcao;
	}

	public void setIndiceOpcao(Integer indiceOpcao) {
		this.indiceOpcao = indiceOpcao;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	@Override
	public String toString() {
		return "Opcao [idOpcao=" + idOpcao + ", nomeOpcao=" + nomeOpcao + ", indiceOpcao=" + indiceOpcao + "]";
	}

	@Override
	public int compareTo(Opcao opcao) {
		if (this.getIndiceOpcao() > opcao.getIndiceOpcao()) {
			return 1;
		} else if (this.getIndiceOpcao() < opcao.getIndiceOpcao()) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(idOpcao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opcao other = (Opcao) obj;
		return Objects.equals(idOpcao, other.idOpcao);
	}

}
