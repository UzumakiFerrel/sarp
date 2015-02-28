package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="aplicacaoinsumo")
@SequenceGenerator(name = "seq_ainsumo" , sequenceName="seq_ainsumo" , initialValue=1)
public class AplicacaoInsumo {
	// Informa que este campo he uma chave da tabela
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_ainsumo")
	@Column(name="cd_aplicacao")
	
			private Integer cd_aplicacao;
			private String nome;
			
			public Integer getCd_aplicacao() {
				return cd_aplicacao;
			}
			public void setCd_aplicacao(Integer cd_aplicacao) {
				this.cd_aplicacao = cd_aplicacao;
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
						+ ((cd_aplicacao == null) ? 0 : cd_aplicacao.hashCode());
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
				AplicacaoInsumo other = (AplicacaoInsumo) obj;
				if (cd_aplicacao == null) {
					if (other.cd_aplicacao != null)
						return false;
				} else if (!cd_aplicacao.equals(other.cd_aplicacao))
					return false;
				return true;
			}



}
