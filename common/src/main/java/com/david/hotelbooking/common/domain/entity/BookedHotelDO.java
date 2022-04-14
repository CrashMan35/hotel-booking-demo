package com.david.hotelbooking.common.domain.entity;

import lombok.Data;

@Data
public class BookedHotelDO {
    private String guestName;
    private String roomNumber;
    private String date;
}
