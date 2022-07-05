package com.airell.bus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.airell.bus.models.Trip;
import com.airell.bus.models.TripSchedule;
import com.airell.bus.payload.request.GetTripScheduleRequest;
import com.airell.bus.payload.response.MessageResponse;
import com.airell.bus.repository.TicketRepository;
import com.airell.bus.repository.TripRepository;
import com.airell.bus.repository.TripScheduleRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/*
 * Cross Origin melarang penggunaan resource di luar penggunaan yang seharusnya
 * Rest Controller untuk membuat Restful Web Services
 * Request Mapping memuat mapping spesifik untuk web
 * Request di sini didefinisikan pada ./api/v1/trip_schedule
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/trip_schedule")
public class TripScheduleController {
	/*
	 * Dihubungkan untuk kontrolernya
	 * Dihuhungkan dengan repository Agency dan Bus
	 */
	@Autowired
	TripScheduleRepository tripScheduleRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TripRepository tripRepository;

	/*
	 * Get Mapping shortcut baca data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> getAllTripSchedules() {
		return ResponseEntity.ok(tripScheduleRepository.findAll());
	}

	/*
	 * Post Mapping shortcut buat data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@PostMapping("/")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTripSchedule(@Valid @RequestBody GetTripScheduleRequest tripScheduleRequest) {
		Trip trip = tripRepository.findById(tripScheduleRequest.getTrip_detail()).get();
		TripSchedule trip_schedule = new TripSchedule(tripScheduleRequest.getTripDate(),
				tripScheduleRequest.getAvailable_seats(), trip);
		return ResponseEntity.ok(new MessageResponse<TripSchedule>(true, "Data berhasil ditambahkan.",
				tripScheduleRepository.save(trip_schedule)));
	}
}
