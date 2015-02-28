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
@Table(name="cadastroinsumo")
@SequenceGenerator(name = "seq_cinsumo" , sequenceName="seq_cinsumo", initialValue=1)
public class CadastroInsumo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_cinsumo")
	@Column(name="cd_insumo")

	private Integer cd_insumo;
	private String descricao;

	private Boolean ativo;
	
	@JoinColumn
	@ManyToOne
	private Unidades unidade;
	
	@JoinColumn
	@ManyToOne
	private Secao secao = new Secao();

	@JoinColumn
	@ManyToOne
	private AplicacaoInsumo aplicacao = new AplicacaoInsumo();
	
	public Integer getCd_insumo() {
		return cd_insumo;
	}

	public void setCd_insumo(Integer cd_insumo) {
		this.cd_insumo = cd_insumo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public AplicacaoInsumo getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(AplicacaoInsumo aplicacao) {
		this.aplicacao = aplicacao;
	}

	
	public Unidades getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidades unidade) {
		this.unidade = unidade;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_insumo == null) ? 0 : cd_insumo.hashCode());
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
		CadastroInsumo other = (CadastroInsumo) obj;
		if (cd_insumo == null) {
			if (other.cd_insumo != null)
				return false;
		} else if (!cd_insumo.equals(other.cd_insumo))
			return false;
		return true;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
