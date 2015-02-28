package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tipohistoricorebocador")
@SequenceGenerator(name = "seq_tphistoricorb" , sequenceName="seq_tphistoricorb" , initialValue=1)

public class TipoHistoricoRebocador {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_tphistoricorb")
	@Column(name="cd_tphistoricorb")

	private Integer cd_tphistoricorb;
	private String nome;
	
	public Integer getCd_tphistoricorb() {
		return cd_tphistoricorb;
	}
	public void setCd_tphistoricorb(Integer cd_tphistoricorb) {
		this.cd_tphistoricorb = cd_tphistoricorb;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((cd_tphistoricorb == null) ? 0 : cd_tphistoricorb.hashCode());
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
		TipoHistoricoRebocador other = (TipoHistoricoRebocador) obj;
		if (cd_tphistoricorb == null) {
			if (other.cd_tphistoricorb != null)
				return false;
		} else if (!cd_tphistoricorb.equals(other.cd_tphistoricorb))
			return false;
		return true;
	}
	
	
}
