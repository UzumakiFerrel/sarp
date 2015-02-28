package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tipomanobras")
@SequenceGenerator(name = "seq_tpmanobras" , sequenceName="seq_tpmanobras" , initialValue=1)
public class TipoManobras {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_tpmanobras")
	@Column(name="cd_tpmanobras")
		
		private Integer cd_tpmanobras;
		private String nome;
		
		
		public Integer getCd_tpmanobras() {
			return cd_tpmanobras;
		}
		public void setCd_tpmanobras(Integer cd_tpmanobras) {
			this.cd_tpmanobras = cd_tpmanobras;
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
			result = prime * result
					+ ((cd_tpmanobras == null) ? 0 : cd_tpmanobras.hashCode());
			result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
			TipoManobras other = (TipoManobras) obj;
			if (cd_tpmanobras == null) {
				if (other.cd_tpmanobras != null)
					return false;
			} else if (!cd_tpmanobras.equals(other.cd_tpmanobras))
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			return true;
		}
			
		
}
