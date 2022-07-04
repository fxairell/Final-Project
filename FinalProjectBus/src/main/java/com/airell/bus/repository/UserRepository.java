package com.airell.bus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airell.bus.models.User;

/*
 * Ini adalah tag Repository
 * Merupakan file repository dari tabel User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	/*
	 * Penentuan pilihan enumerasi dari Nama
	 */
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
