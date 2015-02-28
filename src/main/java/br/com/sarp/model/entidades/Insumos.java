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
@Table(name="insumos")
@SequenceGenerator(name = "seq_insum" , sequenceName="seq_insum" , initialValue=1)
public class Insumos {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_insum")
	@Column(name="cd_insumos")
	private Integer cd_insumos; 

	private float quantidade;

	@JoinColumn
	@ManyToOne
	private CadastroInsumo insumo;

	@JoinColumn
	@ManyToOne
	private CentroCusto ccusto;

	public Integer getCd_insumos() {
		return cd_insumos;
	}
	
	public void setCd_insumos(Integer cd_insumos) {
		this.cd_insumos = cd_insumos;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public CadastroInsumo getInsumo() {
		return insumo;
	}

	public void setInsumo(CadastroInsumo insumo) {
		this.insumo = insumo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_insumos == null) ? 0 : cd_insumos.hashCode());
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
		Insumos other = (Insumos) obj;
		if (cd_insumos == null) {
			if (other.cd_insumos != null)
				return false;
		} else if (!cd_insumos.equals(other.cd_insumos))
			return false;
		return true;
	}
	
	
}
