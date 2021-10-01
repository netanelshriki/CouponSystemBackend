package com.net.couponSystem.services;

import com.net.couponSystem.beans.Category;
import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.mapper.CouponDTO;
import com.net.couponSystem.mapper.CouponMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Scope("prototype")
@Getter
public class CompanyServiceImpl extends ClientService implements CompanyService {

    private String name;
    private int companyID;

    private final CouponMapper couponMapper;

    @Override
    public boolean login(String email, String password) throws LoginException {

        if (!companyRepository.existsByEmailAndPassword(email, password)) {
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
    public Customer getOneCustomer(int customerID) {
        return customerRepository.getOne(customerID);
    }

    public List<CouponDTO> getCouponsCompanyByID(int companyId) {
        List<CouponDTO> coupons = new ArrayList<>();
        for (Coupon coupon: couponRepository.findAll()) {
            if (coupon.getCompanyID()==companyId){
                coupons.add(couponMapper.toDto(coupon));
            }
        }
        return coupons;

//        return couponMapper.toDtoList(companyRepository.findById(companyId).get().getCoupons());
    }

    @Override
    public Company getCompanyDetails(int companyID) {
        return companyRepository.getOne(companyID);
    }

    @Override
    public List<CouponDTO> getCouponsByMaxPrice(int maxPrice, int companyId) {
//        List<Coupon> coupons = new ArrayList<>();
//        for (Coupon coupon : companyRepository.getOne(companyId).getCoupons()) {
//            if (coupon.getPrice() < maxPrice) {
//                coupons.add(coupon);
//            }
//        }
//        return coupons;

        List<CouponDTO> coupons = new ArrayList<>();
        for (Coupon coupon : couponRepository.findAll()) {
            if (coupon.getPrice() < maxPrice && coupon.getCompanyID()==companyId) {
                coupons.add(couponMapper.toDto(coupon));
            }
        }
        return coupons;
    }

    @Override
    public List<CouponDTO> getCouponsCompanyByCategory(Category category, int companyId) {
        List<CouponDTO> coupons = new ArrayList<>();
        for (Coupon coupon : couponRepository.findAll()) {
            if (coupon.getCategory().equals(category)&& coupon.getCompanyID()==companyId) {
                coupons.add(couponMapper.toDto(coupon));
            }
        }
        return coupons;
    }
}
