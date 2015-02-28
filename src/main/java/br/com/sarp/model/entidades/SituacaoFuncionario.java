package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "situacaofuncionario")
@SequenceGenerator(name = "seq_sfuncionario", sequenceName = "seq_sfuncionario", initialValue = 1)
public class SituacaoFuncionario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_sfuncionario")
	@Column(name = "cd_sit")
	private Integer cd_sit;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCd_sit(Integer cd_sit) {
		this.cd_sit = cd_sit;
	}

	public Integer getCd_sit() {
		return cd_sit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd_sit == null) ? 0 : cd_sit.hashCode());
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
		SituacaoFuncionario other = (SituacaoFuncionario) obj;
		if (cd_sit == null) {
			if (other.cd_sit != null)
				return false;
		} else if (!cd_sit.equals(other.cd_sit))
			return false;
		return true;
	}

}
