package com.david.hotelbooking.common.constant;

import com.david.hotelbooking.common.domain.entity.BookedHotelDO;
import org.apache.dubbo.common.utils.ConcurrentHashSet;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    /**
     * guestName---->listOfBookedRoom
     */
    public static ConcurrentHashMap<String, List<BookedHotelDO>> HOTEL_BOOKING_DATABASE = new ConcurrentHashMap<>();

    /**
     * date_roomNumber
     */
    public static ConcurrentHashSet<String> BOOKED_ROOM = new ConcurrentHashSet<>();
}
