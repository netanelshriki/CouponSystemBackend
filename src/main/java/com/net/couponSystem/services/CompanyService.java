package com.net.couponSystem.services;

import com.net.couponSystem.beans.Category;
import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.mapper.CouponDTO;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface CompanyService {

    void addCompany(Company company) throws CouponsException;
    void updateCompany(Company company) throws CouponsException;
    void deleteCompanyById(int companyID);
    Company getOneCompany(int CompanyID);
    Customer getOneCustomer(int customerID);
    List<CouponDTO> getCouponsCompanyByID(int companyId);
    Company getCompanyDetails(int companyID);
    List<CouponDTO> getCouponsByMaxPrice(int maxPrice, int companyId);
    List<CouponDTO> getCouponsCompanyByCategory(Category category, int companyId);


}
