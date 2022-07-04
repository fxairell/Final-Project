package com.airell.bus.models;

import javax.persistence.*;

/*
 * Ini adalah tag Entity
 * Merupakan file model dari tabel Roles
 * Gunanya adalah kerangka dari data tabel Roles
 * Diberi nama roles untuk pemanggilan tabel
 */
@Entity
@Table(name = "roles")
public class Role {
	/*
	 * Ini adalah generasi dan pemberian simbol id
	 * Untuk deklarasi private objek dari kerangka data
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/*
	 * Pemanggilan enumerasi ERole untuk Role
	 */
	@Enumerated(EnumType.STRING)
	@Column(length=20)
	private ERole name;

	public Role() {	
	}
	
	public Role(ERole name) {
		this.name=name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}
