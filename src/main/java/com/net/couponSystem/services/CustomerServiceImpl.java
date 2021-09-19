package com.net.couponSystem.services;

import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.repos.CustomerRepository;
import com.net.couponSystem.utils.DateUtils;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
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
    public List<Coupon> getCustomerCoupon(int customerId) {
        return customerRepository.getOne(customerId).getCoupons();
    }

    @Override
    public List<Coupon> getCouponsByMaxPrice(int maxPrice, int customerId) {
        List<Coupon> coupons = new ArrayList<>();
        for (Coupon coupon : customerRepository.getOne(customerId).getCoupons()) {
            if (coupon.getPrice() < maxPrice) {
                coupons.add(coupon);
            }
        }
        return coupons;
    }

    @Override
    public void buyCoupon(Coupon coupon, int customerId) throws CouponsException {
        for (Coupon existsCoupon : customerRepository.getOne(customerId).getCoupons()) {
            if (existsCoupon.getId() == coupon.getId()) {
                throw new CouponsException("sorry you can buy this coupon only one time");
            }
        }
        if (coupon.getEndDate().before(DateUtils.toSqlDate(LocalDateTime.now()))) {
            throw new CouponsException("sorry this coupon expired");
        }

        int amount = couponRepository.getOne(coupon.getId()).getAmount() ;
        Coupon toBuy = couponRepository.getOne(coupon.getId());
        toBuy.setAmount(amount-1);
        couponRepository.saveAndFlush(toBuy);
        Customer customer = customerRepository.getOne(customerId);
        List<Coupon> coupons = customer.getCoupons();
        coupons.add(toBuy);
        customer.setCoupons(coupons);
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getCustomerDetails(int customerId) {
        return customerRepository.getOne(customerId);
    }
}
