package com.airell.bus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airell.bus.models.ERole;
import com.airell.bus.models.Role;

/*
 * Ini adalah tag Repository
 * Merupakan file repository dari tabel Role
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	/*
	 * Penentuan pilihan enumerasi dari Role
	 */
	Optional<Role> findByName(ERole name);
}
