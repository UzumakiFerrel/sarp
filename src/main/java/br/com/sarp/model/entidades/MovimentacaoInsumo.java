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
@Table(name = "movimentacaoinsumo")
@SequenceGenerator(name = "seq_minsumo", sequenceName = "seq_minsumo", initialValue = 1)

public class MovimentacaoInsumo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_minsumo")
	@Column(name = "cd_movinsumo")
	private Integer cd_movinsumo;
	private String data;
	private Integer documento;
	
	@ManyToOne
	@JoinColumn
	private CadastroInsumo insumo;

	@ManyToOne
	@JoinColumn
	private TipoMove movimentacao;

	@ManyToOne
	@JoinColumn
	private Usuario usuario;

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public Integer getCd_movinsumo() {
		return cd_movinsumo;
	}

	public void setCd_movinsumo(Integer cd_movinsumo) {
		this.cd_movinsumo = cd_movinsumo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public CadastroInsumo getInsumo() {
		return insumo;
	}

	public void setInsumo(CadastroInsumo insumo) {
		this.insumo = insumo;
	}

	public TipoMove getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(TipoMove movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
