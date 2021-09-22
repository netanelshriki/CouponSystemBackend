package com.net.couponSystem.controllers.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ByMazPrize {
    private  int companyId;
   private int prize;
}
