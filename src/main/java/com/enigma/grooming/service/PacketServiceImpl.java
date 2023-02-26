package com.enigma.grooming.service;

import com.enigma.grooming.exception.EntityExistException;
import com.enigma.grooming.exception.NotFoundException;
import com.enigma.grooming.model.Packet;
import com.enigma.grooming.model.request.PacketRequest;
import com.enigma.grooming.repository.PacketRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacketServiceImpl implements PacketService {


    private PacketRepository packetRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PacketServiceImpl(PacketRepository packetRepository, ModelMapper modelMapper) {
        this.packetRepository = packetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Packet create(PacketRequest packetRequest) {
        try {
            Packet packet = modelMapper.map(packetRequest, Packet.class);
            return packetRepository.save(packet);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Page<Packet> getList(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Packet> result = packetRepository.findAll(pageable);
        return result;
    }

    @Override
    public void update(PacketRequest packetRequest, Integer id) {
        try {
            Packet existingPacket = get(id);
            existingPacket.setPackageName(packetRequest.getPackageName());
            existingPacket.setDescription(packetRequest.getDescription());
            existingPacket.setPrice(packetRequest.getPrice());

            packetRepository.save(existingPacket);
        } catch (NotFoundException e) {
            throw new NotFoundException("update failed because id not found");
        }

    }

    @Override
    public void delete(Integer id) {
            packetRepository.deleteById(id);
    }

    @Override
    public Packet get(Integer id) {
        Optional<Packet> packet = packetRepository.findById(id);
        if (packet.isEmpty()) {
            throw new NotFoundException("Packet Not Found");
        }
        return packet.get();
    }

    @Override
    public List<Packet> getAll(Boolean isDeleted) {
        return packetRepository.findAll(isDeleted);
    }
}
