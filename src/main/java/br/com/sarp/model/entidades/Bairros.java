package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="bairros")
@SequenceGenerator(name = "seq_bairros" , sequenceName="seq_bairros" , initialValue=1)
public class Bairros {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_bairros")
	@Column(name="cd_bair")

	private Integer cd_bair;
	private String nome;
	
	@ManyToOne
	@JoinColumn
	private Cidades cidade = new Cidades();
	
	public Integer getCd_bair() {
		return cd_bair;
	}

	public void setCd_bair(Integer cd_bair) {
		this.cd_bair = cd_bair;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Cidades getCidade() {
		return cidade;
	}

	public void setCidade(Cidades cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cd_bair;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bairros other = (Bairros) obj;
		if (cd_bair != other.cd_bair)
			return false;
		return true;
	}

	
}
