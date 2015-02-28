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
@Table(name = "pratico")
@SequenceGenerator(name = "seq_pratico", sequenceName = "seq_pratico", initialValue = 1)
public class Pratico {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pratico")
	@Column(name = "cd_pratico")
	private Integer cd_pratico;
	private String nome;

	@JoinColumn
	@ManyToOne
	private Base base;

	public Integer getCd_pratico() {
		return cd_pratico;
	}

	public void setCd_pratico(Integer cd_pratico) {
		this.cd_pratico = cd_pratico;
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
				+ ((cd_pratico == null) ? 0 : cd_pratico.hashCode());
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
		Pratico other = (Pratico) obj;
		if (cd_pratico == null) {
			if (other.cd_pratico != null)
				return false;
		} else if (!cd_pratico.equals(other.cd_pratico))
			return false;
		return true;
	}

}
