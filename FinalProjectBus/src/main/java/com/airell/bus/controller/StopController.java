package com.airell.bus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.airell.bus.models.Stop;
import com.airell.bus.repository.StopRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/*
 * Cross Origin melarang penggunaan resource di luar penggunaan yang seharusnya
 * Rest Controller untuk membuat Restful Web Services
 * Request Mapping memuat mapping spesifik untuk web
 * Request di sini didefinisikan pada ./api/v1/stop
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/stop")
public class StopController {
	/*
	 * Dihubungkan untuk kontrolernya
	 * Dihuhungkan dengan repository Stop
	 */
	@Autowired
	StopRepository stopRepository;

	/*
	 * Get Mapping shortcut baca data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> getAllStops() {
		return ResponseEntity.ok(stopRepository.findAll());
	}

	/*
	 * Post Mapping shortcut buat data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> addStop(@Valid @RequestBody Stop stop) {
		return ResponseEntity.ok(stopRepository.save(stop));
	}

}
