package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipodocumento")
@SequenceGenerator(name = "seq_tipodocumento", sequenceName = "seq_tipodocumento", initialValue = 1)
public class TipoDocumento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_tipodocumento")
	@Column(name = "cd_tpdocumento")
	
		private Integer cd_tpdocumento;
		private String nome;
		private String sigla;
		
		public Integer getCd_tpdocumento() {
			return cd_tpdocumento;
		}
		public void setCd_tpdocumento(Integer cd_tpdocumento) {
			this.cd_tpdocumento = cd_tpdocumento;
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
					+ ((cd_tpdocumento == null) ? 0 : cd_tpdocumento.hashCode());
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
			TipoDocumento other = (TipoDocumento) obj;
			if (cd_tpdocumento == null) {
				if (other.cd_tpdocumento != null)
					return false;
			} else if (!cd_tpdocumento.equals(other.cd_tpdocumento))
				return false;
			return true;
		}
		public String getSigla() {
			return sigla;
		}
		public void setSigla(String sigla) {
			this.sigla = sigla;
		}
		
		
		

}
