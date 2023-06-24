package br.com.bb.seguranca.questionario.modelo.base;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SECAO_BASE")
public class SecaoBase {

//	Id
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSecaoBase;

//	Objetos

	@ManyToOne()
	@JoinColumn(name = "QST_BS_ID")
	private QuestionarioBase questionarioBase;

	@OneToMany(mappedBy = "secaoBase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PerguntaBase> perguntas;

//	Strings

	@Column(name = "NM_SC", length = 50)
	private String nomeSecao;

	// Alterar para descrição secao
	@Column(name = "DSC_SC")
	private String descricaoSecao;

	@Column(name = "MTR_GRV", length = 8)
	private String matriculaGravacao;

	@Column(name = "MTR_EXCL", length = 8)
	private String matriculaExclusao;

//	Dates

	@Column(name = "DT_GRV")
	private Date dataGravacao;

	@Column(name = "DT_EXCL")
	private Date dataExclusao;

//	Integers

	@Column(name = "SC_ATV", length = 1)
	private Integer secaoAtiva; // Inativo=0, ativo=1
	
	@Column(name = "ORD_SC", length = 9)
	private Integer ordemSecao; // ordem da seção no questionário

//	Fim dos atributos

	public SecaoBase() {
	}

	public Long getIdSecaoBase() {
		return idSecaoBase;
	}

	public void setIdSecaoBase(Long idSecaoBase) {
		this.idSecaoBase = idSecaoBase;
	}

	public String getNomeSecao() {
		return nomeSecao;
	}

	public void setNomeSecao(String nomeSecao) {
		this.nomeSecao = nomeSecao;
	}

	public String getDescricaoSecao() {
		return descricaoSecao;
	}

	public void setDescricaoSecao(String descricaoSecao) {
		this.descricaoSecao = descricaoSecao;
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

	public Integer getSecaoAtiva() {
		return secaoAtiva;
	}

	public void setSecaoAtiva(Integer secaoAtiva) {
		this.secaoAtiva = secaoAtiva;
	}

	public Integer getOrdemSecao() {
		return ordemSecao;
	}

	public void setOrdemSecao(Integer ordemSecao) {
		this.ordemSecao = ordemSecao;
	}

	public List<PerguntaBase> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<PerguntaBase> perguntas) {
		this.perguntas = perguntas;
	}

	public QuestionarioBase getQuestionario() {
		return questionarioBase;
	}

	public void setQuestionario(QuestionarioBase questionarioBase) {
		this.questionarioBase = questionarioBase;
	}

	@Override
	public String toString() {
		return "SecaoBase [idSecao=" + idSecaoBase + ", nomeSecao=" + nomeSecao + ", matriculaGravacao=" + matriculaGravacao
				+ ", secaoAtiva=" + secaoAtiva + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSecaoBase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecaoBase other = (SecaoBase) obj;
		return Objects.equals(idSecaoBase, other.idSecaoBase);
	}

}
