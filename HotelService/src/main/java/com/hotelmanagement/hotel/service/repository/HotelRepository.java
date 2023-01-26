package com.hotelmanagement.hotel.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagement.hotel.service.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
