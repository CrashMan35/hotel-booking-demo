import com.alibaba.fastjson.JSON;
import com.david.hotelbooking.common.core.JsonResult;
import com.david.hotelbooking.common.domain.entity.BookedHotelDO;
import com.david.hotelbooking.hotel.HotelApplication;
import com.david.hotelbooking.hotel.domain.request.SaveBookingRequest;
import com.david.hotelbooking.hotel.service.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = HotelApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BookingTest {

    @Autowired
    private BookingService bookingService;


    /**
     * 获取某日期下所有可预订的房间号
     */
    @Test
    public void getAllAvailableRooms() {
        String date = "20220414";
        // 执行请求
        List<String> rooms = bookingService.getAllAvailableRooms(date);
        JsonResult<List<String>> jsonResult = JsonResult.buildSuccess(rooms);

        System.out.println(JSON.toJSONString(jsonResult));
    }

    /**
     * 预订某日期的房间
     */
    @Test
    public void saveBooking() {
        // 构造请求
        SaveBookingRequest request = buildSaveBookingRequest();
        // 执行请求
        bookingService.saveBooking(request);

        //预约后再获取所有可预订的房间号
        List<String> rooms = bookingService.getAllAvailableRooms("20220414");
        JsonResult<List<String>> jsonResult = JsonResult.buildSuccess(rooms);

        System.out.println(JSON.toJSONString(jsonResult));
    }

    /**
     * 获取guest所有预约的房间
     */
    @Test
    public void getGuestAllBookings() {
        saveBooking();

        //获取所有预订的房间
        List<BookedHotelDO> rooms = bookingService.getGuestAllBookings("david");
        JsonResult<List<BookedHotelDO>> jsonResult = JsonResult.buildSuccess(rooms);

        System.out.println(JSON.toJSONString(jsonResult));
    }

    private SaveBookingRequest buildSaveBookingRequest() {
        SaveBookingRequest request = new SaveBookingRequest();
        request.setGuestName("david");
        request.setRoomNumber("1");
        request.setDate("20220414");
        return request;
    }
}
