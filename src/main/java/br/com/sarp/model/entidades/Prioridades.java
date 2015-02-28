package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "prioridades")
@SequenceGenerator(name = "seq_prd", sequenceName = "seq_prd", initialValue = 1)
public class Prioridades {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_prd")
	@Column(name = "cd_prioridade")
	private Integer cd_prioridade;
	private String nome;
	private Integer dias;
	
	public Integer getCd_prioridade() {
		return cd_prioridade;
	}
	public void setCd_prioridade(Integer cd_prioridade) {
		this.cd_prioridade = cd_prioridade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getDias() {
		return dias;
	}
	public void setDias(Integer dias) {
		this.dias = dias;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_prioridade == null) ? 0 : cd_prioridade.hashCode());
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
		Prioridades other = (Prioridades) obj;
		if (cd_prioridade == null) {
			if (other.cd_prioridade != null)
				return false;
		} else if (!cd_prioridade.equals(other.cd_prioridade))
			return false;
		return true;
	}
	
}
