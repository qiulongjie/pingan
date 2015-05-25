package com.zzcm.fourgad.service.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zzcm.fourgad.entity.Sms;
import com.zzcm.fourgad.repository.SmsDao;

@Component
@Transactional
public class SmsService {
	@Autowired
	private SmsDao smsDao;
	public void saveSms(Sms entity){
		smsDao.save(entity);
	}
}
