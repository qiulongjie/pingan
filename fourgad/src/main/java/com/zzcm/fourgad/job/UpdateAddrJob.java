package com.zzcm.fourgad.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzcm.fourgad.service.ad.UpdateAddrService;
import com.zzcm.fourgad.service.task.TaskService;

/**
 * 通过手机号更新省份，城市信息
 * @author wancan
 */
public class UpdateAddrJob {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** 任务开关 */
	private static final String TASK_KEY = "UpdateAddrJob";
	@Autowired
	private TaskService taskService;
	@Autowired
	private UpdateAddrService updateAddrService;
	
	private static boolean isRunning = false;
	
	public void work()
    {
		if(isRunning){
			logger.info("==== 根据手机号更新地址任务UpdateAddrJob 正在运行 返回 等待下次执行====");
			return;
		}
		isRunning = true;
		try {
			if(taskService.getTaskWork(TASK_KEY)){
				logger.info("===根据手机号更新地址任务UpdateAddrJob==Send====");
				updateAddrService.updateAddr(100);
				logger.info("===根据手机号更新地址任务UpdateAddrJob==End====");
			}else{
				logger.info("**job stop**task_key="+TASK_KEY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			isRunning = false;
		}
    }
	
}
