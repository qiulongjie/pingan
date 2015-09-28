package com.zzcm.fourgad.job;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.service.task.TaskService;
import com.zzcm.fourgad.util.WebUtil;

/**
 * 同步数据给荣时代<br/>
 * http://jixianghutong.com/api/data.ashx
 * @author shilei
 * @modify qiulongjie
 */
public class QuartzJob {
	//private Logger logger = Logger.getLogger(this.getClass());
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/** 答案生成策略 */
	private static final int[] ANSWER2_3= {3,3};
	/** 问卷类型 */
	private static final String ANSWERTYPE10 = "10";
	private static final char CHAR_A = 'A';
	/** json转换类型  */
	private static final Type listType = new TypeToken<List<RetBean>>() {}.getType(); 
	/** 同步接口 */
	private static final String SEND_URL = "http://jixianghutong.com/api/data.ashx";
	@Autowired
	private AdService adService;
	
	/** 任务开关 */
	private static final String TASK_KEY = "QuartzJob";
	@Autowired
	private TaskService taskService;
	
    private static boolean isRunning = false;
	
	public void work()
    {
		if(isRunning){
			logger.info("==== QuartzJob 正在运行 返回 等待下次执行====");
			return;
		}
		isRunning = true;
		try {
			if(taskService.getTaskWork(TASK_KEY)){
				logger.info("===QuartzJob==Send====");
				sendData(SEND_URL);
				logger.info("===QuartzJob==End====");
			}else{
				logger.info("**job stop**task_key="+TASK_KEY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			isRunning = false;
		}
    }
	
	/**
	 * 同步数据
	 * @param url
	 */
	public void sendData(String url){
		List<Map<String,Object>> list = adService.getOrdsByFlag(0, 100);
		for(Map<String,Object> l:list){
			Long id = new Long(Integer.parseInt(l.get("id").toString()));
			PostBean bean = new PostBean();
			bean.setName(l.get("uname").toString());
			bean.setSex(l.get("ddlSex").toString());
			bean.setMobile(l.get("phone").toString());
			bean.setBirthday(l.get("birthday").toString());
			bean.setIp(l.get("ipaddr").toString());
			
			String pubcode = "",pcontent="";
			if(l.get("pubcode")==null){
				pubcode = "A7375893";
			}else{
				pubcode = l.get("pubcode").toString();
			}
			if(l.get("pcontent")==null){
				pcontent = "";
			}else{
				pcontent = l.get("pcontent").toString();
			}
			if(pcontent.equals("") || pcontent.equals("null,null,null,null")){
				pcontent = createAnswer(ANSWERTYPE10,ANSWER2_3);
			}
			bean.setPubCode(pubcode);
			bean.setRemark(pcontent); 
			Gson gson = new Gson();
	    	String json = gson.toJson(bean); 
	    	logger.info(json);
	    	String str = WebUtil.postJOSN(url,json);
	    	logger.info(str);
	    	if( null != str){
	    		List<RetBean> rets = JsonUtil.fromJson(str, listType);
	    		if(rets != null && rets.size()>0){
	    			RetBean ret = rets.get(0);
	    			if(ret!=null){
	    				String code = ret.getRetCode();
	    				String msg = ret.getRetMsg();
	    				if(code==null){
	    					code = "0";
	    				}
	    				if(code.equals("1")){
	    					adService.updOrdLogs(id,1,msg);
	    				}else{
	    					adService.updOrdLogs(id,2,msg);
	    				}
	    			}
	    		}
	    	}
	    	
		}
	}
	
	/**
	 * 生成Remark字段的数据  对应问卷答案
	 * @param type 问卷类型
	 * @param creator
	 * @return
	 */
	public static String createAnswer(String type,int[] creator){
		return type+":"+createAnswer(creator);
	}
	
	/**
	 * 随机创建答案 
	 * @param creator 数组的长度为答案的个数，值为从A开始的答案类型  如{3,3}数据 答案为2个以ABC三种答案类型的
	 * @return
	 */
	public static String createAnswer(int[] creator){
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i : creator){
			sb.append((char)(CHAR_A + random.nextInt(i))).append(",");
		}
		sb.delete(sb.length()-1,sb.length());
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		//System.out.println(createAnswer("10",new int[]{3,3}));
		Random random = new Random();
		System.out.println(random.nextInt(3));
	}
}
