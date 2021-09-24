package com.net.couponSystem.controllers.model;

import com.net.couponSystem.beans.Coupon;
import lombok.Data;

@Data
public class BuyCoupon {
    private int customerId;
    private Coupon coupon;
}
