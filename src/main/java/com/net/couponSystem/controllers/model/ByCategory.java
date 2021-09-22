package com.net.couponSystem.controllers.model;

import com.net.couponSystem.beans.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ByCategory {
    private Category category;
    private int companyId;
}
