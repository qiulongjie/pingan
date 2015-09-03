package com.zzcm.fourgad.util;

import java.util.Map;

/**
 * Excel 工具
 * 利用xml格式数据操作Excel<br/>
 * 执行顺序:<br/>
 * getHeader<br/>
 * --beginSheet<br/>
 * ----createRow<br/>
 * ---- ...<br/>
 * --endSheet<br/>
 * getFooter<br/>
 * @author qiulongjie
 *
 */
public class ExcelUtil {
	private static final int DefaultColumnWidth = 60;
	private static final int DefaultRowHeight = 18;
	
	/**
	 * Excel头信息
	 * @author qiulongjie
	 * @return
	 */
	public static String getHeader(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<?mso-application progid=\"Excel.Sheet\"?>");
		sb.append("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");
		sb.append(" xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
		sb.append(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
		sb.append(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
		sb.append(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">");
		sb.append("<DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">");
		sb.append("<Author>fourgad</Author>");
		sb.append("<LastAuthor>fourgad</LastAuthor>");
		sb.append("<Created>"+DateUtil.getDateTime()+"</Created>");
		sb.append("<LastSaved>"+DateUtil.getDateTime()+"</LastSaved>");
		sb.append("<Version>12.00</Version>");
		sb.append("</DocumentProperties>");
		sb.append("<OfficeDocumentSettings xmlns=\"urn:schemas-microsoft-com:office:office\">");
		sb.append("</OfficeDocumentSettings>");
		sb.append("<ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">");
		sb.append("<WindowHeight>7830</WindowHeight>");
		sb.append("<WindowWidth>19395</WindowWidth>");
		sb.append("<WindowTopX>600</WindowTopX>");
		sb.append("<WindowTopY>75</WindowTopY>");
		sb.append("<ProtectStructure>False</ProtectStructure>");
		sb.append("<ProtectWindows>False</ProtectWindows>");
		sb.append("</ExcelWorkbook>");
		sb.append("<Styles>");
		sb.append("<Style ss:ID=\"Default\" ss:Name=\"Normal\">");
		sb.append("<Alignment ss:Vertical=\"Center\"/>");
		sb.append("<Borders/>");
		sb.append("<Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"11\" ss:Color=\"#000000\"/>");
		sb.append("<Interior/>");
		sb.append("<NumberFormat/>");
		sb.append("<Protection/>");
		sb.append("</Style>");
		sb.append("<Style ss:ID=\"s16\">");
		sb.append("<Alignment ss:Vertical=\"Center\" ss:WrapText=\"1\"/>");
		sb.append("<Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"12\" ss:Color=\"#000000\"/>");
		sb.append("</Style>");
		sb.append("</Styles>");
		return sb.toString();
	}
	
	/**
	 * Excel尾信息
	 * @author qiulongjie
	 * @return
	 */
	public static String getFooter(){
		return "</Workbook>";
	}
	
	/**
	 * sheet页的开始
	 * @author qiulongjie
	 * @param sheetName sheet页的名称
	 * @param columnWidths 每列的宽度 可以不设置 即为null
	 * @return
	 */
	public static String beginSheet(String sheetName,int[] columnWidths){
		StringBuffer sb = new StringBuffer();
		sb.append("<Worksheet ss:Name=\""+ sheetName +"\">");
		sb.append("<Table x:FullColumns=\"1\" x:FullRows=\"1\" ss:DefaultColumnWidth=\""+DefaultColumnWidth+"\" ss:DefaultRowHeight=\""+DefaultRowHeight+"\">");
		if( columnWidths != null ){
			for( int width : columnWidths){
				sb.append("<Column ss:AutoFitWidth=\"0\" ss:Width=\""+ width +"\"/>");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 结束sheet页
	 * @author qiulongjie
	 * @param isSelected 是否选中
	 * @return
	 */
	public static String endSheet(boolean isSelected){
		StringBuffer sb = new StringBuffer();
		sb.append("</Table>");
		sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
		sb.append("<PageSetup>");
		sb.append("<Header x:Margin=\"0.3\"/>");
		sb.append("<Footer x:Margin=\"0.3\"/>");
		sb.append("<PageMargins x:Bottom=\"0.75\" x:Left=\"0.7\" x:Right=\"0.7\" x:Top=\"0.75\"/>");
		sb.append("</PageSetup>");
		sb.append("<Print>");
		sb.append("<ValidPrinterInfo/>");
		sb.append("<PaperSizeIndex>0</PaperSizeIndex>");
		sb.append("<VerticalResolution>0</VerticalResolution>");
		sb.append("<NumberofCopies>0</NumberofCopies>");
		sb.append("</Print>");
		if(isSelected){
			sb.append("<Selected/>");
		}
		sb.append("<Panes>");
		sb.append("<Pane>");
		sb.append("<Number>3</Number>");
		sb.append("<ActiveRow>8</ActiveRow>");
		sb.append("<ActiveCol>3</ActiveCol>");
		sb.append("</Pane>");
		sb.append("</Panes>");
		sb.append("<ProtectObjects>False</ProtectObjects>");
		sb.append("<ProtectScenarios>False</ProtectScenarios>");
		sb.append("</WorksheetOptions>");
		sb.append("</Worksheet>");
		return sb.toString();
	}
	
	/**
	 * 创建一行数据 
	 * @author qiulongjie
	 * @param rows 数据的字符串数组 例如{"姓名","生日","性别","号码","IP","时间","渠道","同步状态","同步信息"}
	 * @return
	 */
	public static String createRow(String[] rows){
		if( rows != null && rows.length > 0){
			StringBuffer sb = new StringBuffer();
			sb.append("<Row ss:Height=\""+DefaultRowHeight+"\">");
			for( String s : rows ){
				sb.append("<Cell ss:StyleID=\"s16\"><Data ss:Type=\"String\">" + s + "</Data></Cell>");
			}
			sb.append("</Row>");
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 创建一行数据 
	 * @author qiulongjie
	 * @param rowKeys 行数据Map<String, Object>中的key的数组 例如{"uname","phone"}
	 * @param map 行数据 例如{"uname":"张三","phone":"12344566"}
	 * @return
	 */
	public static String createRow(String[] rowKeys,Map<String, Object> map){
		if( rowKeys != null && rowKeys.length > 0 && map != null ){
			StringBuffer sb = new StringBuffer();
			sb.append("<Row ss:Height=\""+DefaultRowHeight+"\">");
			for( String key : rowKeys ){
				sb.append("<Cell ss:StyleID=\"s16\"><Data ss:Type=\"String\">" + (map.get(key)==null ? "" : map.get(key)) + "</Data></Cell>");
			}
			sb.append("</Row>");
			return sb.toString();
		}
		return "";
	}
}
