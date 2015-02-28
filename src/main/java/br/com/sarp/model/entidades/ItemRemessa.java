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
@Table(name = "itemremessa")
@SequenceGenerator(name = "seq_iremessa", sequenceName = "seq_iremessa", initialValue = 1)
public class ItemRemessa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_iremessa")
	@Column(name = "iD")
	
	private Integer iD; //campo id obrigatorio do hibernate
	private Integer numItem;
 
	@JoinColumn
	@ManyToOne
	private RemessaMaterial remessa;
	
	private float quantidade;
	private String Data;
	
	@ManyToOne
	@JoinColumn
	private CadastroInsumo insumo = new CadastroInsumo();

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public Integer getNumItem() {
		return numItem;
	}

	public void setNumItem(Integer numItem) {
		this.numItem = numItem;
	}

	public RemessaMaterial getRemessa() {
		return remessa;
	}

	public void setRemessa(RemessaMaterial remessa) {
		this.remessa = remessa;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
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
		result = prime * result + ((insumo == null) ? 0 : insumo.hashCode());
		result = prime * result + ((remessa == null) ? 0 : remessa.hashCode());
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
		ItemRemessa other = (ItemRemessa) obj;
		if (insumo == null) {
			if (other.insumo != null)
				return false;
		} else if (!insumo.equals(other.insumo))
			return false;
		if (remessa == null) {
			if (other.remessa != null)
				return false;
		} else if (!remessa.equals(other.remessa))
			return false;
		return true;
	};
	
}
