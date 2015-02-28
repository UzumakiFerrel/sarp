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
@Table(name="cidades")
@SequenceGenerator(name = "seq_cidades" , sequenceName="seq_cidades" , initialValue=1)
public class Cidades {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_cidades")
	@Column(name="cd_cid")
	private Integer cd_cid;
	private String nome;

	@ManyToOne
	@JoinColumn
	private Estados estado = new Estados();

	public Integer getCd_cid() {
		return cd_cid;
	}

	public void setCd_cid(Integer cd_cid) {
		this.cd_cid = cd_cid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd_cid == null) ? 0 : cd_cid.hashCode());
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
		Cidades other = (Cidades) obj;
		if (cd_cid == null) {
			if (other.cd_cid != null)
				return false;
		} else if (!cd_cid.equals(other.cd_cid))
			return false;
		return true;
	}

}
