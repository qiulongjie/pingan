package com.zzcm.fourgad.util;

import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zzcm.fourgad.entity.HolderIdentify;
import com.zzcm.fourgad.entity.Record;

/**
 * 免费险同步接口数据组装工具 
 * @author qiulongjie
 *
 */
public class RecordXmlUtil {

	/**
	 * 获取同步数据
	 * @param record
	 * @return
	 */
	public static String getXmlData(Record record){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");            
		sb.append("<Records>");
		sb.append(" <Record>");
		sb.append("  <Customer>");
		sb.append("   <Key>").append(record.Key).append("</Key>");					
		sb.append("   <FromSystem>").append(record.FromSystem).append("</FromSystem>");         
		sb.append("   <Name>").append(record.Name).append("</Name>");         
		sb.append("   <Sex>").append(record.Sex).append("</Sex>");         
		sb.append("   <Birthday>").append(record.Birthday).append("</Birthday>");         
		sb.append("   <Document>").append(record.Document).append("</Document>");         
		sb.append("   <DocumentType>").append(record.DocumentType).append("</DocumentType>");
		sb.append("   <Email>").append(record.Email).append("</Email>");         
		sb.append("   <Mobile>").append(record.Mobile).append("</Mobile>");         
		sb.append("   <ContactState>");
		sb.append("   	<Name>").append(record.ContactStateName).append("</Name>");         
		sb.append("   </ContactState>");
		sb.append("   <ContactCity>");
		sb.append("   	<Name>").append(record.ContactCityName).append("</Name>");         
		sb.append("   </ContactCity>");
		sb.append("   <ContactAddress>").append(record.ContactAddress).append("</ContactAddress>");         
		sb.append("   <Occupation>");
		sb.append("   	<Code>").append(record.OccupationCode).append("</Code>");         
		sb.append("   </Occupation>");
		sb.append("   <Description>").append(record.Description).append("</Description>");         
		sb.append("  </Customer>");
		sb.append("  <Task>");
		sb.append("   <CallList><Name>").append(record.CallListName).append("</Name></CallList>");
		sb.append("   <Campaign><Name>").append(record.CampaignName).append("</Name></Campaign>");
		sb.append("  </Task>");
		sb.append("  <Activity>");
		sb.append("   <Code>").append(record.Code).append("</Code>");               
		sb.append("   <Present>");
		sb.append("   <Code>").append(record.PresentCode).append("</Code>");                    
		sb.append("   </Present>");
		sb.append("   <TSR>");
		sb.append("   <Code>").append(record.TSRCode).append("</Code>");       
		sb.append("   </TSR> ");
		sb.append("   <DonateTime>").append(record.DonateTime).append("</DonateTime>");         
		sb.append("   <SMS>").append(record.SMS).append("</SMS>");         
		sb.append("   <FlghtNo>").append(record.FlghtNo).append("</FlghtNo>");         
		sb.append("   <ValidTime>").append(record.ValidTime).append("</ValidTime>");         
		sb.append("  </Activity>");
		sb.append(" </Record>");
		sb.append("</Records>");
		return sb.toString();
	}
	
	/**
	 * 封装返回的xml数据 
	 * @param xml
	 * @return
	 */
	public static HolderIdentify parserXmlResult(String xml){
		try {
			Document document = DocumentHelper.parseText(xml);
			return paserHolderIdentify(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 封装返回的xml数据 
	 * @param input
	 * @return
	 */
	public static HolderIdentify parserXmlResult(InputStream input){
		try {
			// 创建saxReader对象  
	        SAXReader reader = new SAXReader();
			Document document = reader.read(input);
			return paserHolderIdentify(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			if( input!= null ){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 组装HolderIdentify对象
	 * @param document
	 * @return
	 */
	private static HolderIdentify paserHolderIdentify(Document document){
		HolderIdentify identify = new HolderIdentify();
		Element root = document.getRootElement();
		Element paq_body = root.element("paq_body");
		Element HolderIdentify = paq_body.element("HolderIdentify");
		identify.Flag = HolderIdentify.element("Flag").getTextTrim();
		identify.Message = HolderIdentify.element("Message").getTextTrim();
		identify.Email = HolderIdentify.element("Email").getTextTrim();
		identify.Name = HolderIdentify.element("Name").getTextTrim();
		identify.Sex = HolderIdentify.element("Sex").getTextTrim();
		identify.Birthday = HolderIdentify.element("Birthday").getTextTrim();
		identify.DocumentType = HolderIdentify.element("DocumentType").getTextTrim();
		identify.DocumentID = HolderIdentify.element("DocumentID").getTextTrim();
		identify.FreeInsureNo = HolderIdentify.element("FreeInsureNo")==null?"":HolderIdentify.element("FreeInsureNo").getTextTrim();
		identify.Mobile = HolderIdentify.element("Mobile")==null?"":HolderIdentify.element("Mobile").getTextTrim();
		return identify;
	}
}
