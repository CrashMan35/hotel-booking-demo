package com.david.hotelbooking.hotel.dao;

import cn.hutool.core.collection.CollUtil;
import com.david.hotelbooking.common.constant.Database;
import com.david.hotelbooking.common.domain.entity.BookedHotelDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class BookedHotelDAO {

    public void saveBooking(BookedHotelDO bookedHotelDO) {
        Database.BOOKED_ROOM.add(bookedHotelDO.getDate() + "_" + bookedHotelDO.getRoomNumber());
        List<BookedHotelDO> bookedHotelDOS = Database.HOTEL_BOOKING_DATABASE.get(bookedHotelDO.getGuestName());
        if (CollUtil.isEmpty(bookedHotelDOS)) {
            bookedHotelDOS = new ArrayList<>();
        }
        bookedHotelDOS.add(bookedHotelDO);
        Database.HOTEL_BOOKING_DATABASE.put(bookedHotelDO.getGuestName(), bookedHotelDOS);
    }
}
