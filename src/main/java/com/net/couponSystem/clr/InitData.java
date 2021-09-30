package com.net.couponSystem.clr;

import com.net.couponSystem.beans.*;
import com.net.couponSystem.repos.AdminRepository;
import com.net.couponSystem.repos.CompanyRepository;
import com.net.couponSystem.repos.CouponRepository;
import com.net.couponSystem.repos.CustomerRepository;
import com.net.couponSystem.security.ClientType;
import com.net.couponSystem.services.CompanyService;
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
    private final CompanyService companyService;

    @Override
    public void run(String... args) throws Exception {

//        Coupon c1 = Coupon.builder()
//                .amount(10)
//                .category(Category.FOOD)
//                .companyID(1)
//                .description("12% off")
//                .startDate(DateUtils.toSqlDate(LocalDateTime.now()))
//                .endDate(DateUtils.toSqlDate(LocalDateTime.now().plusWeeks(1)))
//                //.image("cola.jpg")
//                .title("12% off for coca cola")
//                .price(10)
//                .build();
//
//        Coupon c2 = Coupon.builder()
//                .amount(10)
//                .category(Category.ELECTRICITY)
//                .companyID(2)
//                .description("5% off")
//                .startDate(DateUtils.toSqlDate(LocalDateTime.now()))
//                .endDate(DateUtils.toSqlDate(LocalDateTime.now().plusWeeks(1)))
//              //  .image("tesla.jpg")
//                .title("5% off for tesla car")
//                .price(10)
//                .build();

        Company cocaCola = Company.builder()
                .firstName("coca cola")
                .password("coca123")
                .email("coca123@gmail.com")
                .clientType(ClientType.COMPANY)
             //   .coupon(c1)
                .build();

        Company tesla = Company.builder()
                .firstName("tesla")
                .password("tesla")
                .email("tesla@gmail.com")
                .clientType(ClientType.COMPANY)
             //   .coupon(c2)
                .build();


        companyRepository.save(cocaCola);
        companyRepository.save(tesla);

        Customer ben = Customer.builder()
                .firstName("ben")
                .lastName("miller")
                .email("ben@gmail.com")
                .password("12345")
//                .coupon(c2)
                .clientType(ClientType.CUSTOMER)
                .build();

        Customer john = Customer.builder()
                .firstName("john")
                .lastName("robinson")
                .email("john@gmail.com")
                .password("12345")
                .clientType(ClientType.CUSTOMER)
                .build();

        Customer scott = Customer.builder()
                .firstName("scott")
                .lastName("robinson")
                .email("scott@gmail.com")
                .password("12345")
                .clientType(ClientType.CUSTOMER)
                .build();

        Customer amili = Customer.builder()
                .firstName("amili")
                .lastName("robinson")
                .email("amili@gmail.com")
                .password("12345")
                .clientType(ClientType.CUSTOMER)
                .build();

        Admin admin = Admin.builder()
                .clientType(ClientType.ADMIN)
                .password("admin")
                .email("admin@admin.com")
                .firstName("admin")
                .build();

        customerRepository.save(ben);
        customerRepository.save(scott);
        customerRepository.save(amili);
        customerRepository.save(john);
        adminRepository.save(admin);


    }
}
