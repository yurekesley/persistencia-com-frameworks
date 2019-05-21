package br.com.yurekesley.persistencia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "owners")
public @Data class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="FIRST_NAME", length = 30)
	private String firstName;

	@Column(length = 30)
	private String lastName;

	@Column(length = 255)
	private String address;

	@Column(length = 80)
	private String city;

	@Column(length = 20)
	private String teleophone;

}
