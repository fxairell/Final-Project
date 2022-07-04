package com.airell.bus.models;

import java.util.Set;

import javax.persistence.*;

/*
 * Ini adalah tag Entity
 * Merupakan file model dari tabel Trip Schedule
 * Gunanya adalah kerangka dari data tabel Trip Schedule
 * Diberi nama trip_schedule untuk pemanggilan tabel
 */
@Entity
@Table(name = "trip_schedule")
public class TripSchedule {
	/*
	 * Ini adalah generasi dan pemberian simbol id
	 * Untuk deklarasi private objek dari kerangka data
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 * Membuat relasi OneToOne dengan data tabel Trip
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_id")
	private Trip tripDetail;
	
	/*
	 * Membuat relasi OneToOne dengan data tabel Ticket
	 */
	@OneToMany(mappedBy = "tripSchedule", cascade = CascadeType.ALL)
	private Set<Ticket> ticketsSold;
	
	private String tripDate;
	private int availableSeats;
	
	public TripSchedule() {
	}

	public TripSchedule(String tripDate, int availableSeats, Trip tripDetail) {
		this.tripDate = tripDate;
		this.availableSeats = availableSeats;
		this.tripDetail = tripDetail;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Trip getTripDetail() {
		return tripDetail;
	}
	
	public void setTripDetail(Trip tripDetail) {
		this.tripDetail = tripDetail;
	}
	
	public Set<Ticket> getTicketsSold() {
		return ticketsSold;
	}
	
	public void setTicketsSold(Set<Ticket> ticketsSold) {
		this.ticketsSold = ticketsSold;
	}
	
	public String getTripDate() {
		return tripDate;
	}
	
	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}
	
	public int getAvailableSeats() {
		return availableSeats;
	}
	
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
}
