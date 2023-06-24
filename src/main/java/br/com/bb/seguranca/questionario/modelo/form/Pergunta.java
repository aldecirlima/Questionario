package br.com.bb.seguranca.questionario.modelo.form;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.bb.seguranca.questionario.modelo.base.PerguntaBase;

@Entity
@Table(name = "PERGUNTA")
public class Pergunta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idPergunta;

	@ManyToOne
	@JoinColumn(name = "SC_FRM_ID")
	private Secao secao;
	
	@OneToOne
	@JoinColumn(name = "PRGT_ID")
	private PerguntaBase pergunta;
	
	@ManyToOne
	@JoinColumn(name = "PRGT_M_ID")
	private Pergunta perguntaMae;
	
	@OneToMany(mappedBy = "perguntaMae", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pergunta> subPerguntas;


	public Long getIdPergunta() {
		return idPergunta;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setIdPergunta(Long idPergunta) {
		this.idPergunta = idPergunta;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public PerguntaBase getPergunta() {
		return pergunta;
	}

	public void setPergunta(PerguntaBase pergunta) {
		this.pergunta = pergunta;
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

}
