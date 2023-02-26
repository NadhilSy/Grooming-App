package com.enigma.grooming.repository;

import com.enigma.grooming.model.Packet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacketRepository extends JpaRepository<Packet, Integer> {
    @Query(value = "SELECT p.package_id,p.package_name,p.description,p.price,p.is_deleted from packet p WHERE p.is_deleted = ?1", nativeQuery = true)
    List<Packet> findAll(Boolean isDeleted);
    @Modifying
    @Query(value = "UPDATE packet SET is_deleted = true WHERE package_id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);
}
