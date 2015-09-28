package com.zzcm.fourgad.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzcm.fourgad.service.ad.AntiCheatingService;
import com.zzcm.fourgad.service.task.TaskService;
import com.zzcm.fourgad.util.DateUtil;
import com.zzcm.fourgad.util.StringUtil;


public class AntiCheatingJob2
{
    //private Logger logger = Logger.getLogger(this.getClass());
    private static Logger logger = LoggerFactory.getLogger(AntiCheatingJob2.class);
    
    @Autowired
    private AntiCheatingService antiCheatingService;
    
    private static boolean isRunning = false;
    
    /** 任务开关 */
	private static final String TASK_KEY = "AntiCheatingJob2";
	@Autowired
	private TaskService taskService;
    
    public void work()
    {
    	if(taskService.getTaskWork(TASK_KEY)){
    		if(!isRunning)
    		{
    			logger.info("反作弊分析统计数据统计开始..............");
    			Date startDate = new Date();
    			
    			isRunning = true;
    			boolean analysisFlag = true;
    			
    			while(analysisFlag)
    			{
    				//获得上次分析任务的时间
    				String jobDateBefore = antiCheatingService.getJobDate();
    				//得到下个分析任务的时间
    				String jobDate = DateUtil.getNextDayDate(jobDateBefore,DateUtil.YYYY_MM_DD);
    				
    				if(!StringUtil.isEmpty(jobDate))
    				{
    					//判断任务时间,如果任务时间在今天之前,则执行,否则不执行
    					boolean isAfter = DateUtil.isAfter(jobDate,DateUtil.YYYY_MM_DD);
    					if(isAfter)
    					{
    						List<String> pubCodeList= antiCheatingService.getPubDateForJob(jobDate);
    						if(pubCodeList!=null && pubCodeList.size()>0)
    						{
    							for(String pubCode : pubCodeList)
    							{
    								count(pubCode,jobDate);
    							}
    						}
    						updateJobDate(jobDate);
    					}
    					else
    					{
    						analysisFlag = false;
    					}
    				}
    				//analysisFlag = false;
    			}
    			
    			isRunning = false;
    			
    			logger.info("反作弊分析统计数据统计结束..............");
    			Date endDate = new Date();
    			logger.info("总耗时:"+(endDate.getTime()-startDate.getTime())+"毫秒");
    		}
    		else
    		{
    			logger.info("反作弊分析统计数据统计正在进行中,不开启新任务!");
    		}
    	}else{
			logger.info("**job stop**task_key="+TASK_KEY);
		}
    }
    
    private void count(String pubCode,String jobDate)
    {
        //如果存在原先数据,先删除原先数据
        antiCheatingService.deleteCountResult(pubCode,jobDate);
        
        int total = antiCheatingService.getTotalCount(pubCode,jobDate);
        int cityCount = antiCheatingService.getCityCount(pubCode,jobDate);
        int provinceCount = antiCheatingService.getProvinceCount(pubCode,jobDate);
        
//        double cityRate = (div(String.valueOf(cityCount),String.valueOf(total),4))*100;
//        double pRate = (div(String.valueOf(provinceCount),String.valueOf(total),4))*100;
        
        antiCheatingService.saveCountResult(pubCode,jobDate,provinceCount,cityCount,total);
    }
    
    private void updateJobDate(String jobDate)
    {
        antiCheatingService.updateJobDate(jobDate);
    }
    
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    private double div(String v1, String v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static void main(String [] args)
    {
        AntiCheatingJob2 j = new AntiCheatingJob2();
        int cityCount = 112;
        int provinceCount = 233;
        int total = 443;
        double cityRate = j.div(String.valueOf(cityCount),String.valueOf(total),4)*100;
        double pRate = j.div(String.valueOf(provinceCount),String.valueOf(total),4);
        System.out.println(cityRate);
    }
}
