package com.airell.bus.payload.request;

import javax.validation.constraints.NotBlank;

/*
 * Kelas publik untuk menangani Request Login
 */
public class LoginRequest {
	/*
	 * Tidak boleh kosong
	 */
	@NotBlank
	private String username;
	
	/*
	 * Tidak boleh kosong
	 */
	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
