package com.net.couponSystem.services;

import com.net.couponSystem.repos.CompanyRepository;
import com.net.couponSystem.repos.CouponRepository;
import com.net.couponSystem.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public abstract class ClientService {

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected CouponRepository couponRepository;

    public abstract boolean login(String email, String password) throws LoginException;

}
