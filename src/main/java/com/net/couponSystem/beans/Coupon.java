package com.net.couponSystem.beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int companyID;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    //    @JsonFormat( pattern = "dd/MM/yyyy")
    private Date startDate;
    //    @JsonFormat( pattern = "dd/MM/yyyy")
    private Date endDate;
    private int amount;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Image image;

    public Coupon(int companyID, Category category, String title, String description, Date startDate, Date endDate, int amount, double price, Image image) {
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }}
