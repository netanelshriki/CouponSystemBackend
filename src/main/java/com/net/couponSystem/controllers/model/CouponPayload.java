package com.net.couponSystem.controllers.model;

import com.net.couponSystem.beans.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponPayload {
 private int companyID;
 private Category category;
 private String title;
 private String description;
 //  @DateTimeFormat(pattern = "dd-MM-yyyy")
 private Date startDate;
 // @DateTimeFormat(pattern = "dd-MM-yyyy")
 private Date endDate;
 private int amount;
 private double price;
 private MultipartFile image;
}
