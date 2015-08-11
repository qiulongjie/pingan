<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
  Calendar   cal   =   Calendar.getInstance();
  cal.add(Calendar.DATE,   -2);
  String c_date = new SimpleDateFormat( "yyyyMMdd").format(cal.getTime());
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
			    <input id="c_date" value="<%=c_date %>" type="text" class="input-medium input-datepicker"/>
			  </div>
	      </div>
	      
		  <div class="span4">
				<label>渠道编码：</label> 
				<input type="text" id="channel" name="channel" class="input-medium" /> 
	      </div>
		  
		  <div class="span4">
				<input id="query_data" type="button" value="&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;" class="btn btn-primary" />
	      </div>
	  </form>
	 </div>
	 <br/>
	 <div class="row">
		 <div class="span4">
		  <p id="total_fee">正在获取总计...</p>
		 </div>
		 <div class="span4">
		 </div>
		 <div class="span4">
		 </div>
	 </div>
	 <br/>
	 <table id="contentTable" class="table table-striped "><!-- table-bordered table-condensed -->
		<thead>
		    <tr>
		        <th>日期</th>
		        <th>渠道</th>
		        <th>结算数量</th>
		        <th>结算金额</th>
		    </tr>
		</thead>
	</table>
	
<script type="text/javascript">
$('.input-datepicker').datepicker({
	language: 'cn',
	autoclose: true, 
    format: 'yyyymmdd',
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
	            "url": "${ctx}/count/getChannelFeeData",
	            "type": "post",
	            "data":function ( d ) {
	                d.date = new Date();
	                d.channel = $('#channel').val();
	                d.c_date = $('#c_date').val();
	            }
	        },
	        "columnDefs": [],
	        "columns": [
	                     { "data": "c_date"},
	                     { "data": "channel"},
	                     { "data": "fee_count"},
	                     { "data": "fee"}
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
		getTotal();
	});
	getTotal();
	function getTotal(){
		var channel = $('#channel').val();
        var c_date = $('#c_date').val();
		$.ajax({
			type : "POST",
			cache : false,
			data:{'channel':channel,'c_date':c_date},
			url : "${ctx}/count/getChannelTotalFeeData",
			dataType : "text",
			success : function(data) {
				if(data != null && data != '' && data!= 'null' && data != undefined){
					var arr = data.split('-');
					var total_cnt = arr[0];
					var total_fee = arr[1];
					$('#total_fee').html('结算总数量：'+total_cnt+'，结算中金额：<span style="color:red;font-size:18px;"><strong>'+total_fee+'</strong></span>');
				}else{
					$('#total_fee').html('');
				}
			},
			error : function() {
				
			}
		});
	}
</script>
