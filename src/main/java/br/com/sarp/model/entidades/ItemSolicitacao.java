package br.com.sarp.model.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ItemSolicitacao {

	@Id
	// defini o campo id como sequencial
	@SequenceGenerator(name = "seq_itemss")
	@GeneratedValue
	private Integer iD; // campo id obrigatorio do hibernate
	private Date data;
	
	@JoinColumn
	@ManyToOne
	private AplicacaoInsumo aplicacao = new AplicacaoInsumo();

	@JoinColumn
	@ManyToOne
	private SolicitacaoServico solicitacao = new SolicitacaoServico();

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public AplicacaoInsumo getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(AplicacaoInsumo aplicacao) {
		this.aplicacao = aplicacao;
	}

	public SolicitacaoServico getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoServico solicitacao) {
		this.solicitacao = solicitacao;
	}

}
