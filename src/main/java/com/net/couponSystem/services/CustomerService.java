package com.net.couponSystem.services;

import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.exceptions.CouponsException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;


public interface CustomerService {

    boolean login(String email, String password) throws LoginException;
    List<Coupon> getCustomerCoupon(int customerId);
    List<Coupon> getCouponsByMaxPrice(int maxPrice,int customerId);
    Coupon buyCoupon(Coupon coupon) throws CouponsException;
    Customer getCustomerDetails(int customerId);
}
