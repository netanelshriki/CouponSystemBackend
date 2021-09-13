package com.net.couponSystem.repos;

import com.net.couponSystem.beans.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    boolean existsByEmailAndPassword(String email, String password);
}
