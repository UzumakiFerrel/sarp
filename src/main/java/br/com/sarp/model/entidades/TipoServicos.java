package br.com.sarp.model.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TipoServicos {

	@Id
	// defini o campo id como sequencial
	@SequenceGenerator(name = "seq_tpservicos")
	@GeneratedValue
	private Integer cd_tpservicos;
	private String nome;
	
	
	
	
	public Integer getCd_tpservicos() {
		return cd_tpservicos;
	}
	public void setCd_tpservicos(Integer cd_tpservicos) {
		this.cd_tpservicos = cd_tpservicos;
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
				+ ((cd_tpservicos == null) ? 0 : cd_tpservicos.hashCode());
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
		TipoServicos other = (TipoServicos) obj;
		if (cd_tpservicos == null) {
			if (other.cd_tpservicos != null)
				return false;
		} else if (!cd_tpservicos.equals(other.cd_tpservicos))
			return false;
		return true;
	}
	
	
}
