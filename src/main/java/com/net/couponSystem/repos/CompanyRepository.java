package com.net.couponSystem.repos;

import com.net.couponSystem.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByFirstNameOrEmail(String name, String email);

    boolean existsByFirstNameAndPassword(String name, String password);

    Company findByEmailAndPassword(String email, String password);
}
