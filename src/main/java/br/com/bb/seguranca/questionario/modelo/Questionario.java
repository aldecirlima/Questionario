package br.com.bb.seguranca.questionario.modelo;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTIONARIO")
public class Questionario {

//	Id

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idQuestionario;

//	Objetos

	@OneToMany(mappedBy = "questionario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Secao> secoes;

//	Strings

	@Column(name = "NM_QST", length = 50)
	private String nomeQuestionario;

	@Column(name = "MTR_GRV", length = 8)
	private String matriculaGravacao;
	
	@Column(name = "DSC_QST", length = 150)
	private String descricaoQuestionario;

	@Column(name = "MTR_ATV", length = 8)
	private String matriculaAtivacao;

	@Column(name = "MTR_ENCRR", length = 8)
	private String matriculaEncerramento;

//	Dates

	@Column(name = "DT_GRV")
	private Date dataGravacao;

	@Column(name = "DT_ATV")
	private Date dataAtivacao;

	@Column(name = "DT_ENCRR")
	private Date dataEncerramento;

//	Integers

	@Column(name = "QST_ATV", length = 1)
	private Integer questionarioAtivo; // Inativo=0, ativo=1, baixado=3

//	Fim dos atributos

	public Questionario() {

	}

	public String getMatriculaGravacao() {
		return matriculaGravacao;
	}

	public void setMatriculaGravacao(String matriculaGravacao) {
		this.matriculaGravacao = matriculaGravacao;
	}

	public String getMatriculaAtivacao() {
		return matriculaAtivacao;
	}

	public void setMatriculaAtivacao(String matriculaAtivacao) {
		this.matriculaAtivacao = matriculaAtivacao;
	}

	public String getMatriculaEncerramento() {
		return matriculaEncerramento;
	}

	public void setMatriculaEncerramento(String matriculaEncerramento) {
		this.matriculaEncerramento = matriculaEncerramento;
	}

	public Date getDataGravacao() {
		return dataGravacao;
	}

	public void setDataGravacao(Date dataGravacao) {
		this.dataGravacao = dataGravacao;
	}

	public Date getDataAtivacao() {
		return dataAtivacao;
	}

	public void setDataAtivacao(Date dataAtivacao) {
		this.dataAtivacao = dataAtivacao;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public List<Secao> getSecoes() {
		return secoes;
	}

	public void setSecoes(List<Secao> secoes) {
		this.secoes = secoes;
	}

	/**
	 * Status: Inativo=0; Ativo=1; Baixado=3
	 *  
	 * @return {@code Integer}
	 */
	public Integer getQuestionarioAtivo() {
		return questionarioAtivo;
	}

	/**
	 * Status: Inativo=0; Ativo=1; Baixado=3
	 *  
	 */
	public void setQuestionarioAtivo(Integer questionatioAtivo) {
		this.questionarioAtivo = questionatioAtivo;
	}

	public Long getIdQuestionario() {
		return idQuestionario;
	}

	public String getNomeQuestionario() {
		return nomeQuestionario;
	}

	public void setIdQuestionario(Long idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	public void setNomeQuestionario(String nomeQuestionario) {
		this.nomeQuestionario = nomeQuestionario;
	}
	
	

	public String getDescricaoQuestionario() {
		return descricaoQuestionario;
	}

	public void setDescricaoQuestionario(String descricaoQuestionario) {
		this.descricaoQuestionario = descricaoQuestionario;
	}

	@Override
	public String toString() {
		return "Id: " + idQuestionario + " - Titulo: " + nomeQuestionario ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idQuestionario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questionario other = (Questionario) obj;
		return Objects.equals(idQuestionario, other.idQuestionario);
	}

}
