package com.airell.bus.payload.request;

import io.swagger.annotations.ApiModelProperty;

/*
 * Kelas publik untuk menangani Request Password User
 */
public class UserPasswordRequest {
	/*
	 * Api Model Property untuk memberikan sifat spesifik hidden 
	 */
	@ApiModelProperty(hidden = true)
	private Long id;
	private String password;
	
	public UserPasswordRequest() {
	}

	public UserPasswordRequest(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
