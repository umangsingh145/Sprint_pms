package com.pms.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pms.in.entities.PensionDetails;

@Repository
public interface ProcessPensionModuleRepository extends JpaRepository<PensionDetails, Integer> {

}
