package com.korit.senicare.repository;

import org.springframework.data.jpa.repository.JpaRepository; //jparepository import
import org.springframework.stereotype.Repository;

import com.korit.senicare.entity.CareRecordEntity;

@Repository
public interface CareRecordRepository extends JpaRepository<CareRecordEntity,Integer>{
    
}
