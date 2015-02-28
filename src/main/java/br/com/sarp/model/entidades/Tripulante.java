package br.com.sarp.model.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tripulante {

	@Id
	private Integer iD;

	@JoinColumn
	@ManyToOne
	private Usuario usuario = new Usuario();

	private String cir;

	@JoinColumn
	@ManyToOne
	private CategoriaMaritimo categoriacir = new CategoriaMaritimo(); // categoria
																		// na
																		// cir

	@JoinColumn
	@ManyToOne
	private Tripulacao tripulacao = new Tripulacao();

	
	@JoinColumn
	@ManyToOne
	private FuncaoMaritimo fcmaritimo = new FuncaoMaritimo();
	
	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {

		this.iD = iD;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCir() {
		return cir;
	}

	public void setCir(String cir) {
		this.cir = cir;
	}

	public CategoriaMaritimo getCategoriacir() {
		return categoriacir;
	}

	public void setCategoriacir(CategoriaMaritimo categoriacir) {
		this.categoriacir = categoriacir;
	}

	public Tripulacao getTripulacao() {
		return tripulacao;
	}

	public void setTripulacao(Tripulacao tripulacao) {
		this.tripulacao = tripulacao;
	}

	
	public FuncaoMaritimo getFcmaritimo() {
		return fcmaritimo;
	}

	public void setFcmaritimo(FuncaoMaritimo fcmaritimo) {
		this.fcmaritimo = fcmaritimo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iD == null) ? 0 : iD.hashCode());
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
		Tripulante other = (Tripulante) obj;
		if (iD == null) {
			if (other.iD != null)
				return false;
		} else if (!iD.equals(other.iD))
			return false;
		return true;
	}

}
