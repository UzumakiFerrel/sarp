package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="estados")
@SequenceGenerator(name = "seq_estados", sequenceName = "seq_estados" , initialValue=1)
public class Estados {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_estados")
	@Column(name="cd_uf")
		
		private Integer cd_uf;
		private String nome;
		private String uf;

		public String getUf() {
			return uf;
		}
		public void setUf(String uf) {
			this.uf = uf;
		}
		
		public Integer getCd_uf() {
			return cd_uf;
		}
		public void setCd_uf(Integer cd_uf) {
			this.cd_uf = cd_uf;
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
			result = prime * result + ((cd_uf == null) ? 0 : cd_uf.hashCode());
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
			Estados other = (Estados) obj;
			if (cd_uf == null) {
				if (other.cd_uf != null)
					return false;
			} else if (!cd_uf.equals(other.cd_uf))
				return false;
			return true;
		}
		
}
