package com.hotelmanagement.hotel.service.service;

import java.util.List;

import com.hotelmanagement.hotel.service.entities.Hotel;

public interface HotelService {

    // create
    Hotel createHotel(Hotel hotel);

    // get all hotel
    List<Hotel> getAllHotel();

    // get hotel by hotelId
    Hotel getHotelById(String hotelId);
}
