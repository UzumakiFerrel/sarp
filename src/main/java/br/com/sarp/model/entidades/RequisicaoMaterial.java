package br.com.sarp.model.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
@Table(name="requisicaomaterial")
@SequenceGenerator(name = "seq_rmaterial" , sequenceName="seq_rmaterial" , initialValue=1)
@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_rmaterial")
	@Column(name="cd_rmaterial")
	*/

@Entity
public class RequisicaoMaterial {

	@Id
	private Integer num_req;

	private String abertura, encerramento, prazo;
	private Integer vencido;
	
	@ManyToOne
	@JoinColumn
	private Prioridades prioridade = new Prioridades();
	
	@ManyToOne
	@JoinColumn
	private SituacaoRequisicao sit = new SituacaoRequisicao();

	@ManyToOne
	@JoinColumn
	private CentroCusto cCustoOrigem,cCustoDestino;

	@ManyToOne
	@JoinColumn
	private Usuario solicitante;

	@ManyToOne
	@JoinColumn
	private  Secao secao;

	public Integer getNum_req() {
		return num_req;
	}

	public void setNum_req(Integer num_req) {
		this.num_req = num_req;
	}

		public SituacaoRequisicao getSit() {
		return sit;
	}

	public void setSit(SituacaoRequisicao sit) {
		this.sit = sit;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}


	public String getEncerramento() {
		return encerramento;
	}

	public void setEncerramento(String encerramento) {
		this.encerramento = encerramento;
	}

	public String getAbertura() {
		return abertura;
	}

	public void setAbertura(String abertura) {
		this.abertura = abertura;
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

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Prioridades getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridades prioridade) {
		this.prioridade = prioridade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((num_req == null) ? 0 : num_req.hashCode());
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
		RequisicaoMaterial other = (RequisicaoMaterial) obj;
		if (num_req == null) {
			if (other.num_req != null)
				return false;
		} else if (!num_req.equals(other.num_req))
			return false;
		return true;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public Integer getVencido() {
		return vencido;
	}

	public void setVencido(Integer vencido) {
		this.vencido = vencido;
	}

}
