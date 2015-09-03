package com.zzcm.fourgad.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzcm.fourgad.service.ad.PinganService;
import com.zzcm.fourgad.web.ad.pingController;


public class PinganJob {
	//private Logger logger = Logger.getLogger(this.getClass());
	private static Logger logger = LoggerFactory.getLogger(pingController.class);
	@Autowired
	private PinganService pinganService;
	public void work()
    {
		logger.info("==== 发送数据给平安 开始====");
		long currentTime = System.currentTimeMillis();
		doJob();
		long endTime = System.currentTimeMillis();
		logger.info("==== 发送数据给平安 结束 ====耗时："+(endTime-currentTime)+"毫秒");
    }
	
	private void doJob() {
		try {
			pinganService.synData(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
