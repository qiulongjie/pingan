package com.zzcm.fourgad.entity;

import java.util.Random;

import com.zzcm.fourgad.util.DateUtil;

/**
 * 免费险同步接口数据实体 
 * @author qiulongjie
 *
 */
public class Record {
	
	public Record(){
		this.DonateTime = DateUtil.getTodayDate();
	}
	
	public Record(String fromSystem) {
		this.FromSystem = fromSystem;
		this.Key = fromSystem+createKey();
		this.DonateTime = DateUtil.getTodayDate();
	}
	
	private String createKey(){
		Random random = new Random();
		return ""+System.currentTimeMillis()+random.nextInt(9)+random.nextInt(9);
	}

	// 带#号的为必录信息
	
	//  customer  begin
	
	/** # 流水号（小于32位） 必录，合作方提供，用来源+时间等唯一标示**/
	public String Key = "ZhangZhong";
	
	/** # 来源，必录，需要分配**/
	public String FromSystem = "ZhangZhong";
	
	/** # 姓名 必录**/
	public String Name = "";
	
	/** #  性别 (男：Male   女：Female) 必录**/
	public String Sex = "Male";
	
	/** #  出生日期 YYYY-MM-DD   必录**/
	public String Birthday = "";
	
	/** 证件号码 **/
	public String Document = "";
	
	/** 证件类型 
	 *  IdentityCard:身份证 
	 *  SoldierCard:军人证 
	 *  Passport:护照 
	 *  EmigrationCard:侨胞证 
	 *  OtherCard:其他 
	 *  Enlistee:士兵证 
	 *  Police:警官证
	 *  Hometown:返乡证 
	 *  AccessCard:通行证 
	 *  Foreigner:外国人居留证 
	 *  Individual:特殊个人卡种类 
	 *  **/
	public String DocumentType = "";
	
	/** 电子邮箱*/
	public String Email = "";
	
	/** #  手机号 必录*/
	public String Mobile = "";
	
	/** #  省或直辖市 必录，名称必须写全，必须带省或者市*/
	public String ContactStateName = "";
	
	/** #  市 必录*/
	public String ContactCityName = "";
	
	/** #  地址 必录*/
	public String ContactAddress = "";
	
	/** 职业代码 0001001*/
	public String OccupationCode = "0001001";
	
	/** 备注，根据业务确定，默认放空*/
	public String Description = "";
	
	//    customer  END   
	
	
	//    task  begin  
	
	/** calllist名称 放空*/
	public String CallListName = "";
	
	/** 专案名称 放空 */
	public String CampaignName = "";
	
	//    task  end   
	
	
	// Activity  begin 
	
	/** 默认放空，免费险种凭证号，具体根据业务需要，再双方约定 */
	public String Code = "";
	
	/** PC0000000123 产品代码 赠品编号 ,由业务提供，一般PC开始 */
	public String PresentCode = "PC0000000123";
	
	/** TSR号 具体工号分配 */
	public String TSRCode = "805095";
	
	/** #  赠送日期 YYYY-MM-DD 必录 */
	public String DonateTime = "";
	
	/** 默认 1  0:不发短信,1:发送短信*/
	public String SMS = "1";
	
	/** 默认放空，扩展字段 */
	public String FlghtNo = "";
	
	/** 默认放空，扩展字段，生效时间*/
	public String ValidTime = "";
	
	// Activity  end 
	
	public static void main(String[] args) {
		Record r = new Record("qwe");
		System.out.println(r.Key);
	}
}
