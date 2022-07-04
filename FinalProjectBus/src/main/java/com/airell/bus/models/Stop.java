package com.airell.bus.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/*
 * Ini adalah tag Entity
 * Merupakan file model dari tabel Stop
 * Gunanya adalah kerangka dari data tabel Stop
 * Diberi nama stop untuk pemanggilan tabel
 */
@Entity
@Table(name = "stop")
public class Stop {
	/*
	 * Ini adalah generasi dan pemberian simbol id
	 * Untuk deklarasi private objek dari kerangka data
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String name;
	private String detail;
	
	public Stop() {
	}

	public Stop(@NotBlank String code, @NotBlank String name, @NotBlank String detail) {
		this.code = code;
		this.name = name;
		this.detail = detail;
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
	
	public String getDetail() {
		return detail;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
