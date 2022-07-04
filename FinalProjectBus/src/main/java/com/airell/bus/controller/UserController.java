package com.airell.bus.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.airell.bus.models.Agency;
import com.airell.bus.models.ERole;
import com.airell.bus.models.Role;
import com.airell.bus.models.User;
import com.airell.bus.payload.request.SignupCustomRequest;
import com.airell.bus.payload.request.UserCustomRequest;
import com.airell.bus.payload.request.UserPasswordRequest;
import com.airell.bus.payload.response.MessageResponse;
import com.airell.bus.repository.AgencyRepository;
import com.airell.bus.repository.RoleRepository;
import com.airell.bus.repository.UserRepository;
import com.airell.bus.security.jwt.JwtUtils;

import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/*
 * Cross Origin melarang penggunaan resource di luar penggunaan yang seharusnya
 * Rest Controller untuk membuat Restful Web Services
 * Request Mapping memuat mapping spesifik untuk web
 * Request di sini didefinisikan pada ./api/v1/user
 */
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET })
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	/*
	 * Dihubungkan untuk kontrolernya
	 * Dihuhungkan dengan Authentication Manager,
	 * Password Encoder, JWT Utils,
	 * repository Agency, User, dan Role
	 */
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	JwtUtils jwtUtils;

	/*
	 * Post Mapping shortcut buat data agar didefinisikan tambahan ./signup
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupCustomRequest signupCustomRequest) {
		// Cek data apakah sudah ada pada database
		if (userRepository.existsByUsername(signupCustomRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signupCustomRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Buat akun user baru
		User user = new User(signupCustomRequest.getFirstName(), signupCustomRequest.getLastName(),
				signupCustomRequest.getMobileNumber(), signupCustomRequest.getUsername(),
				signupCustomRequest.getEmail(), encoder.encode(signupCustomRequest.getPassword()));

		Set<String> strRoles = signupCustomRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		Agency agency = new Agency(signupCustomRequest.getCode(), signupCustomRequest.getDetails(),
				signupCustomRequest.getName(), user);
		agencyRepository.save(agency);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	/*
	 * Put Mapping shortcut ubah data agar didefinisikan tambahan ./{id}
	 */
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long id,
			@Valid @RequestBody UserCustomRequest userCustomRequest) {
		User user = userRepository.findById(id).get();
		
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		
		user.setFirstName(userCustomRequest.getFirstName());
		user.setLastName(userCustomRequest.getLastName());
		user.setMobileNumber(userCustomRequest.getMobileNumber());

		User updatedUser = userRepository.save(user);

		return ResponseEntity.ok(updatedUser);
	}

	/*
	 * Put Mapping shortcut ubah password agar didefinisikan tambahan ./password/{id}
	 */
	@PutMapping("/password/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updatePassword(@PathVariable(value = "id") Long id,
			@Valid @RequestBody UserPasswordRequest userPasswordRequest) {
		User user = userRepository.findById(id).get();
		
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		user.setPassword(encoder.encode(userPasswordRequest.getPassword()));
		User updatedUser = userRepository.save(user);

		return ResponseEntity.ok(updatedUser);
	}
}
