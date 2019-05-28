package br.com.yurekesley.persistencia.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.yurekesley.persistencia.Modelo;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "TBL_CONTAS_CORRENTE")
@EqualsAndHashCode(callSuper = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Cliente.class)
public class ContaCorrente extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "contas_corrente_id_seq", sequenceName = "contas_corrente_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contas_corrente_id_seq")
	private Long id;

	@Column
	private String numero;

	@Column
	private BigDecimal saldo;

	@OneToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "agencia_id", nullable = false)
	private Agencia agencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public void debitar(BigDecimal valor) throws Exception {

		if (this.saldo.compareTo(valor) == -1) {
			throw new Exception("Saldo Insuficiente !!");
		}

		this.saldo = this.saldo.subtract(valor);

	}

	public void depositar(BigDecimal valor) {
		this.saldo = this.saldo.add(valor);

	}

}
