package com.korit.senicare.repository;

import org.springframework.data.jpa.repository.JpaRepository; //jparepository import
import org.springframework.stereotype.Repository;

import com.korit.senicare.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{
    
}
