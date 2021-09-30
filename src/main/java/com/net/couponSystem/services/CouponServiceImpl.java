package com.net.couponSystem.services;

import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.controllers.model.CouponPayload;
import com.net.couponSystem.mapper.CouponDTO;
import com.net.couponSystem.mapper.CouponMapper;
import com.net.couponSystem.repos.CompanyRepository;
import com.net.couponSystem.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final ImageService imageService;
    private final CouponMapper couponMapper;
    private final CouponRepository couponRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CouponDTO addCoupon(CouponPayload payload) throws Exception {

        CouponDTO couponDTO = CouponDTO.builder()
                .companyID(payload.getCompanyID())
                .category(payload.getCategory())
                .price(payload.getPrice())
                .startDate(payload.getStartDate())
                .endDate(payload.getEndDate())
                .title(payload.getTitle())
                .description(payload.getDescription())
                .amount(payload.getAmount())
                .build();

        UUID uuid = imageService.addImage(payload.getImage().getBytes());

        couponDTO.setImageID(uuid);
        System.out.println("==================================================");
        System.out.println("company before adding coupon: " + companyRepository.getOne(payload.getCompanyID()));
        Coupon coupon = couponMapper.toDao(couponDTO);
        Company company = companyRepository.getOne(payload.getCompanyID());
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(coupon);
        company.setCoupons(coupons);
        companyRepository.save(company);
        System.out.println("==================================================");
        System.out.println("company after adding coupon: " + companyRepository.getOne(payload.getCompanyID()));

        return couponDTO;
    }

    public List<CouponDTO> getAllCoupons() {
        return couponMapper.toDtoList(couponRepository.findAll());
    }

    public void deleteCoupon(int id) {
        couponRepository.deleteById(id);
    }
}

