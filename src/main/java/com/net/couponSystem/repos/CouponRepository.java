package com.net.couponSystem.repos;

import com.net.couponSystem.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    Coupon findCouponByTitle(String title);
}
