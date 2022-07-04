package com.airell.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airell.bus.models.Ticket;

/*
 * Ini adalah tag Repository
 * Merupakan file repository dari tabel Ticket
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
