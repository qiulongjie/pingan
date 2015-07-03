<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<br/>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<br/>
	<div class="row">
	 <form class="form-search" action="#">
		  
		  <div class="span4">
			
				<label>渠道编码：</label> 
				<input type="text" id="channel" name="channel" class="input-medium" /> 
		   
	      </div>
	      
		  <div class="span4">
				<input id="query_data" type="button" value="&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;" class="btn btn-primary" />
				<a href="${ctx }/count/addChannel" class="btn" >渠道注册</a>
	      </div>
		  <div class="span4">
				<a id="refreshChannel" href="javascript:refreshChannel();" class="btn" >刷新缓存</a>
	      </div>
	  </form>
	 </div>
	 <br/>
	 <table id="contentTable" class="table table-striped "><!-- table-bordered table-condensed -->
		<thead>
		    <tr>
		        <th>渠道</th>
		        <th>商务</th>
		        <th>客户</th>
		        <th>生效时间</th>
		        <th>终止时间</th>
		        <th>状态</th>
		        <th>操作</th>
		    </tr>
		</thead>
	</table>
	
<script type="text/javascript">

    function refreshChannel(){
    	$.ajax({
	        type: "POST",
	        url: "${ctx}/count/refreshChannel",
	        data: {},
	        dataType: "text",
	        success: function(data,textStatus){
	        },
	        error : function() {    
	        } 
	    });
    }

	var table = $('#contentTable').dataTable({
	        "sPaginationType": "full_numbers",
	        "bFilter":false,
	        "bSort" : false,// 排序
	        "bLengthChange": false,
	        "processing": true,
	        "serverSide": true,
	        "pageLength": 60,
	        "ajax": {
	            "url": "${ctx}/count/getChannelData",
	            "type": "post",
	            "data":function ( d ) {
	                d.date = new Date();
	                d.channel = $('#channel').val();
	            }
	        },
	        "columnDefs": [ 
					       {
						    "targets": 0,
						    "data": "",
						    "render": function ( data, type, full, meta ) {
					   		    return '<a href="${ctx}/count/updateChannelForm?i='+full.id+'">'+data+'</a>';
						    }
						  },
	                 	  {
	                   	    "targets": 6,
	                   	    "data": "",
	                   	    "render": function ( data, type, full, meta ) {
                   	    		var link;
                   	    		var txt='屏蔽';
                   	    		var flag=1;
                   	    		if(full.disable_flag){//已经屏蔽
                   	    			txt='解除屏蔽';
                   	    			flag=0;
                   	    		}
                   	    		link = '${ctx}/count/updateChannelSt?i='+full.id+'&f='+flag;
                   	    		var link2 = '${ctx}/count/deleteChannel?i='+full.id;
                   	    		var confirmTxt = "'确定要"+txt+full.channel+"渠道?'";
                   	    		var confirmTxt2 = "'确定要删除"+full.channel+"渠道?'";
                   	    		return '<a href="'+link+'" onClick="return confirm('+confirmTxt+');">'+txt+'</a>&nbsp;&nbsp;'
                   	    		       +'<a href="'+link2+'" onClick="return confirm('+confirmTxt2+');">删除</a>';
	                   	    }
	                   	  } ],
	        "columns": [
	                     { "data": "channel"},
	                     { "data": "busy_name"},
	                     { "data": "customer"},
	                     { "data": "start_time"},
	                     { "data": "end_time"},
	                     { "data": "st"},
	                     { "data": null}
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
	});
	
</script>
