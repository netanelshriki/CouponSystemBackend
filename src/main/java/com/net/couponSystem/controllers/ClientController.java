package com.net.couponSystem.controllers;

import com.net.couponSystem.dto.request.RequestLogin;
import com.net.couponSystem.dto.request.response.ResponseLogin;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.repos.AdminRepository;
import com.net.couponSystem.repos.CompanyRepository;
import com.net.couponSystem.repos.CouponRepository;
import com.net.couponSystem.repos.CustomerRepository;
import com.net.couponSystem.security.Information;
import com.net.couponSystem.security.LoginManager;
import com.net.couponSystem.security.TokenManager;
import com.net.couponSystem.services.AdminService;
import com.net.couponSystem.services.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping(value ="client")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {

    private final LoginManager loginManager;
    private final CustomerRepository customerRepository;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody RequestLogin requestLogin) throws CouponsException, LoginException {
        String token = loginManager.login(requestLogin.getEmail(), requestLogin.getPassword(), requestLogin.getClientType());
        Information information = tokenManager.getMap().get(token);
        System.out.println("from client info------->" + information);
        ResponseLogin responseLogin = new ResponseLogin();
        responseLogin.setClientID(information.getClientID());
        responseLogin.setClientType(information.getClientType());
        responseLogin.setName(information.getName());
        responseLogin.setToken(token);
        System.out.println("from client ---------->"+responseLogin);
        return new ResponseEntity<>(responseLogin,HttpStatus.CREATED);
    }


}



