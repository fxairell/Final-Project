package com.airell.bus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.airell.bus.models.Agency;
import com.airell.bus.models.User;
import com.airell.bus.payload.request.AgencyRequest;
import com.airell.bus.payload.response.MessageResponse;
import com.airell.bus.repository.AgencyRepository;
import com.airell.bus.repository.BusRepository;
import com.airell.bus.repository.UserRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/*
 * Cross Origin melarang penggunaan resource di luar penggunaan yang seharusnya
 * Rest Controller untuk membuat Restful Web Services
 * Request Mapping memuat mapping spesifik untuk web
 * Request di sini didefinisikan pada ./api/v1/agency
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/agency")
public class AgencyController {
	/*
	 * Dihubungkan untuk kontrolernya
	 * Dihuhungkan dengan repository Agency, User, dan Bus
	 */
	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BusRepository busRepository;

	/*
	 * Get Mapping shortcut baca data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@GetMapping("/")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll() {
		List<AgencyRequest> dataArrResult = new ArrayList<>();
		
		for (Agency dataArr : agencyRepository.findAll()) {
			dataArrResult.add(new AgencyRequest(dataArr.getId(), dataArr.getCode(), dataArr.getName(),
					dataArr.getDetails(), dataArr.getOwner().getId()));
		}
		
		return ResponseEntity.ok(new MessageResponse<AgencyRequest>(true, "Success Retrieving Data", dataArrResult));
	}
	
	/*
	 * Get Mapping shortcut baca data agar didefinisikan tambahan ./{id}
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAgencyById(@PathVariable(value = "id") Long id) {
		Agency agency = agencyRepository.findById(id).get();
		
		if (agency == null) {
			return ResponseEntity.notFound().build();
		} else {
			AgencyRequest dataResult = new AgencyRequest(agency.getId(), agency.getCode(), agency.getName(),
					agency.getDetails(), agency.getOwner().getId());
			
			return ResponseEntity.ok(new MessageResponse<AgencyRequest>(true, "Success Retrieving Data", dataResult));
		}
	}

	/*
	 * Post Mapping shortcut buat data agar didefinisikan tambahan ./
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@PostMapping("/")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addAgency(@Valid @RequestBody AgencyRequest agencyRequest) {
		User user = userRepository.findById(agencyRequest.getOwner()).get();
		Agency agency = new Agency(agencyRequest.getCode(), agencyRequest.getDetails(), agencyRequest.getName(), user);
		
		return ResponseEntity
				.ok(new MessageResponse<Agency>(true, "Success Adding Data", agencyRepository.save(agency)));
	}

	/*
	 * Put Mapping shortcut ubah data agar didefinisikan tambahan ./{id}
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateAgency(@PathVariable(value = "id") Long id,
			@Valid @RequestBody AgencyRequest agencyDetail) {
		Agency agency = agencyRepository.findById(id).get();
		User user = userRepository.findById(agencyDetail.getOwner()).get();
		
		if (agency == null) {
			return ResponseEntity.notFound().build();
		}
		
		agency.setCode(agencyDetail.getCode());
		agency.setDetails(agencyDetail.getDetails());
		agency.setName(agencyDetail.getName());
		agency.setOwner(user);

		Agency updatedAgency = agencyRepository.save(agency);

		return ResponseEntity.ok(new MessageResponse<Agency>(true, "Success Updating Data", updatedAgency));
	}

	/*
	 * Delete Mapping shortcut hapus data agar didefinisikan tambahan ./{id}
	 * Api Operation mendeskripsikan method HTTP dan memerlukan Authorization
	 * Pre Authorize mengecek apakah user punya role ADMIN
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteAgency(@PathVariable(value = "id") Long id) {
		String result = "";
		try {
			agencyRepository.findById(id).get();
			result = "Success Deleting Data with Id: " + id;
			agencyRepository.deleteById(id);

			return ResponseEntity.ok(new MessageResponse<Agency>(true, result));
		} catch (Exception e) {
			result = "Data with Id: " + id + " Not Found";
			
			return ResponseEntity.ok(new MessageResponse<Agency>(false, result));
		}
	}
}
