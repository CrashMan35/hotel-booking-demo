package com.david.hotelbooking.hotel.controller;

import com.alibaba.fastjson.JSON;
import com.david.hotelbooking.common.core.JsonResult;
import com.david.hotelbooking.common.domain.entity.BookedHotelDO;
import com.david.hotelbooking.common.exception.BaseBizException;
import com.david.hotelbooking.hotel.domain.request.SaveBookingRequest;
import com.david.hotelbooking.hotel.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hotel/book")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public JsonResult saveBooking(@RequestBody SaveBookingRequest request) {
        try {
            log.info("新增一个hotel预约, SaveBookingRequest: {}", JSON.toJSONString(request));
            bookingService.saveBooking(request);
            return JsonResult.buildSuccess(null);
        } catch (BaseBizException e) {
            log.error("biz error: request={}", JSON.toJSONString(request), e);
            return JsonResult.buildError(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("system error: request={}", JSON.toJSONString(request), e);
            return JsonResult.buildError(e.getMessage());
        }
    }


    @GetMapping("/rooms")
    public JsonResult<List<String>> getAllAvailableRooms(String date) {
        try {
            List<String> allAvailableRooms = bookingService.getAllAvailableRooms(date);
            return JsonResult.buildSuccess(allAvailableRooms);
        } catch (BaseBizException e) {
            log.error("biz error: request={}", JSON.toJSONString(date), e);
            return JsonResult.buildError(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("system error: request={}", JSON.toJSONString(date), e);
            return JsonResult.buildError(e.getMessage());
        }
    }

    @GetMapping("/bookings")
    public JsonResult<List<BookedHotelDO>> getGuestAllBookings(String guestName) {
        try {
            List<BookedHotelDO> bookings = bookingService.getGuestAllBookings(guestName);
            return JsonResult.buildSuccess(bookings);
        } catch (BaseBizException e) {
            log.error("biz error: request={}", JSON.toJSONString(guestName), e);
            return JsonResult.buildError(e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("system error: request={}", JSON.toJSONString(guestName), e);
            return JsonResult.buildError(e.getMessage());
        }
    }

}
