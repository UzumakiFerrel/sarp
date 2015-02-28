package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "propulsao")
@SequenceGenerator(name = "seq_propulsao", sequenceName = "seq_propulsao", initialValue = 1)
public class Propulsao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_propulsao")
	@Column(name = "cd_propulsao")
	
	private Integer cd_propulsao;
	private String nome;

	public Integer getCd_propulsao() {
		return cd_propulsao;
	}

	public void setCd_propulsao(Integer cd_propulsao) {
		this.cd_propulsao = cd_propulsao;
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
				+ ((cd_propulsao == null) ? 0 : cd_propulsao.hashCode());
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
		Propulsao other = (Propulsao) obj;
		if (cd_propulsao == null) {
			if (other.cd_propulsao != null)
				return false;
		} else if (!cd_propulsao.equals(other.cd_propulsao))
			return false;
		return true;
	}
	
	

}
