package com.zzcm.fourgad.service.ad;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zzcm.fourgad.entity.AntiCheatingBean;

@Component
@Transactional
public class AntiCheatingService
{

    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void Add(AntiCheatingBean bean){
        String sql = "insert into ad_cheatinganalysis " +
        		"(orderID,pubcode,ip,phone,phoneProvince,phoneCity,ipProvince,ipCity,cityFlag,provinceFlag,vtime) " +
        		"values " +
        		"(?,?,?,?,?,?,?,?,?,?,?)";
        Object o [] = {bean.getOrderID(),bean.getPubcode(),bean.getIp(),bean.getPhone(),
                bean.getPhoneProvince(),bean.getPhoneCity(),bean.getIpProvince(),bean.getIpCity(),
                bean.getCityFlag(),bean.getProvinceFlag(),bean.getVtime()};
        try
        {
            jdbcTemplate.update(sql,o);
        }
        catch (DataAccessException e)
        {
            e.printStackTrace();
        }
    }
    
    public void deleteCountResult(String pubcode,String vtime)
    {
        String sql = "delete from ad_cheatingcount where pubcode=? and vtime=?";
        Object o [] = {pubcode,vtime};
        jdbcTemplate.update(sql,o);
    }
    
    public void saveCountResult(String pubcode,String vtime,double p_count,double c_count,int total){
        String sql = "insert into ad_cheatingcount " +
                "(pubcode,p_count,c_count,total,vtime) " +
                "values " +
                "(?,?,?,?,?)";
        Object o [] = {pubcode,p_count,c_count,total,vtime};
        try
        {
            jdbcTemplate.update(sql,o);
        }
        catch (DataAccessException e)
        {
            e.printStackTrace();
        }
    }
    
    public void update(AntiCheatingBean bean){
        String sql = "update ad_cheatinganalysis set vtime= ? where orderID= ?";
        Object o [] = {bean.getVtime(),bean.getOrderID()};
        try
        {
            jdbcTemplate.update(sql,o);
        }
        catch (DataAccessException e)
        {
            e.printStackTrace();
        }
    }
    
    public Long getMaxOrderID()
    {
        Long maxId = 0L;
        String sql = "SELECT MAX(orderID) FROM ad_cheatinganalysis";
        maxId = jdbcTemplate.queryForObject(sql,Long.class);
        if(maxId==null)
        {
            maxId = 0L;
        }
        return maxId;
    }
    
    public String getJobDate()
    {
        String jobDate = "";
        String sql = "SELECT vtime FROM ad_cheatingjob WHERE id=1";
        jobDate = jdbcTemplate.queryForObject(sql,String.class);
        
        return jobDate;
    }
    
    public String updateJobDate(String jobDate)
    {
        String sql = "update ad_cheatingjob set vtime=? WHERE id=1";
        Object o [] = {jobDate};
        try
        {
            jdbcTemplate.update(sql,o);
        }
        catch (DataAccessException e)
        {
            e.printStackTrace();
        }
        
        return jobDate;
    }
    
    public List<String> getPubDateForJob(String jobDate)
    {
        String startDate = jobDate+" 00:00:00";
        String endDate = jobDate + " 23:59:59";
        String sql = "SELECT DISTINCT(pubcode) FROM ad_cheatinganalysis WHERE vtime>= ? AND vtime<= ?";
        Object o [] = {startDate,endDate};
        List<String> pubCodeList = jdbcTemplate.queryForList(sql,o,String.class);
        return pubCodeList;
    }
    
    public Integer getCityCount(String pubCode,String jobDate)
    {
        String startDate = jobDate+" 00:00:00";
        String endDate = jobDate + " 23:59:59";
        String sql = "SELECT COUNT(1) AS cnt FROM  ad_cheatinganalysis WHERE pubcode=? AND vtime>=? AND vtime<=? AND cityFlag = 1";
        Object o [] = {pubCode,startDate,endDate};
        Integer count = jdbcTemplate.queryForObject(sql,o,Integer.class);
        return count;
    }
    
    public Integer getProvinceCount(String pubCode,String jobDate)
    {
        String startDate = jobDate+" 00:00:00";
        String endDate = jobDate + " 23:59:59";
        String sql = "SELECT COUNT(1) AS cnt FROM  ad_cheatinganalysis WHERE pubcode=? AND vtime>=? AND vtime<=? AND provinceFlag = 1";
        Object o [] = {pubCode,startDate,endDate};
        Integer count = jdbcTemplate.queryForObject(sql,o,Integer.class);
        return count;
    }
    
    public Integer getTotalCount(String pubCode,String jobDate)
    {
        String startDate = jobDate+" 00:00:00";
        String endDate = jobDate + " 23:59:59";
        String sql = "SELECT COUNT(1) AS cnt FROM  ad_cheatinganalysis WHERE pubcode=? AND vtime>=? AND vtime<=?";
        Object o [] = {pubCode,startDate,endDate};
        Integer count = jdbcTemplate.queryForObject(sql,o,Integer.class);
        return count;
    }
}
