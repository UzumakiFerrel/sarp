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
@Table(name="centrocusto")
@SequenceGenerator(name = "seq_ccusto" , sequenceName="seq_ccusto" ,initialValue=1)

public class CentroCusto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_ccusto")
	@Column(name="cd_custo")

	private Integer cd_custo;

	@JoinColumn
	@ManyToOne
	private Base base = new Base();

	@JoinColumn
	@ManyToOne
	private Setor setor;


	@JoinColumn
	@ManyToOne(optional = true)
	private Rebocador rebocador = new Rebocador();

	private float total;

	public Integer getCd_custo() {
		return cd_custo;
	}

	public void setCd_custo(Integer cd_custo) {
		this.cd_custo = cd_custo;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Rebocador getRebocador() {
		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result
				+ ((cd_custo == null) ? 0 : cd_custo.hashCode());
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
		CentroCusto other = (CentroCusto) obj;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (cd_custo == null) {
			if (other.cd_custo != null)
				return false;
		} else if (!cd_custo.equals(other.cd_custo))
			return false;
		return true;
	}

}
