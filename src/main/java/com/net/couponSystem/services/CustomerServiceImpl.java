package com.net.couponSystem.services;

import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.mapper.CouponDTO;
import com.net.couponSystem.mapper.CouponMapper;
import com.net.couponSystem.repos.CustomerRepository;
import com.net.couponSystem.utils.DateUtils;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
@Getter
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private int customerID;
    private String customerName;

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public boolean login(String email, String password) throws LoginException {
        System.out.println(email);
        System.out.println(password);
        boolean isLoggedIn = false;
        isLoggedIn = customerRepository.existsByEmailAndPassword(email, password);
        if (!isLoggedIn) {
            throw new LoginException("Error, Unable to logg in.. try again");
        }
        customerID = customerRepository.findByEmailAndPassword(email, password).getId();
        customerName = customerRepository.findByEmailAndPassword(email, password).getFirstName();
        System.out.println("the customer id is : " + customerID);
        return true;

    }

    @Override
    public List<CouponDTO> getCustomerCoupon(int customerId) {
        return couponMapper.toDtoList(customerRepository.getOne(customerId).getCoupons());

    }

    @Override
    public List<CouponDTO> getCouponsByMaxPrice(int maxPrice, int customerId) {
        List<CouponDTO> coupons = new ArrayList<>();
        for (Coupon coupon : customerRepository.getOne(customerId).getCoupons()) {
            if (coupon.getPrice() < maxPrice) {
                coupons.add(couponMapper.toDto(coupon));
            }
        }
        return coupons;
    }

    @Override
    public Coupon buyCoupon(Coupon coupon, int customerId) throws CouponsException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new CouponsException("Error,customer by the id: " + customerId + " does not exists"));
        List<Coupon> customerCoupons = customer.getCoupons();
        System.out.println("customer: " + customer);
        System.out.println("customer coupons: " + customerCoupons);
        int couponId = coupon.getId();
        //  System.out.println(couponRepository.getOne(couponId));
        Coupon couponToBuy = couponRepository.getOne(couponId);
        //  .orElseThrow(() -> new CouponsException("there is no coupon by the id: " + customerId));
        System.out.println(couponToBuy);
//        if (coupon.getEndDate().before(coupon.getStartDate())) {
//            throw new CouponsException("coupon by the id:" + couponId + " has already expired ");
//        }
        if (coupon.getAmount() <= 0) {
            throw new CouponsException("the coupon by the id: " + couponId + "is sold out");
        }
        for (Coupon c : customerCoupons) {
            if (couponId == c.getId()) {
                throw new CouponsException("Coupon was previously purchased");
            }
        }
        coupon.setAmount(coupon.getAmount() - 1);
        customerCoupons.add(coupon);
        customer.setCoupons(customerCoupons);
        customerRepository.save(customer);
        couponRepository.save(coupon);
        System.out.println("the coupon : " + couponId + " was purchased succesfully");
        return coupon;
    }

//    @Override
//    public void buyCoupon(Coupon coupon, int customerId) throws CouponsException {
//        for (Coupon existsCoupon : customerRepository.getOne(customerId).getCoupons()) {
//            if (existsCoupon.getId() == coupon.getId()) {
//                throw new CouponsException("sorry you can buy this coupon only one time");
//            }
//        }
//        if (coupon.getEndDate().before(DateUtils.toSqlDate(LocalDateTime.now()))) {
//            throw new CouponsException("sorry this coupon expired");
//        }
//
//        int amount = couponRepository.getOne(coupon.getId()).getAmount() ;
//        coupon.setAmount(amount);
//        Coupon toBuy = couponRepository.getOne(coupon.getId());
//        toBuy.setAmount(amount-1);
//        System.out.println("customer id: "+customerId);
//        System.out.println("coupon: "+coupon);
//        System.out.println("toBuy: "+toBuy);
//
//        Customer customer = customerRepository.getOne(customerId);
//        System.out.println("before buying: "+customer);
//        List<Coupon> coupons = customer.getCoupons();
//        System.out.println("coupons before: "+coupons);
//        coupons.add(toBuy);
//        customer.setCoupons(coupons);
//        customerRepository.saveAndFlush(customer);
//
//
//        System.out.println("coupons after: "+coupons);
//
//        System.out.println("after buying: "+customer);
//
//    }

    @Override
    public Customer getCustomerDetails(int customerId) {
        return customerRepository.getOne(customerId);
    }
}
