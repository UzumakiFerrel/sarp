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
@Table(name = "historicopComando")
@SequenceGenerator(name = "seq_pComando", sequenceName = "seq_pComando", initialValue = 1)
public class HistoricoPassagemComando {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pComando")
	@Column(name = "cd_hpComando")
	
	private Integer cd_hpComando;
	
	@JoinColumn
	@ManyToOne
	private PassagemComando pComando = new PassagemComando();
	
	
	
	
}
