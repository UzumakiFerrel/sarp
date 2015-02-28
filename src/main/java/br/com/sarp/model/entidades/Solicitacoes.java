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
@Table(name = "solicitacoes")
@SequenceGenerator(name = "seq_solicitacoes", sequenceName = "seq_solicitacoes", initialValue = 1)
public class Solicitacoes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_solicitacoes")
	@Column(name = "cd_solicitacoes")
	private Integer cd_solicitacoes;
	private Integer num_documento;
	private String tipo;

	@JoinColumn
	@ManyToOne
	private CentroCusto origem = new CentroCusto();
	
	public Integer getCd_solicitacoes() {
		return cd_solicitacoes;
	}

	public void setCd_solicitacoes(Integer cd_solicitacoes) {
		this.cd_solicitacoes = cd_solicitacoes;
	}

	public Integer getNum_documento() {
		return num_documento;
	}

	public void setNum_documento(Integer num_documento) {
		this.num_documento = num_documento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public CentroCusto getOrigem() {
		return origem;
	}

	public void setOrigem(CentroCusto origem) {
		this.origem = origem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_solicitacoes == null) ? 0 : cd_solicitacoes.hashCode());
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
		Solicitacoes other = (Solicitacoes) obj;
		if (cd_solicitacoes == null) {
			if (other.cd_solicitacoes != null)
				return false;
		} else if (!cd_solicitacoes.equals(other.cd_solicitacoes))
			return false;
		return true;
	}

}
