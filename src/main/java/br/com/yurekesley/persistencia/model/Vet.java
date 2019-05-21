
package br.com.yurekesley.persistencia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "vets")
@Data
public class Vet {

	protected Vet() { }
	
	public Vet(String firshName, String lastName) {
		this.firshName = firshName;
		this.lastName = lastName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String firshName;

	@Column
	private String lastName;

}
