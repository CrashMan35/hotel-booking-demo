package com.david.hotelbooking.hotel.converter;

import com.david.hotelbooking.common.domain.entity.BookedHotelDO;
import com.david.hotelbooking.hotel.domain.request.SaveBookingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookedRoomConverter {

  
    BookedHotelDO convertPromotionDO(SaveBookingRequest request);


}
