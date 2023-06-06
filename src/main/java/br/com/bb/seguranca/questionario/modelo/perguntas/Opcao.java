package br.com.bb.seguranca.questionario.modelo.perguntas;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPCAO")
public class Opcao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long idOpcao;

	@Column(name = "NM_OPC")
	private String nomeOpcao;

	public Opcao() {

	}

	public Long getIdOpcao() {
		return idOpcao;
	}

	public String getNomeOpcao() {
		return nomeOpcao;
	}

	public void setIdOpcao(Long idOpcao) {
		this.idOpcao = idOpcao;
	}

	public void setNomeOpcao(String nomeOpcao) {
		this.nomeOpcao = nomeOpcao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idOpcao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opcao other = (Opcao) obj;
		return Objects.equals(idOpcao, other.idOpcao);
	}

}
