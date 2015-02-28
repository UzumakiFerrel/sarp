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
@Table(name = "locais")
@SequenceGenerator(name = "seq_locais", sequenceName = "seq_locais", initialValue = 1)
public class Locais {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_locais")
	@Column(name = "cd_local")
	private Integer cd_local;
	private String nome;

	@JoinColumn
	@ManyToOne
	private Base base;

	public Integer getCd_local() {
		return cd_local;
	}

	public void setCd_local(Integer cd_local) {
		this.cd_local = cd_local;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_local == null) ? 0 : cd_local.hashCode());
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
		Locais other = (Locais) obj;
		if (cd_local == null) {
			if (other.cd_local != null)
				return false;
		} else if (!cd_local.equals(other.cd_local))
			return false;
		return true;
	}

}
