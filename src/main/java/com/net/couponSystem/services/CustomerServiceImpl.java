package com.net.couponSystem.services;

import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.repos.CustomerRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
@Getter
public class CustomerServiceImpl extends ClientService implements CustomerService{

    private int customerID;
    private String customerName;


    @Override
    public boolean login(String email, String password) throws LoginException {
        System.out.println(email);
        System.out.println(password);
        boolean isLoggedIn = false;
        isLoggedIn =  customerRepository.existsByEmailAndPassword(email, password);
        if (!isLoggedIn) {
            throw new LoginException("Error, Unable to logg in.. try again");
        }
        customerID = customerRepository.findByEmailAndPassword(email, password).getId();
        customerName = customerRepository.findByEmailAndPassword(email, password).getFirstName();
        System.out.println("the customer id is : " + customerID);
        return true;

    }

    @Override
    public List<Coupon> getCustomerCoupon() {
        return null;
    }

    @Override
    public List<Coupon> getCouponsByMaxPrice(int maxPrice) {
        return null;
    }

    @Override
    public void buyCoupon(Coupon coupon) {

    }
}
