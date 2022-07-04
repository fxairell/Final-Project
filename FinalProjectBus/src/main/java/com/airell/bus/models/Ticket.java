package com.airell.bus.models;

import javax.persistence.*;

/*
 * Ini adalah tag Entity
 * Merupakan file model dari tabel Ticket
 * Gunanya adalah kerangka dari data tabel Ticket
 * Diberi nama ticket untuk pemanggilan tabel
 */
@Entity
@Table(name = "ticket")
public class Ticket {
	/*
	 * Ini adalah generasi dan pemberian simbol id
	 * Untuk deklarasi private objek dari kerangka data
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int seatNumber;
	private Boolean cancellable;
	private String journeyDate;
	
	/*
	 * Membuat relasi ManyToOne dengan data tabel Trip Schedule
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_schedule_id")
	private TripSchedule tripSchedule;
	
	/*
	 * Membuat relasi ManyToOne dengan data tabel User
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User passenger;

	public Ticket() {
	}

	public Ticket(int seatNumber, Boolean cancellable, String journeyDate,
			User passenger, TripSchedule tripSchedule) {
		this.seatNumber = seatNumber;
		this.cancellable = cancellable;
		this.journeyDate = journeyDate;
		this.passenger = passenger;
		this.tripSchedule = tripSchedule;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TripSchedule getTripSchedule() {
		return tripSchedule;
	}

	public void setTripSchedule(TripSchedule tripSchedule) {
		this.tripSchedule = tripSchedule;
	}

	public User getPassenger() {
		return passenger;
	}

	public void setPassenger(User passenger) {
		this.passenger = passenger;
	}
}
