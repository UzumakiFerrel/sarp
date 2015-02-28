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
@Table(name = "historicoembarque")
@SequenceGenerator(name = "seq_hembarque", sequenceName = "seq_hembarque", initialValue = 1)
public class HistoricoEmbarque {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_hembarque")
	@Column(name = "cd_embarque")
	
	private Integer cd_embarque;
	private String embarque;
	private String hrembarque;
	private String desenbarque;
	private String hrdesenbarque;

	@ManyToOne
	@JoinColumn
	private Rebocador rebocador = new Rebocador(); // chave estrangeira para
													// tabela rebocador
	@ManyToOne
	@JoinColumn
	private PrestadorServico prestadorServico; // = new Rebocador() chave

	@ManyToOne
	@JoinColumn
	private Usuario usuario = new Usuario();

	@ManyToOne
	@JoinColumn
	private Locais localembarque = new Locais(); // chave estrangeira para
													// tabela locais
	@ManyToOne
	@JoinColumn
	private Locais localdesenbarque = new Locais(); // chave estrangeira para

	// tabela locais
	public Integer getCd_embarque() {
		return cd_embarque;
	}

	public void setCd_embarque(Integer cd_embarque) {
		this.cd_embarque = cd_embarque;
	}


	public String getEmbarque() {
		return embarque;
	}

	public void setEmbarque(String embarque) {
		this.embarque = embarque;
	}

	public String getHrembarque() {
		return hrembarque;
	}

	public void setHrembarque(String hrembarque) {
		this.hrembarque = hrembarque;
	}

	public String getDesenbarque() {
		return desenbarque;
	}

	public void setDesenbarque(String desenbarque) {
		this.desenbarque = desenbarque;
	}

	public String getHrdesenbarque() {
		return hrdesenbarque;
	}

	public void setHrdesenbarque(String hrdesenbarque) {
		this.hrdesenbarque = hrdesenbarque;
	}

	public Rebocador getRebocador() {
		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Locais getLocalembarque() {
		return localembarque;
	}

	public void setLocalembarque(Locais localembarque) {
		this.localembarque = localembarque;
	}

	public Locais getLocaldesenbarque() {
		return localdesenbarque;
	}

	public void setLocaldesenbarque(Locais localdesenbarque) {
		this.localdesenbarque = localdesenbarque;
	}

	public PrestadorServico getPrestadorServico() {
		return prestadorServico;
	}

	public void setPrestadorServico(PrestadorServico prestadorServico) {
		this.prestadorServico = prestadorServico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_embarque == null) ? 0 : cd_embarque.hashCode());
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
		HistoricoEmbarque other = (HistoricoEmbarque) obj;
		if (cd_embarque == null) {
			if (other.cd_embarque != null)
				return false;
		} else if (!cd_embarque.equals(other.cd_embarque))
			return false;
		return true;
	}

}
