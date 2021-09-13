package com.net.couponSystem.services;

import com.net.couponSystem.beans.Coupon;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;


public interface CustomerService {

    boolean login(String email, String password) throws LoginException;
    List<Coupon> getCustomerCoupon();
    List<Coupon> getCouponsByMaxPrice(int maxPrice);
    void buyCoupon(Coupon coupon);
}
