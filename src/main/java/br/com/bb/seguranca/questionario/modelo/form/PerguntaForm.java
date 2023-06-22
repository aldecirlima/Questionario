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

import br.com.bb.seguranca.questionario.modelo.base.perguntas.Pergunta;

@Entity
@Table(name = "PERGUNTA_FORM")
public class PerguntaForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idPerguntaForm;

	@ManyToOne
	@JoinColumn(name = "SC_FRM_ID")
	private SecaoForm secaoForm;
	
	@OneToOne
	@JoinColumn(name = "PRGT_ID")
	private Pergunta pergunta;
	
	@ManyToOne
	@JoinColumn(name = "PRGT_M_FRM_ID")
	private PerguntaForm perguntaMaeForm;
	
	@OneToMany(mappedBy = "perguntaMaeForm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PerguntaForm> subPerguntasForm;


	public Long getIdPerguntaForm() {
		return idPerguntaForm;
	}

	public SecaoForm getSecaoForm() {
		return secaoForm;
	}

	public void setIdPerguntaForm(Long idPerguntaForm) {
		this.idPerguntaForm = idPerguntaForm;
	}

	public void setSecaoForm(SecaoForm secaoForm) {
		this.secaoForm = secaoForm;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public PerguntaForm getPerguntaMaeForm() {
		return perguntaMaeForm;
	}

	public List<PerguntaForm> getSubPerguntasForm() {
		return subPerguntasForm;
	}

	public void setPerguntaMaeForm(PerguntaForm perguntaMaeForm) {
		this.perguntaMaeForm = perguntaMaeForm;
	}

	public void setSubPerguntasForm(List<PerguntaForm> subPerguntasForm) {
		this.subPerguntasForm = subPerguntasForm;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPerguntaForm);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerguntaForm other = (PerguntaForm) obj;
		return Objects.equals(idPerguntaForm, other.idPerguntaForm);
	}

}
