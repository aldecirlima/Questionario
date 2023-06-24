package br.com.bb.seguranca.questionario.modelo.form;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.bb.seguranca.questionario.modelo.base.QuestionarioBase;

@Entity
@Table(name = "AVALIACAO")
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idAvaliacao;

	@OneToOne
	@JoinColumn(name = "QST_ID")
	private QuestionarioBase questionarioBase;

	@OneToMany(mappedBy = "avaliacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Secao> secoes;

	@Column(name = "DT_AVL")
	private Date dataAvaliacao;

	@Column(name = "DT_DSPCH")
	private Date dataDespacho;

	@Column(name = "DT_EXCL")
	private Date dataExclusao;

	@Column(name = "MTRC_AVL", length = 8)
	private String matriculaAvaliacao;

	@Column(name = "MTRC_DSPCH", length = 8)
	private String matriculaDespacho;

	@Column(name = "MTRC_EXCL", length = 8)
	private String matriculaExclusao;
	
	@Column(name = "DSPCHD", length = 1)
	private Integer despacho; // 1 - sim, 0 - Não

	public Long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public QuestionarioBase getQuestionarioBase() {
		return questionarioBase;
	}

	public List<Secao> getSecoes() {
		return secoes;
	}

	public void setQuestionarioBase(QuestionarioBase questionarioBase) {
		this.questionarioBase = questionarioBase;
	}

	public void setSecoes(List<Secao> secoes) {
		this.secoes = secoes;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public Date getDataDespacho() {
		return dataDespacho;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public String getMatriculaAvaliacao() {
		return matriculaAvaliacao;
	}

	public String getMatriculaDespacho() {
		return matriculaDespacho;
	}

	public String getMatriculaExclusao() {
		return matriculaExclusao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public void setDataDespacho(Date dataDespacho) {
		this.dataDespacho = dataDespacho;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public void setMatriculaAvaliacao(String matriculaAvaliacao) {
		this.matriculaAvaliacao = matriculaAvaliacao;
	}

	public void setMatriculaDespacho(String matriculaDespacho) {
		this.matriculaDespacho = matriculaDespacho;
	}

	public void setMatriculaExclusao(String matriculaExclusao) {
		this.matriculaExclusao = matriculaExclusao;
	}

	public Integer getDespacho() {
		return despacho;
	}

	public void setDespacho(Integer despacho) {
		this.despacho = despacho;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAvaliacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
		return Objects.equals(idAvaliacao, other.idAvaliacao);
	}

}
