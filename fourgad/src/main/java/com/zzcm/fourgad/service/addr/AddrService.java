package com.zzcm.fourgad.service.addr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class AddrService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public static Map<String,String> provinceMap = null;
    
    public String getProvinceCode(String province)
    {
        String provinceCode = "";
        if(provinceMap==null)
        {
            String sql = "SELECT * FROM c_province";
            List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        }
        return provinceCode;
    }
}
