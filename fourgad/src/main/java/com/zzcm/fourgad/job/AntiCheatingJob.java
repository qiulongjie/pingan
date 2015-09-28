package com.zzcm.fourgad.job;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzcm.fourgad.entity.AddrBean;
import com.zzcm.fourgad.entity.AntiCheatingBean;
import com.zzcm.fourgad.entity.OrdLogs;
import com.zzcm.fourgad.service.ad.AdService;
import com.zzcm.fourgad.service.ad.AntiCheatingService;
import com.zzcm.fourgad.service.addr.AddrCodeService;
import com.zzcm.fourgad.service.task.TaskService;
import com.zzcm.fourgad.util.IAddrService;
import com.zzcm.fourgad.util.StringUtil;


public class AntiCheatingJob
{
    //private Logger logger = Logger.getLogger(this.getClass());
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private IAddrService addrService;
    
    @Autowired
    private AddrCodeService addrCodeService;
    
    @Autowired
    private AntiCheatingService antiCheatingService;
    
    @Autowired
    private AdService adService;
    
    private static boolean isRunning = false;
    
    /** 任务开关 */
	private static final String TASK_KEY = "AntiCheatingJob";
	@Autowired
	private TaskService taskService;
    
    public void work()
    {
    	if(taskService.getTaskWork(TASK_KEY)){
    		if(!isRunning)
    		{
    			logger.info("反作弊分析开始..............");
    			Date startDate = new Date();
    			
    			isRunning = true;
    			boolean analysisFlag = true;
    			
    			while(analysisFlag)
    			{
    				long maxOrderId = antiCheatingService.getMaxOrderID();
    				List<OrdLogs> list= adService.getOrds(maxOrderId,20);
    				if(list==null || list.size()==0)
    				{
    					analysisFlag = false;
    				}
    				else
    				{
    					for(OrdLogs order : list)
    					{
    						analysisRecord(order);
    					}
    				}
    				//analysisFlag = false;
    			}
    			
    			isRunning = false;
    			
    			logger.info("反作弊分析结束..............");
    			Date endDate = new Date();
    			logger.info("总耗时:"+(endDate.getTime()-startDate.getTime())+"毫秒");
    		}
    		else
    		{
    			logger.info("反作弊分析正在进行中,不开启新任务!");
    		}
    	}else{
			logger.info("**job stop**task_key="+TASK_KEY);
		}
    }
    
    private void analysisRecord(OrdLogs order)
    {
        AddrBean IPAddr = null;
        AddrBean PhoneAddr = null;

        String IPAddrStr = "";
        String IPProvince = "";
        String IPCity = "";
        
        IPAddr = getIPAddr(order.getIpaddr());
        if(!StringUtil.isEmpty(IPAddrStr))
        {
            
        }
        if(IPAddr!=null)
        {
            IPProvince = IPAddr.getProvinceCode();
            IPCity = IPAddr.getCity();
            if(IPCity==null)
            {
                IPCity = "";
            }
            if(IPProvince==null)
            {
                IPProvince = "";
            }
        }
        PhoneAddr = Phone2Addr(order.getPhone());
        if(PhoneAddr.getCity()==null)
        {
            PhoneAddr.setCity("");
        }
        if(PhoneAddr.getProvinceCode()==null)
        {
            PhoneAddr.setProvinceCode("");
        }
        
        AntiCheatingBean bean = new AntiCheatingBean();
        
        bean.setIp(order.getIpaddr());
        bean.setIpCity(IPCity);
        bean.setIpProvince(IPProvince);
        bean.setOrderID(order.getId());
        bean.setPhone(order.getPhone()==null ? "" : order.getPhone());
        bean.setPhoneCity(PhoneAddr.getCity());
        bean.setPhoneProvince(PhoneAddr.getProvinceCode());
        bean.setPubcode(order.getPubcode()==null ? "" : order.getPubcode());
        bean.setProvinceFlag(0);
        bean.setVtime(order.getSelfVtime());
        if(IPProvince!=null && bean.getPhoneProvince()!=null)
        {
            if(IPProvince.indexOf(bean.getPhoneProvince())!=-1)
            {
                bean.setProvinceFlag(1);
            }
        }
        else
        {
            //System.out.println("IPProvince="+IPProvince+",PhoneProvince="+bean.getPhoneProvince());
        }
        bean.setCityFlag(0);
        if(IPCity !=null && bean.getPhoneCity()!=null)
        {
            if(IPCity.indexOf(bean.getPhoneCity())!=-1)
            {
                bean.setCityFlag(1);
            }
        }
        else
        {
            //System.out.println("IPCity="+IPCity+",PhoneCity="+bean.getPhoneCity());
        }
        
        antiCheatingService.Add(bean);
        
    }
    
    private AddrBean getIPAddr(String ip)
    {
        AddrBean IPAddr = new AddrBean();
        String s = addrService.getAddr(null,ip);
        if(!StringUtil.isEmpty(s))
        {
            IPAddr = JsonUtil.fromJson(s,AddrBean.class);
            String IPProvince = addrCodeService.getProvinceByCode(IPAddr.getProvinceCode());
            String IPCity = addrCodeService.getCityByCode(String.valueOf(IPAddr.getCityId()));
            if(IPCity==null)
            {
                IPCity = "";
            }
            if(IPProvince==null)
            {
                IPProvince = "";
            }
            IPAddr.setProvinceCode(IPProvince);
            IPAddr.setCity(IPCity);
        }
        if(StringUtil.isEmpty(IPAddr.getProvinceCode()) || StringUtil.isEmpty(IPAddr.getCity()))
        {
            IPAddr = ip2Addr(ip);
        }
        return IPAddr;
    }
    
    private AddrBean Phone2Addr(String phoneNumber)
    {
        String url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=%s";
        url = String.format(url, phoneNumber);
        AddrBean bean = new AddrBean();
        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements els = doc.getElementsByClass("tdc2");
//            System.out.println("归属地：" + els.get(1).text());
//            System.out.println("类型：" + els.get(2).text());
//            System.out.println("区号：" + els.get(3).text());
//            System.out.println("邮编：" + els.get(4).text().substring(0, 6));
            String addr = els.get(1).text();
            if(!StringUtil.isEmpty(addr))
            {
                String [] addrs = addr.split(" ");
                if(addrs!=null)
                {
                    String province = "";
                    String city = "";
                    if(addrs.length>=2)
                    {
                        province = addrs[0];
                        city = addrs[1];
                    }
                    else if(addrs.length==1)
                    {
                        province = addrs[0];
                        city = addrs[0];
                    }
                    bean.setProvinceCode(province);
                    bean.setCity(city);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return bean;
    }
    
    private AddrBean ip2Addr(String ip)
    {
        String url = "http://www.ip138.com/ips138.asp?action=ip&ip=%s";
        url = String.format(url, ip);
        AddrBean bean = new AddrBean();;
        String addr = "";
        String split = "";
        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements els = doc.getElementsByTag("li");
//            System.out.println(els.toString());
            if(els.size()==2)
            {
                String addr1 = els.get(0).text();
                String addr2 = els.get(1).text();
                addr1 = addr1.substring(addr1.indexOf("：")+1);
                addr2 = addr2.substring(addr2.indexOf("：")+1);
                String [] addrs1 = addr1.split(" ");
                String [] addrs2 = addr2.split(" ");
                if(addrs1.length>1)
                {
                    addr1 = addrs1[0];
                }
                if(addrs2.length>1)
                {
                    addr2 = addrs2[0];
                }
                addr = addr1;
                if(addr1.indexOf("省")==-1 && addr1.indexOf("自治区")==-1 && addr1.indexOf("市")==-1 && addr1.indexOf("特别行政区")==-1)
                {
                    if(addr2.indexOf("省")!=-1 && addr2.indexOf("自治区")!=-1 && addr2.indexOf("市")!=-1 && addr2.indexOf("特别行政区")!=-1)
                    {
                        addr = addr2;
                    }
                }
            }
            if(addr.indexOf("省")!=-1)
            {
                split = "省";
            }
            else if(addr.indexOf("自治区")!=-1)
            {
                split = "自治区";
            }
            else if(addr.indexOf("市")!=-1)
            {
                split = "市";
            }
            else if(addr.indexOf("特别行政区")!=-1)
            {
                split = "特别行政区";
            }
            if(!StringUtil.isEmpty(addr) && !StringUtil.isEmpty(split))
            {
                String [] addrs = addr.split(split);
                if(addrs!=null)
                {
                    String province = "";
                    String city = "";
                    if(addrs.length==2)
                    {
                        province = addrs[0];
                        city = addrs[1];
                    }
                    else if(addrs.length==1)
                    {
                        province = addrs[0];
                        city = addrs[0];
                    }
                    if(province.indexOf("广西")!=-1)
                    {
                        province = "广西";
                    }
                    else if(province.indexOf("宁夏")!=-1)
                    {
                        province = "宁夏";
                    }
                    else if(province.indexOf("新疆")!=-1)
                    {
                        province = "新疆";
                    }
                    bean.setProvinceCode(province);
                    bean.setCity(city);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return bean;
    }
    
    public static void main(String [] args){
        AntiCheatingJob j = new AntiCheatingJob();
        AddrBean bean= j.ip2Addr("115.168.55.118");
        System.out.println(bean.getProvinceCode());
        System.out.println(bean.getCity());
    }
}
