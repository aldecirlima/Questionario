package br.com.bb.seguranca.questionario.modelo.base;

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

import br.com.bb.seguranca.questionario.modelo.enuns.TipoPergunta;
import br.com.bb.seguranca.questionario.modelo.form.Opcao;

@Entity
@Table(name = "PERGUNTA_BASE")
public class PerguntaBase implements Comparable<PerguntaBase> {

//	Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idPerguntaBase;

//	Objects

	@ManyToOne
	@JoinColumn(name = "SC_ID")
	private SecaoBase secaoBase;

	@ManyToOne
	@JoinColumn(name = "PRGT_M_ID")
	private PerguntaBase perguntaMae;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "PERGUNTA_OPCAO", joinColumns = @JoinColumn(name = "PRGT_ID"), inverseJoinColumns = @JoinColumn(name = "OPC_ID"))
	private List<Opcao> opcoesParaSelecao;

	@OneToMany(mappedBy = "perguntaMae", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PerguntaBase> subPerguntas;

//	Strings

	@Column(name = "TXT_PRGT", length = 250)
	private String textoPergunta;

	@Column(name = "MTR_CDST", length = 8)
	private String matriculaCadastro;

	@Column(name = "MTR_EXCL", length = 8)
	private String matriculaExclusao;

	@Column(name = "TTL", length = 155)
	private String title;

//	Dates

	@Column(name = "DT_CDST")
	private Date dataCadastro;

	@Column(name = "DT_EXCL")
	private Date dataExclusao;

//	Integers
//	0=não 1=sim
	@Column(name = "PRGT_ATV", length = 1)
	private Integer perguntaAtiva;

	@Column(name = "PRGT_VSVL", length = 1)
	private Integer perguntaVisivel;


	@Column(name = "OPC_VSVL_ID")
	private Long opcaoVisivel;

	@Column(name = "ORD", length = 9)
	private Integer ordem;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 30, name = "TP_PRGT")
	private TipoPergunta tipoPergunta;

//	Fim dos atributos

	public PerguntaBase() {

	}

	public Long getIdPerguntaBase() {
		return idPerguntaBase;
	}

	public void setIdPerguntaBase(Long idPerguntaBase) {
		this.idPerguntaBase = idPerguntaBase;
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

	public SecaoBase getSecaoBase() {
		return secaoBase;
	}

	public void setSecaoBase(SecaoBase secaoBase) {
		this.secaoBase = secaoBase;
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

	public PerguntaBase getPerguntaMae() {
		return perguntaMae;
	}

	public List<PerguntaBase> getSubPerguntas() {
		return subPerguntas;
	}

	public Integer getPerguntaVisivel() {
		return perguntaVisivel;
	}

	public Long getOpcaoVisivel() {
		return opcaoVisivel;
	}

	public void setPerguntaVisivel(Integer perguntaVisivel) {
		this.perguntaVisivel = perguntaVisivel;
	}

	public void setOpcaoVisivel(Long opcaoVisivel) {
		this.opcaoVisivel = opcaoVisivel;
	}

	public void setPerguntaMae(PerguntaBase perguntaMae) {
		this.perguntaMae = perguntaMae;
	}

	public void setSubPerguntas(List<PerguntaBase> subPerguntas) {
		this.subPerguntas = subPerguntas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPerguntaBase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerguntaBase other = (PerguntaBase) obj;
		return Objects.equals(idPerguntaBase, other.idPerguntaBase);
	}

	@Override
	public int compareTo(PerguntaBase pergunta) {
		if (this.idPerguntaBase < pergunta.idPerguntaBase) {
			return -1;
		}
		if (this.idPerguntaBase > pergunta.idPerguntaBase) {
			return 1;
		}
		return 0;
	}

}
