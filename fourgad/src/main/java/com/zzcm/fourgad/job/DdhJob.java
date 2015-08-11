package com.zzcm.fourgad.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzcm.fourgad.service.ad.DdhService;


public class DdhJob {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DdhService ddhService;
	public void work()
    {
		logger.info("==== 大都会数据同步 开始====");
		long currentTime = System.currentTimeMillis();
		doJob();
		long endTime = System.currentTimeMillis();
		logger.info("==== 大都会数据同步 结束 ====耗时："+(endTime-currentTime)+"毫秒");
    }
	
	private void doJob() {
		try {
			ddhService.synData(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
