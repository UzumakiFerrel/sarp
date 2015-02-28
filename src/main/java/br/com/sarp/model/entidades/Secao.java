package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="secao")
@SequenceGenerator(name = "seq_secao" , sequenceName="seq_secao" ,initialValue=1)
public class Secao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_secao")
	@Column(name="cd_secao")
	
	private Integer cd_secao;
	private String nome;

	public String getNome() {
		return nome;
	}

	public Integer getCd_secao() {
		return cd_secao;
	}

	public void setCd_secao(Integer cd_secao) {
		this.cd_secao = cd_secao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_secao == null) ? 0 : cd_secao.hashCode());
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
		Secao other = (Secao) obj;
		if (cd_secao == null) {
			if (other.cd_secao != null)
				return false;
		} else if (!cd_secao.equals(other.cd_secao))
			return false;
		return true;
	}
}
