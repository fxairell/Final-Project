package com.airell.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airell.bus.models.Stop;
import com.airell.bus.models.Trip;

/*
 * Ini adalah tag Repository
 * Merupakan file repository dari tabel Trip
 */
@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
	List<Trip> findAllBySourceStopAndDestStop(Stop sourceStop, Stop destStop);
	List<Trip> findByFare(Integer fare);
	List<Trip> findByJourneyTime (String journeyTime);
	List<Trip> findBySourceStop (String sourceStop);
	List<Trip> findByDestStop (String destStop);
	List<Trip> findByBus (String bus);
	List<Trip> findByAgency (String agency);
	
	/*
	 * Bahasa native Query untuk pemanggilan tabel berfilter
	 * Filter menggunakan id dari tabel Stop
	 */
	@Query(value = "SELECT DISTINCT * FROM trip WHERE source_stop_id = :sourceStop AND dest_stop_id = :destStop", nativeQuery = true)
	List<Trip> findTripsByStops(String sourceStop, String destStop);
	
	/*
	 * Bahasa native Query untuk pemanggilan tabel berfilter
	 * Filter menggunakan id dari tabel Agency
	 */
	@Query(value = "SELECT * FROM trip WHERE agency_id = :id", nativeQuery = true)
	List<Trip> findByAgencyId(Long id);
}
