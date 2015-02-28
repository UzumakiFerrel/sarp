package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "situacaorequisicao")
@SequenceGenerator(name = "seq_srequisicao", sequenceName = "seq_srequisicao", initialValue = 1)
public class SituacaoRequisicao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_srequisicao")
	@Column(name = "cd_sitrm")
	private Integer cd_sitrm;

	private String nome;

	public Integer getCd_sitrm() {
		return cd_sitrm;
	}

	public void setCd_sitrm(Integer cd_sitrm) {
		this.cd_sitrm = cd_sitrm;
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
				+ ((cd_sitrm == null) ? 0 : cd_sitrm.hashCode());
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
		SituacaoRequisicao other = (SituacaoRequisicao) obj;
		if (cd_sitrm == null) {
			if (other.cd_sitrm != null)
				return false;
		} else if (!cd_sitrm.equals(other.cd_sitrm))
			return false;
		return true;
	}

}
