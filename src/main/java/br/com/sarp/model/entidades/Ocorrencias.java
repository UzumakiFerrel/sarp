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
@Table(name = "ocorrencias")
@SequenceGenerator(name = "seq_ocorrencia", sequenceName = "seq_ocorrencia", initialValue = 1)
public class Ocorrencias {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ocorrencia")
	@Column(name = "cd_ocorrencias")
		private Integer cd_ocorrencias;
		private String descricao;	
		private String abertura;
		private String solucao;
		
		@ManyToOne
		@JoinColumn
		private TipoHistoricoRebocador tpocorrencia;
		 
		@ManyToOne
		@JoinColumn
		private Rebocador rebocador;
		
		@ManyToOne
		@JoinColumn
		private Tripulante tripulante;
		
		@ManyToOne
		@JoinColumn
		private SituacaoRequisicao sitOcorrencia;
		
		@ManyToOne
		@JoinColumn
		private Secao secao;

		@ManyToOne
		@JoinColumn
		private Solicitacoes solicitacao;
		
		
		public Integer getCd_Ocorrencias() {
			return cd_ocorrencias;
		}

		public void setCd_Ocorrencias(Integer cd_Ocorrencias) {
			this.cd_ocorrencias = cd_Ocorrencias;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getAbertura() {
			return abertura;
		}

		public void setAbertura(String abertura) {
			this.abertura = abertura;
		}

		public String getSolucao() {
			return solucao;
		}

		public void setSolucao(String solucao) {
			this.solucao = solucao;
		}

		public TipoHistoricoRebocador getTpocorrencia() {
			return tpocorrencia;
		}

		public void setTpocorrencia(TipoHistoricoRebocador tpocorrencia) {
			this.tpocorrencia = tpocorrencia;
		}

		public Rebocador getRebocador() {
			return rebocador;
		}

		public void setRebocador(Rebocador rebocador) {
			this.rebocador = rebocador;
		}

		public Tripulante getTripulante() {
			return tripulante;
		}

		public void setTripulante(Tripulante tripulante) {
			this.tripulante = tripulante;
		}

		public SituacaoRequisicao getSitOcorrencia() {
			return sitOcorrencia;
		}

		public void setSitOcorrencia(SituacaoRequisicao sitOcorrencia) {
			this.sitOcorrencia = sitOcorrencia;
		}

		public Secao getSecao() {
			return secao;
		}

		public void setSecao(Secao secao) {
			this.secao = secao;
		}

		public Solicitacoes getSolicitacao() {
			return solicitacao;
		}

		public void setSolicitacao(Solicitacoes solicitacao) {
			this.solicitacao = solicitacao;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((cd_ocorrencias == null) ? 0 : cd_ocorrencias.hashCode());
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
			Ocorrencias other = (Ocorrencias) obj;
			if (cd_ocorrencias == null) {
				if (other.cd_ocorrencias != null)
					return false;
			} else if (!cd_ocorrencias.equals(other.cd_ocorrencias))
				return false;
			return true;
		}
		
}
