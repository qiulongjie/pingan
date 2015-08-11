<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
  Calendar   cal   =   Calendar.getInstance();
  cal.add(Calendar.DATE,   0);
  String vtime = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
%>
<br/>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<br/>
	<div class="row">
	 <form class="form-search" action="#">
		  <div class="span4">
			  <div class="input-group">
			    <label>日期：</label>
			    <input id="vtime" value="<%=vtime %>" type="text" class="input-medium input-datepicker"/>
			  </div>
		  </div>
		 
		  <div class="span4">
			
				<label>渠道编码：</label> 
				<input type="text" id="channel" name="channel" class="input-medium" /> 
		   
	      </div>
	      
		  <div class="span4">
				<input id="query_data" type="button" value="&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;" class="btn btn-primary" />
				<input id="output_data" type="button" value="&nbsp;&nbsp;导&nbsp;&nbsp;出&nbsp;&nbsp;" class="btn btn-default" />
	      </div>
	  </form>
	 </div>
	 <br/>
	 <table id="contentTable" class="table table-striped "><!-- table-bordered table-condensed -->
		<thead>
		    <tr>
		        <th>姓名</th>
		        <th>生日</th>
		        <th>性别</th>
		        <th>电话</th>
		        <th>IP</th>
		        <th>时间</th>
		        <th>渠道</th>
		        <th>状态标识</th>
		        <th>状态</th>
		    </tr>
		</thead>
	</table>
	
<script type="text/javascript">


	$('.input-datepicker').datepicker({
		language: 'cn',
		autoclose: true, 
        format: 'yyyy-mm-dd',
        todayHighlight:true
	});
	
	var table = $('#contentTable').dataTable({
	        "sPaginationType": "full_numbers",
	        "bFilter":false,
	        "bSort" : false,// 排序
	        "bLengthChange": false,
	        "processing": true,
	        "serverSide": true,
	        "pageLength": 60,
	        "ajax": {
	            "url": "${ctx}/count/getOrdDetailData",
	            "type": "post",
	            "data":function ( d ) {
	                d.date = new Date();
	                d.vtime = $('#vtime').val();
	                d.channel = $('#channel').val();
	            }
	        },
	        "columns": [
	                     { "data": "uname"},
	                     { "data": "birthday"},
	                     { "data": "ddlSex"},
	                     { "data": "phone"},
	                     { "data": "ipaddr"},
	                     { "data": "vtime"},
	                     { "data": "pubcode"},
	                     { "data": "flag"},
	                     { "data": "vstr1"}
	                ],
		   	 "oLanguage": {
		            "sProcessing": "正在加载中......",
		            "sLengthMenu": "每页显示 _MENU_ 条记录",
		            "sZeroRecords": "对不起，查询不到相关数据！",
		            "sEmptyTable": "表中无数据存在！",
		            "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
		            "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
		            "sSearch": "搜索",
		            "oPaginate": {
		                "sFirst": "首页",
		                "sPrevious": "上一页",
		                "sNext": "下一页",
		                "sLast": "末页"
		            }
		   	 }
	});
	
	$('#query_data').click(function(e){
		$("#contentTable").DataTable().ajax.reload(null,true);
		$('#output_data').removeAttr("disabled");
	});
	//$('#output_data').attr("disabled",true);
	$('#output_data').click(function(e){
		//导出数据
		//alert($('#vtime').val()+$('#channel').val());
		//window.open('http://www.baidu.com');
		if(confirm('确定下载以下订单数据吗?\n日期：'+$('#vtime').val()+'\n渠道：'+$('#channel').val())){
			var url = '${ctx}/count/downloadOrdDetailData?vt='+$('#vtime').val()+'&chn='+$('#channel').val();
			var a = document.createElement("a");
		    a.setAttribute("href", url);
		    a.setAttribute("target", '_blank');
		    document.body.appendChild(a);
		    if(a.click){
		        a.click();
		    }
		    document.body.removeChild(a);
		}
	});
</script>
