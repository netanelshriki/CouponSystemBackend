package com.net.couponSystem.mapper;

import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Image;
import com.net.couponSystem.repos.CouponRepository;
import com.net.couponSystem.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CouponMapper implements Mapper<Coupon, CouponDTO> {

    private final ImageService imageService;
    private final CouponRepository couponRepository;

    @Override
    public Coupon toDao(CouponDTO couponDTO) throws Exception {

        UUID uuid = couponDTO.getImageID();
        Image image = imageService.getImage(uuid);

        Coupon coupon = Coupon.builder()
                .id(couponDTO.getId())
                .category(couponDTO.getCategory())
                .amount(couponDTO.getAmount())
                .description(couponDTO.getDescription())
                .companyID(couponDTO.getCompanyID())
                .endDate(couponDTO.getEndDate())
                .startDate(couponDTO.getStartDate())
                .price(couponDTO.getPrice())
                .title(couponDTO.getTitle())
                .image(image).build();
        System.out.println("couponDTO: "+couponDTO);
        return coupon;
    }

    @Override
    public CouponDTO toDto(Coupon coupon) {

        return CouponDTO.builder()
                .id(coupon.getId())
                .companyID(coupon.getCompanyID())
                .amount(coupon.getAmount())
                .description(coupon.getDescription())
                .title(coupon.getTitle())
                .endDate(coupon.getEndDate())
                .startDate(coupon.getStartDate())
                .imageID(coupon.getImage().getId())
                .price(coupon.getPrice())
                .category(coupon.getCategory())
                .build();
    }

    @Override
    public List<Coupon> toDaoList(List<CouponDTO> couponDTOS) throws Exception {

        List<Coupon> coupons = new ArrayList<>();
        for (CouponDTO dto:couponDTOS) {
            coupons.add(toDao(dto));
        }
        return coupons;
    }

    @Override
    public List<CouponDTO> toDtoList(List<Coupon> coupons) {
        return coupons.stream().map(this::toDto).collect(Collectors.toList());
    }
}

