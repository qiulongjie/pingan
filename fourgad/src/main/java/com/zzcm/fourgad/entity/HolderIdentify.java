package com.zzcm.fourgad.entity;

/**
 * 免费险返回的数据实体
 * @author qiulongjie
 *
 */
public class HolderIdentify {

	/** TRUE or FALSE */
	public String Flag;
	
	public String Message;
	public String Email;
	public String Name;
	public String Sex;
	public String Birthday;
	public String DocumentType;
	public String DocumentID;
	public String Mobile;
	
	/** 免费险凭证号，当Flag为TRUE时返回*/
	public String FreeInsureNo;
}
