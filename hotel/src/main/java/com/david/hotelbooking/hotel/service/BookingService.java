package com.david.hotelbooking.hotel.service;

import com.david.hotelbooking.common.domain.entity.BookedHotelDO;
import com.david.hotelbooking.hotel.domain.request.SaveBookingRequest;

import java.util.List;

public interface BookingService {

    void saveBooking(SaveBookingRequest saveBookingRequest);

    List<String> getAllAvailableRooms(String date);

    List<BookedHotelDO> getGuestAllBookings(String guestName);
}
