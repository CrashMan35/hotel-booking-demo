package com.david.hotelbooking.hotel.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SaveBookingRequest implements Serializable {
    @NotNull(message = "账户名称[guestName]不能为空")
    private String guestName;
    @NotNull(message = "房间号[roomNumber]不能为空")
    private String roomNumber;
    @NotNull(message = "时间[date]不能为空")
    private String date;
}
