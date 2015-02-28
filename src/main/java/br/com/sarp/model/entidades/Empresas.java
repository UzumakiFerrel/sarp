package br.com.sarp.model.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Empresas {
	
	// Informa que este campo he uma chave da tabela
		@Id
		// defini o campo id como sequencial
		@SequenceGenerator(name = "seq_emp")
		@GeneratedValue
		
		private Integer cd_empresa;
		private String cnpj; 
		private String nome;
		
		@ManyToOne
		@JoinColumn
		private TipoEmpresas tipoEmpresas = new TipoEmpresas(); //chave estrangeira da tabela tipo empresa

		public Integer getCd_empresa() {
			return cd_empresa;
		}

		public void setCd_empresa(Integer cd_empresa) {
			this.cd_empresa = cd_empresa;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		
		
		public TipoEmpresas getTipoEmpresas() {
			return tipoEmpresas;
		}

		public void setTipoEmpresas(TipoEmpresas tipoEmpresas) {
			this.tipoEmpresas = tipoEmpresas;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((cd_empresa == null) ? 0 : cd_empresa.hashCode());
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
			Empresas other = (Empresas) obj;
			if (cd_empresa == null) {
				if (other.cd_empresa != null)
					return false;
			} else if (!cd_empresa.equals(other.cd_empresa))
				return false;
			return true;
		}
		
}
