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
@Table(name = "passagemcomando")
@SequenceGenerator(name = "seq_pcomando", sequenceName = "seq_pcomando", initialValue = 1)

public class PassagemComando {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pcomando")
	@Column(name = "cd_passagemcomando")
	private Integer cd_passagemComando;
	private String abertura;
	private String trocaTurno;

	@ManyToOne
	@JoinColumn
	private Tripulante comandanteIn, comandanteOut;

	@ManyToOne
	@JoinColumn
	private SituacaoRequisicao stPassagemComando;

	@ManyToOne
	@JoinColumn
	private Rebocador rebocador;

	public Rebocador getRebocador() {
		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public Integer getCd_passagemComando() {
		return cd_passagemComando;
	}

	public void setCd_passagemComando(Integer cd_passagemComando) {
		this.cd_passagemComando = cd_passagemComando;
	}

	public Tripulante getComandanteIn() {
		return comandanteIn;
	}

	public void setComandanteIn(Tripulante comandanteIn) {
		this.comandanteIn = comandanteIn;
	}

	public Tripulante getComandanteOut() {
		return comandanteOut;
	}

	public void setComandanteOut(Tripulante comandanteOut) {
		this.comandanteOut = comandanteOut;
	}

	public String getAbertura() {
		return abertura;
	}

	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}

	public SituacaoRequisicao getStPassagemComando() {
		return stPassagemComando;
	}

	public void setStPassagemComando(SituacaoRequisicao stPassagemComando) {
		this.stPassagemComando = stPassagemComando;
	}

	public String getTrocaTurno() {
		return trocaTurno;
	}

	public void setTrocaTurno(String trocaTurno) {
		this.trocaTurno = trocaTurno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((cd_passagemComando == null) ? 0 : cd_passagemComando
						.hashCode());
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
		PassagemComando other = (PassagemComando) obj;
		if (cd_passagemComando == null) {
			if (other.cd_passagemComando != null)
				return false;
		} else if (!cd_passagemComando.equals(other.cd_passagemComando))
			return false;
		return true;
	}

}
