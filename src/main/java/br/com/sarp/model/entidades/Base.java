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
@Table(name="base")
@SequenceGenerator(name = "seq_base" , sequenceName="seq_base" , initialValue=1)
public class Base {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_base")
	@Column(name="cd_base")
	
	private Integer cd_base;
	private String nome;
	
	@JoinColumn
	@ManyToOne
	private Estados estado = new Estados();
	
	@JoinColumn
	@ManyToOne
	private Cidades cidade = new Cidades();
	
	public Integer getCd_base() {
		return cd_base;
	}
	public void setCd_base(Integer cd_base) {
		this.cd_base = cd_base;
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
	public Cidades getCidade() {
		return cidade;
	}
	public void setCidade(Cidades cidade) {
		this.cidade = cidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd_base == null) ? 0 : cd_base.hashCode());
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
		Base other = (Base) obj;
		if (cd_base == null) {
			if (other.cd_base != null)
				return false;
		} else if (!cd_base.equals(other.cd_base))
			return false;
		return true;
	}
		
}
