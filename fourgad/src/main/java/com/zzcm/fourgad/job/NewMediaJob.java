package com.zzcm.fourgad.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzcm.fourgad.service.ad.NewMediaService;
import com.zzcm.fourgad.service.task.TaskService;

/**
 * 同步数据给360新媒体<br/>
 * http://sx.360doo.com/JSI/foreign/colligateYwxHandler.ashx
 * @author wancan
 */
public class NewMediaJob {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NewMediaService newMediaService;
	
	/** 任务开关 */
	private static final String TASK_KEY = "NewMediaJob";
	@Autowired
	private TaskService taskService;
	
	private static boolean isRunning = false;
	
	public void work()
    {
		if(isRunning){
			logger.info("==== 360新媒体NewMediaJob 正在运行 返回 等待下次执行====");
			return;
		}
		isRunning = true;
		try {
			if(taskService.getTaskWork(TASK_KEY)){
				logger.info("===360新媒体NewMediaJob==Send====");
				long currentTime = System.currentTimeMillis();
				newMediaService.sendData(100);
				long endTime = System.currentTimeMillis();
				logger.info("===360新媒体NewMediaJob==End====耗时："+(endTime-currentTime)+"毫秒");
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
