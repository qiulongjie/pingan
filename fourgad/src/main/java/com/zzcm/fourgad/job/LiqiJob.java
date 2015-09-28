package com.zzcm.fourgad.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzcm.fourgad.service.ad.LiqiService;
import com.zzcm.fourgad.service.task.TaskService;

/**
 * 立其 数据传送任务
 * @author qiulongjie
 *
 */
public class LiqiJob {
	//private Logger logger = Logger.getLogger(this.getClass());
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LiqiService liqiService;
	
	/** 任务开关 */
	private static final String TASK_KEY = "LiqiJob";
	@Autowired
	private TaskService taskService;
	
    private static boolean isRunning = false;
	
	public void work()
    {
		if(isRunning){
			logger.info("==== 立其数据同步 正在运行 返回 等待下次执行====");
			return;
		}
		isRunning = true;
		try {
			if(taskService.getTaskWork(TASK_KEY)){
				logger.info("==== 立其数据同步 开始====");
				long currentTime = System.currentTimeMillis();
				doJob();
				long endTime = System.currentTimeMillis();
				logger.info("==== 立其数据同步 结束 ====耗时："+(endTime-currentTime)+"毫秒");
			}else{
				logger.info("**job stop**task_key="+TASK_KEY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			isRunning = false;
		}
    }
	
	private void doJob() {
		try {
			liqiService.synData(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
