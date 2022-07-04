package com.airell.bus.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/*
 * Kelas publik untuk menangani Request Ticket
 */
public class TicketRequest {
	/*
	 * Api Model Property untuk memberikan sifat spesifik hidden 
	 */
	@ApiModelProperty(hidden = true)
	private Long id;
	private int seatNumber;
	private Boolean cancellable;
	private String journeyDate;
	private Long passegerId;
	private Long tripScheduleId;

	public TicketRequest() {
	}

	public TicketRequest(Long id, @NotNull Boolean cancellable, @NotBlank String journeyDate,
			@NotNull int seatNumber, @NotBlank Long passegerId, @NotBlank Long tripScheduleId) {
		this.id = id;
		this.cancellable = cancellable;
		this.journeyDate = journeyDate;
		this.seatNumber = seatNumber;
		this.passegerId = passegerId;
		this.tripScheduleId = tripScheduleId;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Boolean getCancellable() {
		return cancellable;
	}

	public void setCancellable(Boolean cancellable) {
		this.cancellable = cancellable;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}

	public Long getPassegerId() {
		return passegerId;
	}

	public void setPassegerId(Long passegerId) {
		this.passegerId = passegerId;
	}

	public Long getTripScheduleId() {
		return tripScheduleId;
	}

	public void setTripScheduleId(Long tripScheduleId) {
		this.tripScheduleId = tripScheduleId;
	}
}