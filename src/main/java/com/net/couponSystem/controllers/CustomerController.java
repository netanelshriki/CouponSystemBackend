package com.net.couponSystem.controllers;


import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("coupons")
    public ResponseEntity<?> getCustomerCoupon(@RequestHeader ("Authorization") String token,@RequestParam int customerId) {
        return new ResponseEntity<>(customerService.getCustomerCoupon(customerId), HttpStatus.OK);
    }

    @GetMapping("coupons/")
    public ResponseEntity<?> getCouponsByMaxPrize(@RequestHeader ("Authorization") String token,@RequestParam int prize,@RequestParam int customerId){
        return new ResponseEntity<>(customerService.getCouponsByMaxPrice(prize,customerId),HttpStatus.OK);    }

        @PostMapping("buy")
        public ResponseEntity<?> buyCoupon(@RequestHeader ("Authorization") String token, @RequestBody Coupon coupon, @RequestParam int customerId) throws CouponsException {
        customerService.buyCoupon(coupon, customerId);
        return new ResponseEntity<>(customerService.getCustomerDetails(customerId),HttpStatus.CREATED);
        }

        @GetMapping("details")
        public ResponseEntity<?> getCustomerDetails(@RequestHeader ("Authorization") String token,@RequestParam int customerId){
        return new ResponseEntity<>(customerService.getCustomerDetails(customerId),HttpStatus.OK);
        }

}
