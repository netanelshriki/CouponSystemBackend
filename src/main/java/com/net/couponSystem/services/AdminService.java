package com.net.couponSystem.services;

import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.exceptions.CouponsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
void addCompany(Company company) throws CouponsException;
void updateCompany(Company company) throws CouponsException;
void deleteCompany(int companyID);
List<Company> getAllCompanies();
Company getOneCompany(int companyID);
void addCustomer(Customer customer) throws CouponsException;
void updateCustomer(Customer customer) throws CouponsException;
void deleteCustomer(int customerID);
List<Customer> getAllCustomers();
Customer getOneCustomer(int customerID);
}
