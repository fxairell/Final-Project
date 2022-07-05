package com.airell.bus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.airell.bus.models.Ticket;
import com.airell.bus.models.TripSchedule;
import com.airell.bus.models.User;
import com.airell.bus.payload.request.TicketRequest;
import com.airell.bus.payload.response.MessageResponse;
import com.airell.bus.repository.TicketRepository;
import com.airell.bus.repository.TripScheduleRepository;
import com.airell.bus.repository.UserRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/*
 * Cross Origin melarang penggunaan resource di luar penggunaan yang seharusnya
 * Rest Controller untuk membuat Restful Web Services
 * Request Mapping memuat mapping spesifik untuk web
 * Request di sini didefinisikan pada ./api/v1/ticket
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
	/*
	 * Dihubungkan untuk kontrolernya
	 * Dihuhungkan dengan repository Ticket, User dan Trip Schedule
	 */
	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TripScheduleRepository tripScheduleRepository;

	/*
	 * Get Mapping shortcut baca data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> getAllTickets() {
		return ResponseEntity.ok(ticketRepository.findAll());
	}

	/*
	 * Post Mapping shortcut buat data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@PostMapping("/")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTicket(@Valid @RequestBody TicketRequest ticketRequest) {
		User user = userRepository.findById(ticketRequest.getPassegerId()).get();
		TripSchedule tripSchedule = tripScheduleRepository.findById(ticketRequest.getTripScheduleId()).get();
		Ticket ticket = new Ticket(ticketRequest.getSeatNumber(), ticketRequest.getCancellable(),
				ticketRequest.getJourneyDate(), user, tripSchedule);
		ticketRepository.save(ticket);
		return ResponseEntity.ok(new MessageResponse<Ticket>(true, "Data berhasil ditambahkan."));
	}
}
