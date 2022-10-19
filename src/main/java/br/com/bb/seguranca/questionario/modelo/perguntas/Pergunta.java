package br.com.bb.seguranca.questionario.modelo.perguntas;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import br.com.bb.seguranca.questionario.modelo.Resposta;

public class Pergunta {

//	Id

	private Long idPergunta;

//	Objects

	private Resposta resposta;

	private List<Opcao> opcoesParaSelecao;

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

//	Fim dos atributos

	public Pergunta() {

	}

	public Resposta getResposta() {
		return this.resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

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

	public List<Opcao> getOpcoesParaSelecao() {
		return opcoesParaSelecao;
	}

	public void setOpcoesParaSelecao(List<Opcao> opcoesParaSelecao) {
		this.opcoesParaSelecao = opcoesParaSelecao;
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
