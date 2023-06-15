package br.com.bb.seguranca.questionario.modelo.perguntas;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.bb.seguranca.questionario.modelo.Secao;

@Entity
@Table(name = "PERGUNTA")
public class Pergunta implements Comparable<Pergunta> {

//	Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idPergunta;

//	Objects

	@ManyToOne
	@JoinColumn(name = "SC_ID")
	private Secao secao;

	@ManyToOne
	@JoinColumn(name = "PRGT_M_ID")
	private Pergunta perguntaMae;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "PERGUNTA_OPCAO", joinColumns = @JoinColumn(name = "PRGT_ID"), inverseJoinColumns = @JoinColumn(name = "OPC_ID"))
	private List<Opcao> opcoesParaSelecao;

	@OneToMany(mappedBy = "perguntaMae", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Pergunta> subPerguntas;

//	Strings

	@Column(name = "TXT_PRGT")
	private String textoPergunta;

	@Column(name = "MTR_CDST")
	private String matriculaCadastro;

	@Column(name = "MTR_EXCL")
	private String matriculaExclusao;

	@Column(name = "TTL")
	private String title;

//	Dates

	@Column(name = "DT_CDST")
	private Date dataCadastro;

	@Column(name = "DT_EXCL")
	private Date dataExclusao;

//	Integers
//	0=não 1=sim
	@Column(name = "PRGT_ATV")
	private Integer perguntaAtiva;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 30, name = "TP_PRGT")
	private TipoPergunta tipoPergunta;

	@Column(name = "ORD")
	private Integer ordem;

//	Fim dos atributos

	public Pergunta() {

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

	public List<Opcao> getOpcoesParaSelecao() {
		return opcoesParaSelecao;
	}

	public void setOpcoesParaSelecao(List<Opcao> opcoesParaSelecao) {
		this.opcoesParaSelecao = opcoesParaSelecao;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public String getTitle() {
		return title;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public TipoPergunta getTipoPergunta() {
		return tipoPergunta;
	}

	public void setTipoPergunta(TipoPergunta tipoPergunta) {
		this.tipoPergunta = tipoPergunta;
	}

	public Pergunta getPerguntaMae() {
		return perguntaMae;
	}

	public List<Pergunta> getSubPerguntas() {
		return subPerguntas;
	}

	public void setPerguntaMae(Pergunta perguntaMae) {
		this.perguntaMae = perguntaMae;
	}

	public void setSubPerguntas(List<Pergunta> subPerguntas) {
		this.subPerguntas = subPerguntas;
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

	@Override
	public int compareTo(Pergunta pergunta) {
		if (this.idPergunta < pergunta.idPergunta) {
			return -1;
		}
		if (this.idPergunta > pergunta.idPergunta) {
			return 1;
		}
		return 0;
	}

}
