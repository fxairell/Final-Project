package com.airell.bus.models;

import javax.persistence.*;

/*
 * Ini adalah tag Entity
 * Merupakan file model dari tabel Bus
 * Gunanya adalah kerangka dari data tabel Bus
 * Diberi nama bus untuk pemanggilan tabel
 */
@Entity
@Table(name="bus")
public class Bus {
	/*
	 * Ini adalah generasi dan pemberian simbol id
	 * Untuk deklarasi private objek dari kerangka data
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private int capacity;
	private String make;
	
	/*
	 * Membuat relasi ManyToOne dengan data tabel Agency
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agency_id")
	private Agency agency;
	
	public Bus() {
    }

	public Bus(String code, int capacity, String make, Agency agency) {
		this.code = code;
		this.capacity = capacity;
		this.make = make;
		this.agency = agency;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}
}
