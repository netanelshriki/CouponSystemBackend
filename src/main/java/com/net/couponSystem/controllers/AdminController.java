package com.net.couponSystem.controllers;

import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("customers")
    public ResponseEntity<?> addCustomer(@RequestHeader("Authorization") String token, @RequestBody Customer customer) throws CouponsException {
        adminService.addCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("customers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("customers/{id}")
    public void deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable int id) {
        adminService.deleteCustomer(id);
    }

    @GetMapping("customers/{customerID}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader("Authorization") String token, @PathVariable int customerId){
        return new ResponseEntity<>(adminService.getOneCustomer(customerId),HttpStatus.OK);
    }

    @PutMapping("customers")
    public ResponseEntity<?> updateCustomer(@RequestHeader("Authorization") String token, @RequestBody Customer customer) throws CouponsException {
        adminService.updateCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("companies")
    public ResponseEntity<?> addCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) throws CouponsException {
        adminService.addCompany(company);
        return new ResponseEntity<>(company,HttpStatus.OK);
    }

    @GetMapping("companies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
    }

    @PutMapping("companies")
    public ResponseEntity<?> updateCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) throws CouponsException {
        adminService.updateCompany(company);
        return new ResponseEntity<>(company,HttpStatus.OK);
    }

    @GetMapping("company/{companyID}")
    public ResponseEntity<?> getOneCompany(@RequestHeader("Authorization") String token,@PathVariable int companyId){
        return new ResponseEntity<>(adminService.getOneCompany(companyId),HttpStatus.OK);
    }

    @DeleteMapping("companies/{companyID}")
    public ResponseEntity<?> deleteOneCompany(@RequestHeader("Authorization") String token, @PathVariable int companyID){
        adminService.deleteCompany(companyID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
