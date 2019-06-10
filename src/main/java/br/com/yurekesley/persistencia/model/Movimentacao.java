package br.com.yurekesley.persistencia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.yurekesley.persistencia.Modelo;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "TBL_MOVIMENTACAO")
@EqualsAndHashCode(callSuper = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Movimentacao.class)
public class Movimentacao extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "movimentacao_id_seq", sequenceName = "movimentacao_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movimentacao_id_seq")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_movimentacao")
	private TipoMovimentacao tipoMovimentacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conta_origem_id")
	@JsonIgnore
	private ContaCorrente contaOrigem;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conta_destino_id")
	@JsonIgnore
	private ContaCorrente contaDestino;

	private double valor;

	public Movimentacao() {
	}

	public Movimentacao(TipoMovimentacao tipoMovimentacao, ContaCorrente contaOrigem, ContaCorrente contaDestino,
			double valor) {
		this.tipoMovimentacao = tipoMovimentacao;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
	}

	public Movimentacao(TipoMovimentacao tipoMovimentacao, ContaCorrente contaOrigem, double valor) {
		this.tipoMovimentacao = tipoMovimentacao;
		this.contaOrigem = contaOrigem;
		this.valor = valor;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public ContaCorrente getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(ContaCorrente contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public ContaCorrente getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(ContaCorrente contaDestino) {
		this.contaDestino = contaDestino;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "NOME: "+ this.getContaOrigem().getCliente().getNome() +
			    " TIPO: "+ this.tipoMovimentacao + 
			    " VALOR: "+ this.getValor() + "\n";
	}

}