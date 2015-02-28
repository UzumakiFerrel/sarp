package br.com.sarp.model.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TipoEmpresas {
	
	// Informa que este campo he uma chave da tabela
		@Id
		// defini o campo id como sequencial
		@SequenceGenerator(name = "seq_tpemp")
		@GeneratedValue
		private Integer tp_empresa;
		private String nome;
		
		
		public Integer getTp_empresa() {
			return tp_empresa;
		}
		public void setTp_empresa(Integer tp_empresa) {
			this.tp_empresa = tp_empresa;
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
					+ ((tp_empresa == null) ? 0 : tp_empresa.hashCode());
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
			TipoEmpresas other = (TipoEmpresas) obj;
			if (tp_empresa == null) {
				if (other.tp_empresa != null)
					return false;
			} else if (!tp_empresa.equals(other.tp_empresa))
				return false;
			return true;
		}
		

}
