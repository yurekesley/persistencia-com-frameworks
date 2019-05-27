package br.com.yurekesley.persistencia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.yurekesley.persistencia.Modelo;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "TBL_AGENCIAS")
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Cliente.class)
public class Agencia extends Modelo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "agencias_id_seq", sequenceName = "agencias_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agencias_id_seq")
	private Long id;	

	@Column
	private String codigo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "agencia", cascade = CascadeType.PERSIST)
	private List<Cliente> clientes;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}



}
