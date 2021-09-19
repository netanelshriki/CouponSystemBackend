package com.net.couponSystem.services;

import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.exceptions.CouponsException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Scope("prototype")
@Getter
public class CompanyServiceImpl extends ClientService implements CompanyService {

    private String name;
    private int companyID;


    @Override
    public boolean login(String email, String password) throws LoginException {

        if ( !companyRepository.existsByEmailAndPassword(email, password)) {
            throw new LoginException("Error, Unable to logg in.. try again");
        }
        companyID = companyRepository.findByEmailAndPassword(email, password).getId();
        name = companyRepository.findByEmailAndPassword(email, password).getFirstName();
        System.out.println("the customer id is : " + companyID);
        return companyRepository.existsByEmailAndPassword(email, password);
    }


    @Override
    public void addCompany(Company company) throws CouponsException {
        if (companyRepository.existsByFirstNameAndPassword(company.getFirstName(), company.getPassword())) {
            throw new CouponsException("sorry the name or email used");
        }
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Company company) throws CouponsException {
        if (!companyRepository.existsByFirstNameAndPassword(company.getFirstName(), company.getPassword()))
            throw new CouponsException("sorry you can not change name or password.");

        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompanyById(int companyID) {
        companyRepository.deleteById(companyID);
    }

    @Override
    public Company getOneCompany(int companyID) {
        return companyRepository.getOne(companyID);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }



    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerID) {
        return customerRepository.getOne(customerID);
    }

    public List<Coupon> getCouponsCompanyByID(int companyID){
        return companyRepository.findById(companyID).get().getCoupons();
    }
}
