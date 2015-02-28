package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "armador")
@SequenceGenerator(name = "seq_armador", sequenceName = "seq_armador", initialValue = 1)
public class Armador {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_armador")
	@Column(name = "cd_armador")
	
	private Integer cd_armador;
	private String cnpj;
	private String nome;

	public Integer getCd_armador() {
		return cd_armador;
	}

	public void setCd_armador(Integer cd_armador) {
		this.cd_armador = cd_armador;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_armador == null) ? 0 : cd_armador.hashCode());
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
		Armador other = (Armador) obj;
		if (cd_armador == null) {
			if (other.cd_armador != null)
				return false;
		} else if (!cd_armador.equals(other.cd_armador))
			return false;
		return true;
	}

}
