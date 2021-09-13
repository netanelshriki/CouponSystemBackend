package com.net.couponSystem.controllers;

import com.net.couponSystem.beans.Admin;
import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.controllers.model.LogoutDetails;
import com.net.couponSystem.dto.request.RequestLogin;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.security.ClientType;
import com.net.couponSystem.services.AdminService;
import com.net.couponSystem.services.ClientService;
import com.net.couponSystem.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("admin/")
public class AdminController  {



//    @GetMapping("customers")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<?> getAllCustomers() {
//        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("customers/{id}")
//    public void deleteCustomer(@PathVariable int id) {
//        customerRepository.deleteById(id);
//    }
//
//    @PutMapping("customers/update")
//    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws CouponsException {
//        adminService.updateCustomer(customer);
//        return new ResponseEntity<>(customer, HttpStatus.OK);
//    }
//
//    @GetMapping("companies")
//    public ResponseEntity<?> getAllCompanies() {
//        return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.OK);
//    }

}
