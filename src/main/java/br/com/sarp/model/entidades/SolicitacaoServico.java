package br.com.sarp.model.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SolicitacaoServico {

	@Id
	/*
	 * defini o campo id como sequencial
	 * 
	 * @SequenceGenerator(name = "seq_rm", initialValue = 0)
	 * 
	 * @GeneratedValue(generator = "seq_rm", strategy = GenerationType.TABLE) o
	 * campo num_req sera definido no bean
	 */

	private Integer num_sserv;
	private Date abertura, encerramento;

	
	@ManyToOne
	@JoinColumn
	private AplicacaoServicos aplicacao; 
	
	@ManyToOne
	@JoinColumn
	private Servicos servico; 
	
	@ManyToOne
	@JoinColumn
	private SituacaoRequisicao sit;

	@ManyToOne
	@JoinColumn
	private CentroCusto cCustoOrigem,cCustoDestino;

	@ManyToOne
	@JoinColumn
	private Usuario solicitante;

	@ManyToOne
	@JoinColumn
	private  Secao secao;


	public Integer getNum_sserv() {
		return num_sserv;
	}

	public void setNum_sserv(Integer num_sserv) {
		this.num_sserv = num_sserv;
	}

	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

	public Date getEncerramento() {
		return encerramento;
	}

	public void setEncerramento(Date encerramento) {
		this.encerramento = encerramento;
	}

	public SituacaoRequisicao getSit() {
		return sit;
	}

	public void setSit(SituacaoRequisicao sit) {
		this.sit = sit;
	}

	public CentroCusto getcCustoOrigem() {
		return cCustoOrigem;
	}

	public void setcCustoOrigem(CentroCusto cCustoOrigem) {
		this.cCustoOrigem = cCustoOrigem;
	}

	public CentroCusto getcCustoDestino() {
		return cCustoDestino;
	}

	public void setcCustoDestino(CentroCusto cCustoDestino) {
		this.cCustoDestino = cCustoDestino;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Servicos getServico() {
		return servico;
	}

	public void setServico(Servicos servico) {
		this.servico = servico;
	}

	public AplicacaoServicos getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(AplicacaoServicos aplicacao) {
		this.aplicacao = aplicacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((num_sserv == null) ? 0 : num_sserv.hashCode());
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
		SolicitacaoServico other = (SolicitacaoServico) obj;
		if (num_sserv == null) {
			if (other.num_sserv != null)
				return false;
		} else if (!num_sserv.equals(other.num_sserv))
			return false;
		return true;
	}
	
	

}
