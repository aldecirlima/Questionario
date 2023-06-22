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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.bb.seguranca.questionario.modelo.base.Questionario;

@Entity
@Table(name = "AVALIACAO")
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idAvaliacao;

	@OneToOne
	@JoinColumn(name = "QST_ID")
	private Questionario questionario;

	@OneToMany(mappedBy = "avaliacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SecaoForm> secoesForm;

	public Long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public List<SecaoForm> getSecoesForm() {
		return secoesForm;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public void setSecoesForm(List<SecaoForm> secoesForm) {
		this.secoesForm = secoesForm;
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
