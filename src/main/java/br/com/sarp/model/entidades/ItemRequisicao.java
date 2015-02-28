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
@Table(name = "itemrequisicao")
@SequenceGenerator(name = "seq_irequisicao", sequenceName = "seq_irequisicao", initialValue = 1)
public class ItemRequisicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_irequisicao")
	@Column(name = "iD")
	
	private Integer iD; //campo id obrigatorio do hibernate
	private Integer numItem;
	private float quantidade,pendente;
	private String Data;
	
	@JoinColumn
	@ManyToOne
	private AplicacaoInsumo aplicacao = new AplicacaoInsumo();
	
	@JoinColumn
	@ManyToOne
	private RequisicaoMaterial requisicao = new RequisicaoMaterial();
	
	@ManyToOne
	@JoinColumn
	private CadastroInsumo insumo = new CadastroInsumo();
	
	@ManyToOne
	@JoinColumn
	private TipoMove sitInsumo = new TipoMove();
	
	
	public TipoMove getSitInsumo() {
		return sitInsumo;
	}

	public void setSitInsumo(TipoMove sitInsumo) {
		this.sitInsumo = sitInsumo;
	}

	public RequisicaoMaterial getRequisicao() {
		return requisicao;
	}

	public AplicacaoInsumo getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(AplicacaoInsumo aplicacao) {
		this.aplicacao = aplicacao;
	}

	public void setRequisicao(RequisicaoMaterial requisicao) {
		this.requisicao = requisicao;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	public CadastroInsumo getInsumo() {
		return insumo;
	}

	public void setInsumo(CadastroInsumo insumo) {
		this.insumo = insumo;
	}


	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public Integer getNumItem() {
		return numItem;
	}

	public void setNumItem(Integer numItem) {
		this.numItem = numItem;
	}

	public float getPendente() {
		return pendente;
	}

	public void setPendente(float pendente) {
		this.pendente = pendente;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((insumo == null) ? 0 : insumo.hashCode());
		result = prime * result
				+ ((requisicao == null) ? 0 : requisicao.hashCode());
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
		ItemRequisicao other = (ItemRequisicao) obj;
		if (insumo == null) {
			if (other.insumo != null)
				return false;
		} else if (!insumo.equals(other.insumo))
			return false;
		if (requisicao == null) {
			if (other.requisicao != null)
				return false;
		} else if (!requisicao.equals(other.requisicao))
			return false;
		return true;
	}
}
