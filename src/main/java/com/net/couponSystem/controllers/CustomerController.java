package com.net.couponSystem.controllers;


import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.controllers.model.BuyCoupon;
import com.net.couponSystem.controllers.model.CustomerCouponsByCategory;
import com.net.couponSystem.dto.request.RequestLogin;
import com.net.couponSystem.dto.request.RequestRegister;
import com.net.couponSystem.dto.request.response.ResponseLogin;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.mapper.CouponDTO;
import com.net.couponSystem.mapper.CouponMapper;
import com.net.couponSystem.security.Information;
import com.net.couponSystem.security.LoginManager;
import com.net.couponSystem.security.TokenManager;
import com.net.couponSystem.services.AdminService;
import com.net.couponSystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private final CouponMapper couponMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private LoginManager loginManager;
    @Autowired
    private TokenManager tokenManager;

    @GetMapping("coupons")
    public ResponseEntity<?> getCustomerCoupon(@RequestHeader("Authorization") String token, @RequestParam int customerId) {
        return new ResponseEntity<>(customerService.getCustomerCoupon(customerId), HttpStatus.OK);
    }

    @GetMapping("coupons/")
    public ResponseEntity<?> getCouponsByMaxPrize(@RequestHeader("Authorization") String token, @RequestParam int prize, @RequestParam int customerId) {
        return new ResponseEntity<>(customerService.getCouponsByMaxPrice(prize, customerId), HttpStatus.OK);
    }

    @PostMapping("coupons/category")
    public ResponseEntity<?> getCouponsByMaxCategory(@RequestHeader("Authorization") String token, @RequestBody CustomerCouponsByCategory byCategory) {
        return new ResponseEntity<>(customerService.getCouponsByCategory(byCategory.getCategory(), byCategory.getCustomerId()), HttpStatus.OK);
    }

    @PostMapping(    value = "buy"
//            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> buyCoupon(@RequestHeader("Authorization") String token,@RequestBody CouponDTO couponDTO, @RequestParam int customerId) throws Exception {
        System.out.println("coupon dto:"+couponDTO);

        Coupon coupon = couponMapper.toDao(couponDTO);
        System.out.println("coupon dto:"+couponDTO);
        System.out.println("coupon: "+coupon);
        customerService.buyCoupon(coupon, customerId);
        return new ResponseEntity<>(coupon, HttpStatus.CREATED);
    }


    @GetMapping("details")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader("Authorization") String token, @RequestParam int customerId) {
        return new ResponseEntity<>(customerService.getCustomerDetails(customerId), HttpStatus.OK);
    }


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RequestRegister requestRegister) throws CouponsException, LoginException {
Customer customer = Customer.builder()
        .firstName(requestRegister.getFirstName())
        .lastName(requestRegister.getLastName())
        .email(requestRegister.getEmail())
        .password(requestRegister.getPassword())
        .clientType(requestRegister.getClientType())
        .build();
        adminService.addCustomer(customer);
       String token = loginManager.login(requestRegister.getEmail(), requestRegister.getPassword(),requestRegister.getClientType());

        Information information = tokenManager.getMap().get(token);
        System.out.println("from client info------->" + information);

        ResponseLogin responseLogin = ResponseLogin.builder()
                .id(information.getId())
                .clientType(information.getClientType())
                .name(information.getName())
                .token(token)
                .build();
        return new ResponseEntity<>(responseLogin,HttpStatus.CREATED);
    }




}
