package br.com.sarp.model.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Rebocador {

	// Informa que este campo he uma chave da tabela
	@Id
	/*
	 * defini o campo id como sequencial
	 */
	@SequenceGenerator(name = "seq_reb")
	@GeneratedValue
	private Integer cd_rebocador;

	private String inscricao; // unique

	private String nome;
	private float boca;
	private float calado;
	private float bollardpull;
	private float comprimento;

	@ManyToOne
	@JoinColumn
	private Base base = new Base();

	@ManyToOne
	@JoinColumn
	private Propulsao propulsao = new Propulsao(); // relacionamento um para um
													// Propulsao.class
	@ManyToOne
	@JoinColumn
	private SituacaoRebocador sitrb = new SituacaoRebocador(); // relacionamento
																// um para um
																// SituacaoRebocador.class
	public Integer getCd_rebocador() {
		return cd_rebocador;
	}

	public void setCd_rebocador(Integer cd_rebocador) {
		this.cd_rebocador = cd_rebocador;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
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

	public float getBollardpull() {
		return bollardpull;
	}

	public void setBollardpull(float bollardpull) {
		this.bollardpull = bollardpull;
	}

	public float getComprimento() {
		return comprimento;
	}

	public void setComprimento(float comprimento) {
		this.comprimento = comprimento;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Propulsao getPropulsao() {
		return propulsao;
	}

	public void setPropulsao(Propulsao propulsao) {
		this.propulsao = propulsao;
	}

	public SituacaoRebocador getSitrb() {
		return sitrb;
	}

	public void setSitrb(SituacaoRebocador sitrb) {
		this.sitrb = sitrb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_rebocador == null) ? 0 : cd_rebocador.hashCode());
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
		Rebocador other = (Rebocador) obj;
		if (cd_rebocador == null) {
			if (other.cd_rebocador != null)
				return false;
		} else if (!cd_rebocador.equals(other.cd_rebocador))
			return false;
		return true;
	}

}
