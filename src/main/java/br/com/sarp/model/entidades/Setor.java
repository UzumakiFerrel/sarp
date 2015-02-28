package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "setor")
@SequenceGenerator(name = "seq_setor", sequenceName = "seq_setor",initialValue=1)
public class Setor {

	// Informa que este campo he uma chave da tabela
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_setor")
	@Column(name = "cd_setor")
	private Integer cd_setor;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCd_setor() {
		return cd_setor;
	}

	public void setCd_setor(Integer cd_setor) {
		this.cd_setor = cd_setor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_setor == null) ? 0 : cd_setor.hashCode());
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
		Setor other = (Setor) obj;
		if (cd_setor == null) {
			if (other.cd_setor != null)
				return false;
		} else if (!cd_setor.equals(other.cd_setor))
			return false;
		return true;
	}

}
