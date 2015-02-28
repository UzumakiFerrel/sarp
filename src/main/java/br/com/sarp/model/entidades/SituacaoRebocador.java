package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "situacaorebocador")
@SequenceGenerator(name = "seq_srebocador", sequenceName = "seq_srebocador", initialValue = 1)
public class SituacaoRebocador {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_srebocador")
	@Column(name = "cd_sitrb")
	private Integer cd_sitrb;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCd_sitrb() {
		return cd_sitrb;
	}

	public void setCd_sitrb(Integer cd_sitrb) {
		this.cd_sitrb = cd_sitrb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_sitrb == null) ? 0 : cd_sitrb.hashCode());
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
		SituacaoRebocador other = (SituacaoRebocador) obj;
		if (cd_sitrb == null) {
			if (other.cd_sitrb != null)
				return false;
		} else if (!cd_sitrb.equals(other.cd_sitrb))
			return false;
		return true;
	}

}
