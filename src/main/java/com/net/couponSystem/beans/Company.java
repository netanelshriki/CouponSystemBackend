package com.net.couponSystem.beans;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.net.couponSystem.security.ClientType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @ManyToMany(cascade = CascadeType.ALL)
    @Singular
    private List<Coupon> coupons = new ArrayList<>();

    public Company(int id, String firstName, String email, String password,List<Coupon> coupons) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.password = password;

        this.coupons = coupons;
    }
}
