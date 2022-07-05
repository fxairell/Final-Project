package com.airell.bus.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/*
 * Kelas publik untuk menangani Request Bus
 */
public class BusRequest {
	/*
	 * Api Model Property untuk memberikan sifat spesifik hidden 
	 */
	@ApiModelProperty(hidden = true)
	private Long id;
	private String code;
	private int capacity;
	private String make;
	
	/*
	 * Api Model Property untuk memberikan sifat spesifik hidden 
	 */
	@ApiModelProperty(hidden = true)
	private long agencyId;

	public BusRequest() {
	}

	public BusRequest(Long id, @NotBlank String code, @NotNull int capacity,
			@NotBlank String make, @NotBlank long agencyId) {
		this.id = id;
		this.capacity = capacity;
		this.code = code;
		this.make = make;
		this.agencyId = agencyId;
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

	public long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(long agencyId) {
		this.agencyId = agencyId;
	}
}
