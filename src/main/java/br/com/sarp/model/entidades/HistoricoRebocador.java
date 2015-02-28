package br.com.sarp.model.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class HistoricoRebocador {

	@Id
	// defini o campo id como sequencial
	@SequenceGenerator(name = "seq_hrb")
	@GeneratedValue
	private Integer cd_historico;
	private Date data;
	private Integer hora;
	private boolean status;
	private String observacao;
	
	
	// numero da requisicao que dara baixa no
								// historico
	@JoinColumn
	@ManyToOne
	private Solicitacoes solicitacao;

	@JoinColumn
	@ManyToOne
	private Tripulante tripulante;

	@JoinColumn
	@ManyToOne
	private TipoHistoricoRebocador tipoHistorico;

	@JoinColumn
	@ManyToOne
	private Rebocador rebocador;

	public Integer getCd_historico() {
		return cd_historico;
	}

	public void setCd_historico(Integer cd_historico) {
		this.cd_historico = cd_historico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoHistoricoRebocador getTipoHistorico() {
		return tipoHistorico;
	}

	public void setTipoHistorico(TipoHistoricoRebocador tipoHistorico) {
		this.tipoHistorico = tipoHistorico;
	}

	public Rebocador getRebocador() {
		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public Tripulante getTripulante() {
		return tripulante;
	}

	public void setTripulante(Tripulante tripulante) {
		this.tripulante = tripulante;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_historico == null) ? 0 : cd_historico.hashCode());
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
		HistoricoRebocador other = (HistoricoRebocador) obj;
		if (cd_historico == null) {
			if (other.cd_historico != null)
				return false;
		} else if (!cd_historico.equals(other.cd_historico))
			return false;
		return true;
	}


	public Solicitacoes getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacoes solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

}
