package com.net.couponSystem.services;

import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.exceptions.CouponsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {


    @Override
    public boolean login(String email, String password) {
        return email.equalsIgnoreCase("admin@admin.com") && password.equalsIgnoreCase("admin");
    }


    @Override
    public void addCompany(Company company) throws CouponsException {
        if (companyRepository.existsByFirstNameAndPassword(company.getFirstName(), company.getPassword())) {
            throw new CouponsException("sorry this name already exist.");
        }
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Company company) throws CouponsException {
        if (!companyRepository.existsByFirstNameAndPassword(company.getFirstName(), company.getPassword())) {
            throw new CouponsException("sorry you can not change name or password.");
        }
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyID) {
        companyRepository.deleteById(companyID);

    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getOneCompany(int companyID) {
        return companyRepository.getOne(companyID);
    }

    @Override
    public void addCustomer(Customer customer) throws CouponsException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponsException("sorry this email is already exist");
        }
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws CouponsException {
        if (!customerRepository.existsById(customer.getId())) {
            throw new CouponsException("this id doesn't exist");
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerID) {
        customerRepository.deleteById(customerID);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerID) {
        return customerRepository.getOne(customerID);
    }


}
