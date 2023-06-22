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

import br.com.bb.seguranca.questionario.modelo.base.Secao;

@Entity
@Table(name = "SECAO_FORM")
public class SecaoForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idSecaoForm;

	@ManyToOne()
	@JoinColumn(name = "AVL_ID")
	private Avaliacao avaliacao;

	@OneToOne
	@JoinColumn(name = "SC_ID")
	private Secao secao;
	
	@OneToMany(mappedBy = "secaoForm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PerguntaForm> perguntasForm;

	public Long getIdSecaoForm() {
		return idSecaoForm;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setIdSecaoForm(Long idSecaoForm) {
		this.idSecaoForm = idSecaoForm;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public List<PerguntaForm> getPerguntasForm() {
		return perguntasForm;
	}

	public void setPerguntasForm(List<PerguntaForm> perguntasForm) {
		this.perguntasForm = perguntasForm;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSecaoForm);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecaoForm other = (SecaoForm) obj;
		return Objects.equals(idSecaoForm, other.idSecaoForm);
	}

}
