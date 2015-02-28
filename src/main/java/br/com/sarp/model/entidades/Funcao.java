package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="funcao")
@SequenceGenerator(name = "seq_funcao" , sequenceName="seq_funcao" , initialValue=1)
public class Funcao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_funcao")
	@Column(name="cd_funcao")
		private Integer cd_funcao;
		private String nome;
		private String sigla;
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public Integer getCd_funcao() {
			return cd_funcao;
		}
		public void setCd_funcao(Integer cd_funcao) {
			this.cd_funcao = cd_funcao;
		}

		public String getSigla() {
			return sigla;
		}
		public void setSigla(String sigla) {
			this.sigla = sigla;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((cd_funcao == null) ? 0 : cd_funcao.hashCode());
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
			Funcao other = (Funcao) obj;
			if (cd_funcao == null) {
				if (other.cd_funcao != null)
					return false;
			} else if (!cd_funcao.equals(other.cd_funcao))
				return false;
			return true;
		}

		
}
