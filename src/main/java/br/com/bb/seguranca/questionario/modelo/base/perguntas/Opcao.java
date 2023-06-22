package br.com.bb.seguranca.questionario.modelo.base.perguntas;

import java.util.Date;
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

	@Column(name = "NM_OPC", length = 80)
	private String nomeOpcao;

	@Column(name = "DT_CDSTR")
	private Date dataCadastro;

	@Column(name = "MTR_CDSTR", length = 8)
	private String matriculaCadastro;

	@Column(name = "ATV", length = 1)
	private Integer ativa; // 0 inativa 1 ativa

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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public String getMatriculaCadastro() {
		return matriculaCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setMatriculaCadastro(String matriculaCadastro) {
		this.matriculaCadastro = matriculaCadastro;
	}

	public Integer getAtiva() {
		return ativa;
	}

	public void setAtiva(Integer ativa) {
		this.ativa = ativa;
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
