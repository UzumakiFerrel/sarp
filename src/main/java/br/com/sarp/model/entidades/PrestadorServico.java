package br.com.sarp.model.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class PrestadorServico {

	@Id
	@SequenceGenerator(name = "seq_prestador")
	@GeneratedValue
	private Integer cd_prestador;
	private String nome;
	private String empresa;
	private String documento;
	
	
	public Integer getCd_prestador() {
		return cd_prestador;
	}
	public void setCd_prestador(Integer cd_prestador) {
		this.cd_prestador = cd_prestador;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
}
