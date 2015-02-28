package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="categoriamaritimo")
@SequenceGenerator(name = "seq_cmaritimo" , sequenceName="seq_cmaritimo" , initialValue=1)
public class CategoriaMaritimo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_cmaritimo")
	@Column(name = "cd_categoria")
	
	private Integer cd_categoria;
	
	private String nome;

	private String sigla;
	
	public Integer getCd_categoria() {
		return cd_categoria;
	}

	public void setCd_categoria(Integer cd_categoria) {
		this.cd_categoria = cd_categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_categoria == null) ? 0 : cd_categoria.hashCode());
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
		CategoriaMaritimo other = (CategoriaMaritimo) obj;
		if (cd_categoria == null) {
			if (other.cd_categoria != null)
				return false;
		} else if (!cd_categoria.equals(other.cd_categoria))
			return false;
		return true;
	}
	
}
