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
import com.airell.bus.payload.request.BusRequest;
import com.airell.bus.payload.response.MessageResponse;
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
	 * Get Mapping shortcut baca data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	public ResponseEntity<?> getAllBuses() {
		return ResponseEntity.ok(busRepository.findAll());
	}
	
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
	
	/*
	 * Put Mapping shortcut ubah data agar didefinisikan tambahan ./{id}
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateBus(@PathVariable(value = "id") Long id,
			@Valid @RequestBody BusRequest busDetail) {
		Bus bus = busRepository.findById(id).get();
		Agency agency = agencyRepository.findById(busDetail.getAgencyId()).get();
		if (bus == null) {
			return ResponseEntity.notFound().build();
		}
		bus.setCode(busDetail.getCode());
		bus.setCapacity(busDetail.getCapacity());
		bus.setMake(busDetail.getMake());
		bus.setAgency(agency);

		Bus updatedBus = busRepository.save(bus);

		return ResponseEntity.ok(new MessageResponse<Bus>(true, "Data berhasil diubah.", updatedBus));
	}
	
	/*
	 * Delete Mapping shortcut hapus data agar didefinisikan tambahan ./{id}
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteBus(@PathVariable(value = "id") Long id) {
		String result = "";
		try {
			busRepository.findById(id).get();
			result = "Data dengan ID: " + id + " berhasil dihapus.";
			busRepository.deleteById(id);

			return ResponseEntity.ok(new MessageResponse<Bus>(true, result));
		} catch (Exception e) {
			result = "Data dengan ID: " + id + " tidak ditemukan.";
			
			return ResponseEntity.ok(new MessageResponse<Bus>(false, result));
		}
	}
}
