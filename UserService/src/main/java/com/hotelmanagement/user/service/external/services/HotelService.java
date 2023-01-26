package com.hotelmanagement.user.service.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotelmanagement.user.service.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE/hotels")
public interface HotelService {

    // get by id
    @GetMapping(path = "/{hotelId}")
    Hotel getHotelById(@PathVariable String hotelId);

    // create
    @PostMapping()
    Hotel createHotel(@RequestBody Hotel hotel);

    // get all
    @GetMapping()
    List<Hotel> getAllHotel();

}
