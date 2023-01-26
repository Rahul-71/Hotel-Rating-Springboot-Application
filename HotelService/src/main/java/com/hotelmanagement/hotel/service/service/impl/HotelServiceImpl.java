package com.hotelmanagement.hotel.service.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagement.hotel.service.entities.Hotel;
import com.hotelmanagement.hotel.service.exceptions.ResourceNotFoundExceptions;
import com.hotelmanagement.hotel.service.repository.HotelRepository;
import com.hotelmanagement.hotel.service.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setId(randomId);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return this.hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Hotel not found with hotelId: " + hotelId));
    }

}
