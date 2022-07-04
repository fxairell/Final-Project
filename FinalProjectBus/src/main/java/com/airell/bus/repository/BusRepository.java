package com.airell.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airell.bus.models.Bus;

/*
 * Ini adalah tag Repository
 * Merupakan file repository dari tabel Bus
 */
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
	/*
	 * Bahasa native Query untuk pemanggilan tabel berfilter
	 * Filter menggunakan id dari tabel Agency
	 */
    @Query(value = "SELECT * FROM bus WHERE agency_id = :id", nativeQuery = true)
    List<Bus> findByAgencyId(Long id);
}
