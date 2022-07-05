package com.airell.bus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.airell.bus.models.Agency;
import com.airell.bus.models.Bus;
import com.airell.bus.models.Stop;
import com.airell.bus.models.Trip;
import com.airell.bus.payload.request.TripRequest;
import com.airell.bus.repository.AgencyRepository;
import com.airell.bus.repository.BusRepository;
import com.airell.bus.repository.StopRepository;
import com.airell.bus.repository.TripRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/*
 * Cross Origin melarang penggunaan resource di luar penggunaan yang seharusnya
 * Rest Controller untuk membuat Restful Web Services
 * Request Mapping memuat mapping spesifik untuk web
 * Request di sini didefinisikan pada ./api/v1/trip
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/trip")
public class TripController {
	/*
	 * Dihubungkan untuk kontrolernya
	 * Dihuhungkan dengan repository Agency, Bus, Stop, dan Trip
	 */
	@Autowired
	TripRepository tripRepository;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	StopRepository stopRepository;

	/*
	 * Post Mapping shortcut buat data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@PostMapping("/")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTrip(@Valid @RequestBody TripRequest tripRequest) {
		Agency agency = agencyRepository.findById(tripRequest.getAgencyId()).get();
		Bus bus = busRepository.findById(tripRequest.getBusId()).get();
		Stop sourceStop = stopRepository.findById(tripRequest.getSourceStopId()).get();
		Stop destStop = stopRepository.findById(tripRequest.getDestStopId()).get();
		Trip trip = new Trip(tripRequest.getFare(), tripRequest.getJourneyTime(), sourceStop, destStop, bus, agency);
		
		return ResponseEntity.ok(tripRepository.save(trip));
	}

	/*
	 * Get Mapping shortcut baca data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> getAllTrips() {
		return ResponseEntity.ok(tripRepository.findAll());
	}
	
	/*
	 * Get Mapping shortcut baca data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getTripByAgencyId(@PathVariable(value = "id") Long id) {
		List<Trip> trip = tripRepository.findByAgencyId(id);
		
		return ResponseEntity.ok(trip);
	}
}
