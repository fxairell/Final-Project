package com.airell.bus.models;

import javax.persistence.*;

/*
 * Ini adalah tag Entity
 * Merupakan file model dari tabel Trip
 * Gunanya adalah kerangka dari data tabel Trip
 * Diberi nama trip untuk pemanggilan tabel
 */
@Entity
@Table(name = "trip")
public class Trip {
	/*
	 * Ini adalah generasi dan pemberian simbol id
	 * Untuk deklarasi private objek dari kerangka data
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int fare;
	private int journeyTime;
	
	/*
	 * Membuat relasi ManyToOne dengan data tabel Stop
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "source_stop_id")
	private Stop sourceStop;
	
	/*
	 * Membuat relasi ManyToOne dengan data tabel Stop
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dest_stop_id")
	private Stop destStop;
	
	/*
	 * Membuat relasi ManyToOne dengan data tabel Bus
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bus_id")
	private Bus bus;
	
	/*
	 * Membuat relasi ManyToOne dengan data tabel Agency
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agency_id")
	private Agency agency;

	public Trip() {
	}

	public Trip(int fare, int journeyTime, Stop sourceStop, Stop destStop, Bus bus, Agency agency) {
		this.fare = fare;
		this.journeyTime = journeyTime;
		this.sourceStop = sourceStop;
		this.destStop = destStop;
		this.bus = bus;
		this.agency = agency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public int getJourneyTime() {
		return journeyTime;
	}

	public void setJourneyTime(int journeyTime) {
		this.journeyTime = journeyTime;
	}

	public Stop getSourceStop() {
		return sourceStop;
	}

	public void setSourceStop(Stop sourceStop) {
		this.sourceStop = sourceStop;
	}

	public Stop getDestStop() {
		return destStop;
	}

	public void setDestStop(Stop destStop) {
		this.destStop = destStop;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}
}
