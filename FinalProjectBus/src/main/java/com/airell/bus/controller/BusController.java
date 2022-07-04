package com.airell.bus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.airell.bus.models.Agency;
import com.airell.bus.models.Bus;
import com.airell.bus.payload.request.BusCustomRequest;
import com.airell.bus.repository.AgencyRepository;
import com.airell.bus.repository.BusRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/*
 * Cross Origin melarang penggunaan resource di luar penggunaan yang seharusnya
 * Rest Controller untuk membuat Restful Web Services
 * Request Mapping memuat mapping spesifik untuk web
 * Request di sini didefinisikan pada ./api/v1/bus
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
	/*
	 * Dihubungkan untuk kontrolernya
	 * Dihuhungkan dengan repository Agency dan Bus
	 */
	@Autowired
	BusRepository busRepository;

	@Autowired
	AgencyRepository agencyRepository;

	/*
	 * Get Mapping shortcut baca data agar didefinisikan tambahan ./{id}
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getBusByAgencyId(@PathVariable(value = "id") Long id) {
		List<Bus> bus = busRepository.findByAgencyId(id);
		
		return ResponseEntity.ok(bus);
	}

	/*
	 * Post Mapping shortcut buat data agar didefinisikan tambahan ./{id}
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@PostMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addBusByUserId(@PathVariable(value = "id") Long id,
			@Valid @RequestBody BusCustomRequest busCustomRequest) {
		Agency agency = agencyRepository.findByOwnerUser(id);
		Bus bus = new Bus(busCustomRequest.getCode(), busCustomRequest.getCapacity(), busCustomRequest.getMake(),
				agency);
		
		return ResponseEntity.ok(busRepository.save(bus));
	}
}
