package com.net.couponSystem.clr;

import com.net.couponSystem.beans.*;
import com.net.couponSystem.repos.AdminRepository;
import com.net.couponSystem.repos.CompanyRepository;
import com.net.couponSystem.repos.CouponRepository;
import com.net.couponSystem.repos.CustomerRepository;
import com.net.couponSystem.security.ClientType;
import com.net.couponSystem.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private final AdminRepository adminRepository;

    @Override
    public void run(String... args) throws Exception {

        Coupon c1 = Coupon.builder()
                .amount(10)
                .category(Category.FOOD)
                .companyID(1)
                .description("12% off")
                .startDate(DateUtils.toSqlDate(LocalDateTime.now()))
                .endDate(DateUtils.toSqlDate(LocalDateTime.now().plusWeeks(1)))
                .image("cola.jpg")
                .title("12% off for coca cola")
                .price(10)
                .build();

        Company cocaCola = Company.builder()
                .firstName("coca cola")
                .password("coca123")
                .email("coca123@gmail.com")
                .clientType(ClientType.COMPANY)
                .coupon(c1)
                .build();


        companyRepository.save(cocaCola);
        companyRepository.findAll().forEach(company -> System.out.println(company));

        Customer ben = Customer.builder()
                .firstName("ben")
                .lastName("miller")
                .email("ben@gmail.com")
                .password("12345")
                .coupon(c1)
                .clientType(ClientType.CUSTOMER)
                .build();

        Admin admin = Admin.builder()
                .clientType(ClientType.ADMIN)
                .password("admin")
                .email("admin@admin.com")
                .firstName("admin")
                .build();

        customerRepository.save(ben);
        adminRepository.save(admin);

    }
}
