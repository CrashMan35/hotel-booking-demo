package com.david.hotelbooking.hotel.service.impl;

import com.david.hotelbooking.common.constant.Database;
import com.david.hotelbooking.common.domain.entity.BookedHotelDO;
import com.david.hotelbooking.common.exception.BaseBizException;
import com.david.hotelbooking.hotel.converter.BookedRoomConverter;
import com.david.hotelbooking.hotel.dao.BookedHotelDAO;
import com.david.hotelbooking.hotel.domain.request.SaveBookingRequest;
import com.david.hotelbooking.hotel.service.BookingService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {


    @Value("${hotel.amount}")
    private Integer amount;

    @Resource
    private BookedRoomConverter bookedRoomConverter;

    @Autowired
    private BookedHotelDAO bookedHotelDAO;

    @Override
    public void saveBooking(SaveBookingRequest saveBookingRequest) {
        //判断房间是否有效
        validBooking(saveBookingRequest);
        //构造预订房间实体
        BookedHotelDO bookedHotelDO = bookedRoomConverter.convertPromotionDO(saveBookingRequest);
        //房间预订落库
        synchronized (this){
            if (Database.BOOKED_ROOM.contains(saveBookingRequest.getDate() + "_" + saveBookingRequest.getRoomNumber())) {
                log.error("房间已被预订："+saveBookingRequest.getDate() + "_" + saveBookingRequest.getRoomNumber());
                throw new BaseBizException("房间已被预订");
            }
            bookedHotelDAO.saveBooking(bookedHotelDO);
        }
    }

    @Override
    public List<String> getAllAvailableRooms(String date) {
        List<String> availableRooms = new ArrayList<>();
        Set<Integer> bookedRooms = Database.BOOKED_ROOM.stream()
                .filter(it->it.startsWith(date))
                .map(it -> Integer.parseInt(it.split("_")[1])).collect(Collectors.toSet());
        for (int i = 1; i <= amount; i++) {
            if (!bookedRooms.contains(i)) {
                availableRooms.add(String.valueOf(i));
            }
        }
        return availableRooms;
    }

    @Override
    public List<BookedHotelDO> getGuestAllBookings(String guestName) {
        return Database.HOTEL_BOOKING_DATABASE.get(guestName);
    }

    private void validBooking(SaveBookingRequest saveBookingRequest) {
        int roomNumber = Integer.parseInt(saveBookingRequest.getRoomNumber());
        if (roomNumber < 1 || roomNumber > amount) {
            throw new BaseBizException("房间号异常");
        }
    }
}
