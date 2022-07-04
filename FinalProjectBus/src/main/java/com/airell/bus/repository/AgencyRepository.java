package com.airell.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airell.bus.models.Agency;
import com.airell.bus.models.User;

/*
 * Ini adalah tag Repository
 * Merupakan file repository dari tabel Agency
 */
@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
	Agency findByOwner(User owner);
	
	/*
	 * Bahasa native Query untuk pemanggilan tabel berfilter
	 * Filter menggunakan id dari tabel User
	 */
	@Query(value = "SELECT DISTINCT * FROM agency WHERE owner_user_id = :owner", nativeQuery = true)
	Agency findByOwnerUser(Long owner);
	
	Agency findByOwnerId(Long owner_id);
}
