package com.infoplus.ibft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoplus.ibft.entity.NapasReconciliationTransaction;


public interface NapasReconciliationTransResp extends JpaRepository<NapasReconciliationTransaction, Long>{
}
