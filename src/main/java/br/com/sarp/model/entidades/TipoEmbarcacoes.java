package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipoembarcacoes")
@SequenceGenerator(name = "seq_tipoembarcacoes", sequenceName = "seq_tipoembarcacoes", initialValue = 1)
public class TipoEmbarcacoes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_tipoembarcacoes")
	@Column(name = "cd_tpemb")
	private Integer cd_tpemb;
	private String nome;

	public Integer getCd_tpemb() {
		return cd_tpemb;
	}

	public void setCd_tpemb(Integer cd_tpemb) {
		this.cd_tpemb = cd_tpemb;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
