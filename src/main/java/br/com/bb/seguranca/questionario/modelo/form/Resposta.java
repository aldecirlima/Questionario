package br.com.bb.seguranca.questionario.modelo.form;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.bb.seguranca.questionario.modelo.base.PerguntaBase;

@Entity
@Table(name = "RESPOSTA")
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idResposta;

//	Objects

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRGT_ID")
	private PerguntaBase pergunta;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "RESPOSTA_OPCAO", joinColumns = @JoinColumn(name = "RSP_ID"), inverseJoinColumns = @JoinColumn(name = "OPC_ID"))
	private Set<Opcao> opcoesSelecionadas;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "OPC_SEL_ID")
	private Opcao opcaoUnicaSelecionada;

	@Column(name = "RSP_TXT_CRT", length = 250)
	private String respostaTextoCurto;

	@Column(name = "RSP_TXT_LNG", length = 1000)
	private String respostaTextoLongo;

	public Resposta() {

	}

	public Long getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}

	public Set<Opcao> getOpcoesSelecionadas() {
		return opcoesSelecionadas;
	}

	public void setOpcoesSelecionadas(Set<Opcao> opcoesSelecionadas) {
		this.opcoesSelecionadas = opcoesSelecionadas;
	}

	public Opcao getOpcaoUnicaSelecionada() {
		return opcaoUnicaSelecionada;
	}

	public void setOpcaoUnicaSelecionada(Opcao opcaoUnicaSelecionada) {
		this.opcaoUnicaSelecionada = opcaoUnicaSelecionada;
	}

	public PerguntaBase getPergunta() {
		return pergunta;
	}

	public String getRespostaTextoCurto() {
		return respostaTextoCurto;
	}

	public String getRespostaTextoLongo() {
		return respostaTextoLongo;
	}

	public void setPergunta(PerguntaBase pergunta) {
		this.pergunta = pergunta;
	}

	public void setRespostaTextoCurto(String respostaTextoCurto) {
		this.respostaTextoCurto = respostaTextoCurto;
	}

	public void setRespostaTextoLongo(String respostaTextoLongo) {
		this.respostaTextoLongo = respostaTextoLongo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idResposta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resposta other = (Resposta) obj;
		return Objects.equals(idResposta, other.idResposta);
	}

}
