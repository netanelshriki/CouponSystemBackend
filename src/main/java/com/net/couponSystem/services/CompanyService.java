package com.net.couponSystem.services;

import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.exceptions.CouponsException;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface CompanyService {
    void addCompany(Company company) throws CouponsException;
    void updateCompany(Company company) throws CouponsException;
    void deleteCompanyById(int companyID);
    Company getOneCompany(int CompanyID);
    List<Company> getAllCompanies();
    void deleteCustomer(int id);
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerID);
    List<Coupon> getCouponsCompanyByID(int companyID);


}
