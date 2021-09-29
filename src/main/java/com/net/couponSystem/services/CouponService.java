package com.net.couponSystem.services;

import com.net.couponSystem.controllers.model.CouponPayload;
import com.net.couponSystem.mapper.CouponDTO;

import java.util.List;

public interface CouponService {
    CouponDTO addCoupon(CouponPayload payload) throws Exception;
    List<CouponDTO> getAllCoupons();
    void deleteCoupon(int id);
}