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
@Table(name="tripulacao")
@SequenceGenerator(name = "seq_tripulacao" , sequenceName="seq_tripulacao" , initialValue=1)
public class Tripulacao {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_tripulacao")
	@Column(name="cd_tripulacao")
	private Integer cd_tripulacao;

	@JoinColumn
	@ManyToOne
	private Rebocador rebocador = new Rebocador(); //relacionamento muitos para um Rebocador.class
	
	public Integer getCd_tripulacao() {
		return cd_tripulacao;
	}

	public void setCd_tripulacao(Integer cd_tripulacao) {
		this.cd_tripulacao = cd_tripulacao;
	}

	public Rebocador getRebocador() {
		return rebocador;
	}

	public void setRebocador(Rebocador rebocador) {
		this.rebocador = rebocador;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cd_tripulacao == null) ? 0 : cd_tripulacao.hashCode());
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
		Tripulacao other = (Tripulacao) obj;
		if (cd_tripulacao == null) {
			if (other.cd_tripulacao != null)
				return false;
		} else if (!cd_tripulacao.equals(other.cd_tripulacao))
			return false;
		return true;
	}

}
