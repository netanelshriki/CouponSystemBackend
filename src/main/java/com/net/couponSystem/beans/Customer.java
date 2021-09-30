package com.net.couponSystem.beans;



import com.net.couponSystem.security.ClientType;
import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@Scope("prototype")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(cascade = CascadeType.ALL)
    @Singular
    private List<Coupon> coupons = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ClientType clientType = ClientType.CUSTOMER;



    public Customer(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;


    }
}

