package com.net.couponSystem.services;

import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.controllers.model.CouponPayload;
import com.net.couponSystem.mapper.CouponDTO;
import com.net.couponSystem.mapper.CouponMapper;
import com.net.couponSystem.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final ImageService imageService;
    private final CouponMapper couponMapper;
    private final CouponRepository couponRepository;

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

        Coupon coupon = couponMapper.toDao(couponDTO);
        couponRepository.save(coupon);
        return couponDTO;
    }

    public List<CouponDTO> getAllCoupons(){
        return couponMapper.toDtoList(couponRepository.findAll());
    }

    public void deleteCoupon(int id){
        couponRepository.deleteById(id);
    }
}

