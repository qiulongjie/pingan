package com.zzcm.fourgad.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.zzcm.fourgad.entity.Sms;

public interface SmsDao extends PagingAndSortingRepository<Sms, Long>, JpaSpecificationExecutor<Sms>{

}
