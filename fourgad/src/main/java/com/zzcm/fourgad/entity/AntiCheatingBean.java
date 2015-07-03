package com.zzcm.fourgad.entity;

public class AntiCheatingBean
{

    private Long id;
    
    private Long orderID;
    
    private String pubcode;
    
    private String phone;
    
    private String ip;
    
    private String phoneProvince;
    
    private String phoneCity;
    
    private String ipProvince;
    
    private String ipCity;
    
    private int cityFlag;
    
    private int provinceFlag;
    
    private String vtime;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getOrderID()
    {
        return orderID;
    }

    public void setOrderID(Long orderID)
    {
        this.orderID = orderID;
    }

    public String getPubcode()
    {
        return pubcode;
    }

    public void setPubcode(String pubcode)
    {
        this.pubcode = pubcode;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getPhoneProvince()
    {
        return phoneProvince;
    }

    public void setPhoneProvince(String phoneProvince)
    {
        this.phoneProvince = phoneProvince;
    }

    public String getPhoneCity()
    {
        return phoneCity;
    }

    public void setPhoneCity(String phoneCity)
    {
        this.phoneCity = phoneCity;
    }

    public String getIpProvince()
    {
        return ipProvince;
    }

    public void setIpProvince(String ipProvince)
    {
        this.ipProvince = ipProvince;
    }

    public String getIpCity()
    {
        return ipCity;
    }

    public void setIpCity(String ipCity)
    {
        this.ipCity = ipCity;
    }

    public int getCityFlag()
    {
        return cityFlag;
    }

    public void setCityFlag(int cityFlag)
    {
        this.cityFlag = cityFlag;
    }

    public int getProvinceFlag()
    {
        return provinceFlag;
    }

    public void setProvinceFlag(int provinceFlag)
    {
        this.provinceFlag = provinceFlag;
    }

    public String getVtime()
    {
        return vtime;
    }

    public void setVtime(String vtime)
    {
        this.vtime = vtime;
    }
    
}
