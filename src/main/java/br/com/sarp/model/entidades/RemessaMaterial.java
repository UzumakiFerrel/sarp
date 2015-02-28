package br.com.sarp.model.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RemessaMaterial {

	@Id
	/*
	@SequenceGenerator(name = "seq_remessa")
	@GeneratedValue
	*/
	private Integer num_rem;
	private String data;
	private Integer documento; //documento que a remessa vai baixar

	@ManyToOne
	@JoinColumn
	private TipoDocumento tpDocumento;
	
	@ManyToOne
	@JoinColumn
	private CentroCusto cCustoOrigem,cCustoDestino;
	
	@ManyToOne
	@JoinColumn
	private SituacaoRequisicao status;

	@ManyToOne
	@JoinColumn
	private Usuario solicitante;

	@ManyToOne
	@JoinColumn
	private Base base;

	@ManyToOne
	@JoinColumn
	private RequisicaoMaterial solicitacao;
	
	public Integer getNum_rem() {
		return num_rem;
	}

	public void setNum_rem(Integer num_rem) {
		this.num_rem = num_rem;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public TipoDocumento getTpDocumento() {
		return tpDocumento;
	}

	public void setTpDocumento(TipoDocumento tpDocumento) {
		this.tpDocumento = tpDocumento;
	}
	
	public SituacaoRequisicao getStatus() {
		return status;
	}

	public void setStatus(SituacaoRequisicao status) {
		this.status = status;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
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

	public RequisicaoMaterial getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(RequisicaoMaterial solicitacao) {
		this.solicitacao = solicitacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((num_rem == null) ? 0 : num_rem.hashCode());
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
		RemessaMaterial other = (RemessaMaterial) obj;
		if (num_rem == null) {
			if (other.num_rem != null)
				return false;
		} else if (!num_rem.equals(other.num_rem))
			return false;
		return true;
	}
	
}
