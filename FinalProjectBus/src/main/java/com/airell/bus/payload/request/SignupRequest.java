package com.airell.bus.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

/*
 * Kelas publik untuk menangani Request Signup
 */
public class SignupRequest {
	/*
	 * Tidak boleh kosong,
	 * Minimal 3 karakter dan maksimal 50 karakter
	 */
	@NotBlank
	@Size(min = 3, max = 50)
	private String username;
	
	/*
	 * Tidak boleh kosong,
	 * Maksimal 50 karakter, dan
	 * Data merupakan data dengan tag Email
	 */
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	/*
	 * Tidak boleh kosong, dan
	 * Maksimal 120 karakter
	 */
	@NotBlank
	@Size(max = 120)
	private String firstName;
	
	/*
	 * Tidak boleh kosong, dan
	 * Maksimal 120 karakter
	 */
	@NotBlank
	@Size(max = 120)
	private String lastName;
	
	/*
	 * Tidak boleh kosong, dan
	 * Maksimal 120 karakter
	 */
	@NotBlank
	@Size(max = 120)
	private String mobileNumber;
	private Set<String> role;
	
	/*
	 * Tidak boleh kosong,
	 * Minimal 6 karakter dan maksimal 40 karakter
	 */
	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
}
