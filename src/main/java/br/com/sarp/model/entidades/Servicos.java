package br.com.sarp.model.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Servicos {

	@Id
	@SequenceGenerator(name = "seq_servicos")
	@GeneratedValue
	private Integer cd_servicos;

	private String nome;
	private String descricao;

	@JoinColumn
	@ManyToOne
	private TipoServicos tipoServicos;

	@JoinColumn
	@ManyToOne
	private Secao secao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCd_servicos() {
		return cd_servicos;
	}

	public void setCd_servicos(Integer cd_servicos) {
		this.cd_servicos = cd_servicos;
	}

	public TipoServicos getTipoServicos() {
		return tipoServicos;
	}

	public void setTipoServicos(TipoServicos tipoServicos) {
		this.tipoServicos = tipoServicos;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_servicos == null) ? 0 : cd_servicos.hashCode());
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
		Servicos other = (Servicos) obj;
		if (cd_servicos == null) {
			if (other.cd_servicos != null)
				return false;
		} else if (!cd_servicos.equals(other.cd_servicos))
			return false;
		return true;
	}

	
	
}
