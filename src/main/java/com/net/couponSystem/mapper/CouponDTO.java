package com.net.couponSystem.mapper;

import com.net.couponSystem.beans.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CouponDTO {
    private int id;
    private int companyID;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private UUID imageID;
}

