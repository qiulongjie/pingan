package com.zzcm.fourgad.entity;


/**
 * 地址bean
 * 
 * @author tangl
 */
public class AddrBean
{
	private String country;// 国家
	private String provinceCode;//省代号
	private Integer cityId;// 市编号
	private String street;// 街道
	private String streetNumber;// 街道号
	private String postCode;// 邮编号
	private String city;

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}
	
	/**
	 * 返回省代号
	 * @return 省代号
	 */
	public String getProvinceCode()
	{
		return provinceCode;
	}
	
	/**
	 * 设置省代号
	 * @return 省代号
	 */
	public void setProvinceCode(String provinceCode)
	{
		this.provinceCode=provinceCode;
	}
	
	/**
	 * 返回城市编号
	 * @return 城市编号
	 */
	public Integer getCityId()
	{
		return cityId;
	}
	
	/**
	 * 设置城市编号
	 * @return 城市编号
	 */
	public void setCityId(Integer cityId)
	{
		this.cityId=cityId;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getStreetNumber()
	{
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber)
	{
		this.streetNumber = streetNumber;
	}

	public String getPostCode()
	{
		return postCode;
	}

	public void setPostCode(String postCode)
	{
		this.postCode = postCode;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(this.country).append(",");
		sb.append(this.provinceCode).append(",");
		sb.append(this.cityId).append(",");
		sb.append(this.street).append(",");
		sb.append(this.streetNumber).append(",");
		sb.append(this.postCode);
		return sb.toString();
	}

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }
	
}
