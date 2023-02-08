package com.enigma.grooming.controller;

import com.enigma.grooming.model.Packet;
import com.enigma.grooming.model.request.PacketRequest;
import com.enigma.grooming.model.response.PagingResponse;
import com.enigma.grooming.model.response.SuccessResponse;
import com.enigma.grooming.service.PacketService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packages")
public class PacketController {

    private PacketService packetService;

    public PacketController(@Autowired PacketService packetService) {
        this.packetService = packetService;
    }

    @PostMapping
    public ResponseEntity createPacket(@Valid @RequestBody PacketRequest packetRequest){
        Packet result = packetService.create(packetRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("",result));
    }

    @GetMapping
    public ResponseEntity getAllPacket(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "packageName") String sortBy
    ){
        Page<Packet> packets = packetService.getList(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get packets", packets));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody PacketRequest packetRequest, @PathVariable("id") Integer id) {
        packetService.update(packetRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update packet", packetRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id){
        packetService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete packet", null));
    }


}
