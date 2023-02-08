package com.enigma.grooming.repository;

import com.enigma.grooming.model.Packet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacketRepository extends JpaRepository<Packet, Integer> {

    List<Packet> findAll(Specification specification);
}
