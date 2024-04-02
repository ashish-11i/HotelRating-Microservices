package com.cwa.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwa.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
