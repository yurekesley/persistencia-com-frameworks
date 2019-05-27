package br.com.yurekesley.persistencia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.yurekesley.persistencia.Modelo;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "TBL_CLIENTES")
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Cliente.class)
public class Cliente extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "clientes_id_seq", sequenceName = "clientes_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_id_seq")
	private Long id;

	@Column(length = 255, nullable = false)
	private String nome;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TipoCliente tipo;
	
	@ManyToOne
	@JoinColumn(name = "agencia_id", nullable = false)
	private Agencia agencia;
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public TipoCliente getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}
	
	public Agencia getAgencia() {
		return agencia;
	}
	
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}


}