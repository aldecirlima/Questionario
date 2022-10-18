package br.com.caelum.auron.modelo;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Sorteio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	private String nome;
	
	@OneToMany(mappedBy="sorteio", cascade=CascadeType.PERSIST)
	private Set<Par> pares = new LinkedHashSet<>();

	public Sorteio() {
	}
	
	public void adicionaPar(Par par) {
		this.pares.add(par);
	}
	

	public Sorteio(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Par> getPares() {
		return Collections.unmodifiableSet(pares);
	}

	public void setPares(Set<Par> pares) {
		this.pares = pares;
	}

	public int getQuantidadeDePares() {
		return pares.size();
	}

}
