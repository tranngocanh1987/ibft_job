package com.infoplus.ibft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoplus.ibft.entity.IbftHistory;

public interface IbftHistoryReps extends JpaRepository<IbftHistory, Long>{

}
