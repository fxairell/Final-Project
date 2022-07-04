package com.airell.bus.models;

import java.util.Set;

import javax.persistence.*;

/*
 * Ini adalah tag Entity
 * Merupakan file model dari tabel Agency
 * Gunanya adalah kerangka dari data tabel Agency
 * Diberi nama agency untuk pemanggilan tabel
 */
@Entity
@Table(name="agency")
public class Agency {
	/*
	 * Ini adalah generasi dan pemberian simbol id
	 * Untuk deklarasi private objek dari kerangka data
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String name;
	private String details;
	
	/*
	 * Membuat relasi ManyToOne dengan data tabel User
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_user_id")
	private User owner;
	
	/*
	 * Membuat relasi OneToMany dengan data tabel Bus
	 */
	@OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
	private Set<Bus> buses;
	
	public Agency() {
    }
	
	public Agency(String code, String name, String details, User owner) {
		this.code = code;
		this.name = name;
		this.details = details;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
