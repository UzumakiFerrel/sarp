package br.com.sarp.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="manobras")
@SequenceGenerator(name = "seq_manobras" , sequenceName="seq_manobras" , initialValue=1)
public class Manobras {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO,generator="seq_manobras")
		@Column(name="id")

		private Integer id;
		
		private Integer cd_manobras;  //numero da manobra por rebocador

		
		private Integer dia , mes , ano,tempoManobra,vlrManobra;
		private String inicio,fim,observacao;

		@JoinColumn
		@ManyToOne
		private Rebocador rebocador;
		
		@JoinColumn
		@ManyToOne
		private Rebocador rbAux01;
		
		@JoinColumn
		@ManyToOne
		private Rebocador rbAux02;
		
		@JoinColumn
		@ManyToOne
		private Rebocador rbAux03;
		
		@JoinColumn
		@ManyToOne
		private Rebocador rbAux04;
		
		@JoinColumn
		@ManyToOne
		private Locais locais;
		
		@JoinColumn
		@ManyToOne
		private Pratico pratico;
		
		@JoinColumn
		@ManyToOne
		private TipoManobras tipoManobras= new TipoManobras();

		@JoinColumn
		@ManyToOne
		private Embarcacoes embarcacao = new Embarcacoes();
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getCd_manobras() {
			return cd_manobras;
		}

		public void setCd_manobras(Integer cd_manobras) {
			this.cd_manobras = cd_manobras;
		}

		public Rebocador getRebocador() {
			return rebocador;
		}

		public void setRebocador(Rebocador rebocador) {
			this.rebocador = rebocador;
		}

		public Locais getLocais() {
			return locais;
		}

		public void setLocais(Locais locais) {
			this.locais = locais;
		}

		public Pratico getPratico() {
			return pratico;
		}

		public void setPratico(Pratico pratico) {
			this.pratico = pratico;
		}

		public TipoManobras getTipoManobras() {
			return tipoManobras;
		}

		public void setTipoManobras(TipoManobras tipoManobras) {
			this.tipoManobras = tipoManobras;
		}
		
		public Rebocador getRbAux01() {
			return rbAux01;
		}

		public void setRbAux01(Rebocador rbAux01) {
			this.rbAux01 = rbAux01;
		}

		public Rebocador getRbAux02() {
			return rbAux02;
		}

		public void setRbAux02(Rebocador rbAux02) {
			this.rbAux02 = rbAux02;
		}

		public Rebocador getRbAux03() {
			return rbAux03;
		}

		public void setRbAux03(Rebocador rbAux03) {
			this.rbAux03 = rbAux03;
		}

		public Rebocador getRbAux04() {
			return rbAux04;
		}

		public void setRbAux04(Rebocador rbAux04) {
			this.rbAux04 = rbAux04;
		}

		public Embarcacoes getEmbarcacao() {
			return embarcacao;
		}

		public void setEmbarcacao(Embarcacoes embarcacao) {
			this.embarcacao = embarcacao;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((cd_manobras == null) ? 0 : cd_manobras.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			Manobras other = (Manobras) obj;
			if (cd_manobras == null) {
				if (other.cd_manobras != null)
					return false;
			} else if (!cd_manobras.equals(other.cd_manobras))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		public String getInicio() {
			return inicio;
		}

		public void setInicio(String inicio) {
			this.inicio = inicio;
		}

		public String getFim() {
			return fim;
		}

		public void setFim(String fim) {
			this.fim = fim;
		}

		public Integer getMes() {
			return mes;
		}

		public void setMes(Integer mes) {
			this.mes = mes;
		}

		public Integer getDia() {
			return dia;
		}

		public void setDia(Integer dia) {
			this.dia = dia;
		}

		public Integer getAno() {
			return ano;
		}

		public void setAno(Integer ano) {
			this.ano = ano;
		}

		public String getObservacao() {
			return observacao;
		}

		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}

		public Integer getTempoManobra() {
			return tempoManobra;
		}

		public void setTempoManobra(Integer tempoManobra) {
			this.tempoManobra = tempoManobra;
		}

		public Integer getVlrManobra() {
			return vlrManobra;
		}

		public void setVlrManobra(Integer vlrManobra) {
			this.vlrManobra = vlrManobra;
		}


}
