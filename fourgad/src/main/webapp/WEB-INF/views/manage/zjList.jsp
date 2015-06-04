<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
/*   Calendar   cal   =   Calendar.getInstance();
  SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd ");
  String end_time = sdf.format(cal.getTime());
  cal.add(Calendar.DATE,   -7);
  String begin_time = sdf.format(cal.getTime()); */
%>
<br/>
	<div id="message" style="display:none" class="alert alert-success"><button data-dismiss="alert" class="close">×</button><span id="msg_show">${message}</span></div>
	<br/>
	<div class="row">
	 <form class="form-search" action="#">
		  <div class="span4">
		        <label>渠道编码：</label> 
				<input type="text" id="channel" name="channel" class="input-medium" /> 
		  </div>
		 
		  <div class="span4">
				
	      </div>
	      
		  <div class="span4">
				<input id="query_data" type="button" value="&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;" class="btn btn-primary" />
	      </div>
	  </form>
	 </div>
	 <br/>
	 <table id="contentTable" class="table table-striped "><!-- table-bordered table-condensed -->
		<thead>
		    <tr>
		        <th>渠道</th>
 		        <th>姓名</th>
		        <th>电话</th>
 		        <th>收件地址</th>
		        <th>填单时间</th>
		        <th>奖品</th>
		        <th>状态</th>
		    </tr>
		</thead>
	</table>
	
<script type="text/javascript">

    function sendStatusChange(event){
    	$('#message').hide();
    	
    	var v = $(event.currentTarget).val();
    	var id = $(event.currentTarget).attr("zjid");
    	
    	$.ajax({
			type : "POST",
			cache : false,
			async : false,
			data:{'id':id,'if_send':v},
			url : "${ctx}/count/updateZJSendStatus",
			dataType : "text",
			success : function(data) {
				if(data === '1'){
					msg_show('更新成功！！');
				}else{
					msg_show('更新失败！！');
				}
			},
			error : function() {
				alert('服务器忙!')
			}
		});
    }
    
    function msg_show(msg){
    	$('#msg_show').html(msg);
    	$('#message').show('slow');
    }

	var table = $('#contentTable').dataTable({
	        "sPaginationType": "full_numbers",
	        "bFilter":false,
	        "bSort" : false,// 排序
	        "bLengthChange": false,
	        "processing": true,
	        "serverSide": true,
	        "pageLength": 30,
	        "ajax": {
	            "url": "${ctx}/count/getZJData",
	            "type": "post",
	            "data":function ( d ) {
	                d.date = new Date();
	                d.channel = $('#channel').val();
	            }
	        },
	        "columnDefs": [ 
		                 	  {
		                   	    "targets": 6,
		                   	    "data": "",
		                   	    "render": function ( data, type, full, meta ) {
		                   	      var d = '<select style="width:100px;" zjid="'+full.id+'" onchange="sendStatusChange(event)">';
		                   	         if( full.if_send === 0){
		                   	        	d = d + '<option value="0" selected>未确认</option>'+
				                   	             '<option value="1">已确认</option>'+
				                   	             '<option value="2">联系失败</option>'+
				                   	             '<option value="3">已发送</option>';
		                   	         }
		                   	         if( full.if_send === 1){
		                   	        	d = d + '<option value="0">未确认</option>'+
				                   	             '<option value="1" selected>已确认</option>'+
				                   	             '<option value="2">联系失败</option>'+
				                   	             '<option value="3">已发送</option>';
		                   	         }
		                   	         if( full.if_send === 2){
		                   	        	d = d + '<option value="0">未确认</option>'+
				                   	             '<option value="1">已确认</option>'+
				                   	             '<option value="2" selected>联系失败</option>'+
				                   	             '<option value="3">已发送</option>';
		                   	         }
		                   	         if( full.if_send === 3){
		                   	        	d = d + '<option value="0" >未确认</option>'+
				                   	             '<option value="1">已确认</option>'+
				                   	             '<option value="2">联系失败</option>'+
				                   	             '<option value="3" selected>已发送</option>';
		                   	         }
	                   	          d = d + '</select>';
		                   	      return d;
		                   	    }
		                   	  } ],
	        "columns": [
	                     { "data": "channel"},
	                     { "data": "uname"},
	                     { "data": "phone"},
	                     { "data": "address"},
	                     { "data": "vtime"},
	                     { "data": "ok_title"},
	                     { "data": "send_status"},
	                     { "data": "if_send","visible":false},
	                     { "data": "id","visible":false}
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
		var a = $('#channel').val();
		if( a === null || a === ''){
			alert('请输入一个渠道');
			return;
		}
		$("#contentTable").DataTable().ajax.reload(null,true);
	});
	
</script>
