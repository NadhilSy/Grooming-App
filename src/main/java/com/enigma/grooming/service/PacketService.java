package com.enigma.grooming.service;

import com.enigma.grooming.model.Packet;
import com.enigma.grooming.model.request.PacketRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PacketService {

    Packet create(PacketRequest packetRequest);

    Page<Packet> getList(Integer page, Integer size, String direction, String sortBy);
    List<Packet> getAll(Boolean isDeleted);

    void update(PacketRequest packetRequest, Integer id);

    void delete(Integer id);

    Packet get(Integer id);


}
