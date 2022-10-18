package br.com.bb.seguranca.questionario.modelo;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import br.com.bb.seguranca.questionario.modelo.perguntas.Pergunta;

public class Secao {

//	Longs

	private Long idSecao;

//	Strings

	private String nomeSecao;

	private String instrucoesSecao;

	private String matriculaGravacao;

	private String matriculaExclusao;

//	Dates

	private Date dataGravacao;

	private Date dataExclusao;

//	Integers

	private Integer secaoAtiva; // Inativo=0, ativo=1

//	Objetos

	private Questionario questionario;

	private List<Pergunta> perguntas;

//	Fim dos atributos

	public Secao() {
	}

	public Long getIdSecao() {
		return idSecao;
	}

	public void setIdSecao(Long idSecao) {
		this.idSecao = idSecao;
	}

	public String getNomeSecao() {
		return nomeSecao;
	}

	public void setNomeSecao(String nomeSecao) {
		this.nomeSecao = nomeSecao;
	}

	public String getInstrucoesSecao() {
		return instrucoesSecao;
	}

	public void setInstrucoesSecao(String instrucoesSecao) {
		this.instrucoesSecao = instrucoesSecao;
	}

	public String getMatriculaGravacao() {
		return matriculaGravacao;
	}

	public void setMatriculaGravacao(String matriculaGravacao) {
		this.matriculaGravacao = matriculaGravacao;
	}

	public String getMatriculaExclusao() {
		return matriculaExclusao;
	}

	public void setMatriculaExclusao(String matriculaExclusao) {
		this.matriculaExclusao = matriculaExclusao;
	}

	public Date getDataGravacao() {
		return dataGravacao;
	}

	public void setDataGravacao(Date dataGravacao) {
		this.dataGravacao = dataGravacao;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public Integer getSecaoAtiva() {
		return secaoAtiva;
	}

	public void setSecaoAtiva(Integer secaoAtiva) {
		this.secaoAtiva = secaoAtiva;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	@Override
	public String toString() {
		return "Secao [idSecao=" + idSecao + ", nomeSecao=" + nomeSecao + ", questionarioId="
				+ questionario.getIdQuest() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSecao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Secao other = (Secao) obj;
		return Objects.equals(idSecao, other.idSecao);
	}

}
