package com.example.team.fch.secretofmay.domain.guest.repository;

import com.example.team.fch.secretofmay.domain.guest.entity.Guest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findBySessionId(String sessionId);

    boolean existsBySessionId(String sessionId);
}