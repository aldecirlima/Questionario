package br.com.bb.seguranca.questionario.modelo.perguntas;

import java.util.Date;
import java.util.Objects;

import br.com.bb.seguranca.questionario.modelo.Secao;

public abstract class Pergunta {

//	Longs

	private Long idPergunta;

//	Strings

	private String textoPergunta;

	private String matriculaCadastro;

	private String matriculaExclusao;

//	Dates

	private Date dataCadastro;

	private Date dataExclusao;

//	Integers
//	0=não 1=sim
	private Integer perguntaAtiva;

	private Integer tipoPergunta;

//	Objects

	private Secao secao;

//	Fim dos atributos

	public Pergunta() {

	}

	public abstract <T> T getResposta();

	public abstract <T> void setResposta(T resposta);

	public Long getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(Long idPergunta) {
		this.idPergunta = idPergunta;
	}

	public String getTextoPergunta() {
		return textoPergunta;
	}

	public void setTextoPergunta(String textoPergunta) {
		this.textoPergunta = textoPergunta;
	}

	public String getMatriculaCadastro() {
		return matriculaCadastro;
	}

	public void setMatriculaCadastro(String matriculaCadastro) {
		this.matriculaCadastro = matriculaCadastro;
	}

	public String getMatriculaExclusao() {
		return matriculaExclusao;
	}

	public void setMatriculaExclusao(String matriculaExclusao) {
		this.matriculaExclusao = matriculaExclusao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Integer getPerguntaAtiva() {
		return perguntaAtiva;
	}

	public void setPerguntaAtiva(Integer perguntaAtiva) {
		this.perguntaAtiva = perguntaAtiva;
	}

	public Integer getTipoPergunta() {
		return tipoPergunta;
	}

	public void setTipoPergunta(Integer tipoPergunta) {
		this.tipoPergunta = tipoPergunta;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPergunta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pergunta other = (Pergunta) obj;
		return Objects.equals(idPergunta, other.idPergunta);
	}

}
