package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "unidades")
@SequenceGenerator(name = "seq_unidades", sequenceName = "seq_unidades", initialValue = 1)
public class Unidades {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_unidades")
	@Column(name = "cd_unidades")
	
	private Integer cd_unidades;
	private String sigla;
	private String nome;

	public Integer getCd_unidades() {
		return cd_unidades;
	}

	public void setCd_unidades(Integer cd_unidades) {
		this.cd_unidades = cd_unidades;
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
				+ ((cd_unidades == null) ? 0 : cd_unidades.hashCode());
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
		Unidades other = (Unidades) obj;
		if (cd_unidades == null) {
			if (other.cd_unidades != null)
				return false;
		} else if (!cd_unidades.equals(other.cd_unidades))
			return false;
		return true;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
