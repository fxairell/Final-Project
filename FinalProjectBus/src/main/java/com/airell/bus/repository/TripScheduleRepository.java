package com.airell.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airell.bus.models.TripSchedule;

/*
 * Ini adalah tag Repository
 * Merupakan file repository dari tabel Trip Schedule
 */
@Repository
public interface TripScheduleRepository extends JpaRepository<TripSchedule, Long> {
	List<TripSchedule> findAllByTripDate(String tripDate);
	List<TripSchedule> findByTripDate(String tripDate);
	
	/*
	 * Bahasa native Query untuk pemanggilan tabel berfilter
	 * Filter menggunakan trip_date dari tabel Trip Schedule
	 */
	@Query(value = "SELECT DISTINCT * FROM trip_schedule WHERE trip_date = :tripDate", nativeQuery = true)
    List<TripSchedule> findTripScheduleByDate(String tripDate);
}
