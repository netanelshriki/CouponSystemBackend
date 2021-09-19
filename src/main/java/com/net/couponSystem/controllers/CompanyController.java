package com.net.couponSystem.controllers;

import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.controllers.model.LogoutDetails;
import com.net.couponSystem.dto.request.RequestLogin;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.security.TokenManager;
import com.net.couponSystem.services.ClientService;
import com.net.couponSystem.services.CompanyService;
import com.net.couponSystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("company")
public class CompanyController{

    private final TokenManager tokenManager;

    @Autowired
    private  CompanyService companyService;

    @GetMapping("coupons")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Coupon> getCompanyCoupons(@RequestHeader ("Authorization") String token, @RequestParam int companyID) {
        return companyService.getCouponsCompanyByID(companyID);
    }

}
