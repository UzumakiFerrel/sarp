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
@Table(name="embarcacoes")
@SequenceGenerator(name = "seq_embarcacoes" , sequenceName="seq_embarcacoes" , initialValue=1)
public class Embarcacoes {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_embarcacoes")
	@Column(name="cd_embarcacoes")
	private Integer cd_embarcacoes;
	private Integer imo;
	private String nome;
	
	private float tpb;
	private float boca;
	private float calado;
	private float comprimento;

	@JoinColumn
	@ManyToOne
	private Armador armador;

	@JoinColumn
	@ManyToOne
	private TipoEmbarcacoes tipoEmbarcacoes;
	
	public Integer getCd_embarcacoes() {
		return cd_embarcacoes;
	}

	public void setCd_embarcacoes(Integer cd_embarcacoes) {
		this.cd_embarcacoes = cd_embarcacoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getBoca() {
		return boca;
	}

	public void setBoca(float boca) {
		this.boca = boca;
	}

	public float getCalado() {
		return calado;
	}

	public void setCalado(float calado) {
		this.calado = calado;
	}

	public float getComprimento() {
		return comprimento;
	}

	public void setComprimento(float comprimento) {
		this.comprimento = comprimento;
	}

	public Armador getArmador() {
		return armador;
	}

	public void setArmador(Armador armador) {
		this.armador = armador;
	}

	public float getTpb() {
		return tpb;
	}

	public void setTpb(float tpb) {
		this.tpb = tpb;
	}

	public Integer getImo() {
		return imo;
	}

	public void setImo(Integer imo) {
		this.imo = imo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_embarcacoes == null) ? 0 : cd_embarcacoes.hashCode());
		result = prime * result + ((imo == null) ? 0 : imo.hashCode());
		return result;
	}
	
	public TipoEmbarcacoes getTipoEmbarcacoes() {
		return tipoEmbarcacoes;
	}

	public void setTipoEmbarcacoes(TipoEmbarcacoes tipoEmbarcacoes) {
		this.tipoEmbarcacoes = tipoEmbarcacoes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Embarcacoes other = (Embarcacoes) obj;
		if (cd_embarcacoes == null) {
			if (other.cd_embarcacoes != null)
				return false;
		} else if (!cd_embarcacoes.equals(other.cd_embarcacoes))
			return false;
		if (imo == null) {
			if (other.imo != null)
				return false;
		} else if (!imo.equals(other.imo))
			return false;
		return true;
	}
	
	
	
}
