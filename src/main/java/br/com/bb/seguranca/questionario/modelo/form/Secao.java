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

import br.com.bb.seguranca.questionario.modelo.base.SecaoBase;

@Entity
@Table(name = "SECAO")
public class Secao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idSecao;

	@ManyToOne()
	@JoinColumn(name = "AVL_ID")
	private Avaliacao avaliacao;

	@OneToOne
	@JoinColumn(name = "SC_ID")
	private SecaoBase secao;
	
	@OneToMany(mappedBy = "secao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pergunta> perguntas;

	public Long getIdSecao() {
		return idSecao;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setIdSecao(Long idSecao) {
		this.idSecao = idSecao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public SecaoBase getSecao() {
		return secao;
	}

	public void setSecao(SecaoBase secao) {
		this.secao = secao;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntasForm(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
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
