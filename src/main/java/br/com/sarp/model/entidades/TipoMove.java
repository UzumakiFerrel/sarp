package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipomove")
@SequenceGenerator(name = "seq_tpmove", sequenceName = "seq_tpmove", initialValue = 1)
public class TipoMove {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_tpmove")
	@Column(name = "cd_tpmove")
	private Integer cd_tpmove;
	private String nome;
	
	public Integer getCd_tpmove() {
		return cd_tpmove;
	}
	public void setCd_tpmove(Integer cd_tpmove) {
		this.cd_tpmove = cd_tpmove;
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
				+ ((cd_tpmove == null) ? 0 : cd_tpmove.hashCode());
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
		TipoMove other = (TipoMove) obj;
		if (cd_tpmove == null) {
			if (other.cd_tpmove != null)
				return false;
		} else if (!cd_tpmove.equals(other.cd_tpmove))
			return false;
		return true;
	}
	
	
	
}
