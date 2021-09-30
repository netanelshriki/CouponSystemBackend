package com.net.couponSystem.controllers.model;

import com.net.couponSystem.beans.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCouponsByCategory {
    private Category category;
    private int customerId;
}
