package com.net.couponSystem.repos;

import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    boolean existsById(int id);

    boolean existsByEmailAndPassword(String email, String password);

    Customer findByEmailAndPassword(String email, String password);


    @Query(value = "SELECT customer FROM customers where name = ?1", nativeQuery = true)
    Customer findCouponsByName(String name);
}
