package com.airell.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airell.bus.models.Stop;

/*
 * Ini adalah tag Repository
 * Merupakan file repository dari tabel Stop
 */
@Repository
public interface StopRepository extends JpaRepository<Stop, Long> {
	List<Stop> findByName (String name);
	List<Stop> findByCode (String code);
}
