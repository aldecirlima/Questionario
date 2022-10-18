package br.com.caelum.auron.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Par {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Participante amigo;

	@ManyToOne
	private Participante amigoOculto;

	@ManyToOne
	private Sorteio sorteio;

	public Par(Participante amigo, Participante amigoOculto, Sorteio sorteio) {
		this.amigo = amigo;
		this.amigoOculto = amigoOculto;
		this.sorteio = sorteio;
	}

	Par() {
	}

	public Participante getAmigoOculto() {
		// TODO Auto-generated method stub
		return this.amigoOculto;
	}

	public Integer getId() {
		return id;
	}

	public Participante getAmigo() {
		return amigo;
	}

	public Sorteio getSorteio() {
		return sorteio;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAmigo(Participante amigo) {
		this.amigo = amigo;
	}

	public void setAmigoOculto(Participante amigoOculto) {
		this.amigoOculto = amigoOculto;
	}

	public void setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
	}

}
