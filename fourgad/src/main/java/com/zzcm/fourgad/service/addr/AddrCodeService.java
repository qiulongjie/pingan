package com.zzcm.fourgad.service.addr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.zzcm.fourgad.entity.NameCode;
import com.zzcm.fourgad.util.StringUtil;

@Component
public class AddrCodeService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public static Map<String,String> provinceMap = null;
    
    public static Map<String,String> cityMap = null;
    
    public String getProvinceByCode(String provinceCode)
    {   
        if(StringUtil.isEmpty(provinceCode))
        {
            return "";
        }
        if(provinceMap==null)
        {
            provinceMap = new HashMap<String,String>();
            String sql = "SELECT province_code as code,province_name as name FROM c_province";
            RowMapper<NameCode> mapper = BeanPropertyRowMapper.newInstance(NameCode.class);
            List<NameCode> list = jdbcTemplate.query(sql,mapper);
            for(NameCode bean : list)
            {
                provinceMap.put(bean.getCode(),bean.getName());
            }
        }
        return provinceMap.get(provinceCode);
    }
    
    public String getCityByCode(String cityCode)
    {   
        if(StringUtil.isEmpty(cityCode))
        {
            return "";
        }
        if(cityMap==null)
        {
            cityMap = new HashMap<String,String>();
            String sql = "SELECT city_id as code,city_name as name FROM c_city";
            RowMapper<NameCode> mapper = BeanPropertyRowMapper.newInstance(NameCode.class);
            List<NameCode> list = jdbcTemplate.query(sql,mapper);
            for(NameCode bean : list)
            {
                cityMap.put(bean.getCode(),bean.getName());
            }
        }
        return cityMap.get(cityCode);
    }
}
