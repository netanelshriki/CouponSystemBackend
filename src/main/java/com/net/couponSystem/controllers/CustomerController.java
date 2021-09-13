package com.net.couponSystem.controllers;

import com.net.couponSystem.beans.Category;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.controllers.model.LogoutDetails;
import com.net.couponSystem.dto.request.RequestLogin;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.services.ClientService;
import com.net.couponSystem.services.CompanyService;
import com.net.couponSystem.services.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("customer/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("coupons")
    public ResponseEntity<?> getCustomerCoupon() {
        return new ResponseEntity<>(customerService.getCustomerCoupon(), HttpStatus.OK);
    }


}
