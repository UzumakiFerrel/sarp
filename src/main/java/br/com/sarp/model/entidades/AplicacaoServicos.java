package br.com.sarp.model.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class AplicacaoServicos {
	// Informa que este campo he uma chave da tabela

	@Id
	// defini o campo id como sequencial
	@SequenceGenerator(name = "seq_apliserv")
	@GeneratedValue
	
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

}
