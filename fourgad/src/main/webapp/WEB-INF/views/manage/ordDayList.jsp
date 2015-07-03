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
			  <div class="input-group">
			    <label>日期：</label>
			    <input id="vtime" value="" type="text" class="input-medium input-datepicker"/>
			  </div>
		  </div>
		 
		  <div class="span4">
			
 				<!-- <label>渠道编码：</label> -->
 				<input type="hidden" id="channel" name="channel" class="input-medium" /> 
		   
	      </div>
	      
		  <div class="span4">
				<input id="query_data" type="button" value="&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;" class="btn btn-primary" />
				<input id="goback" type="button" onclick="javascript:history.back()" value="&nbsp;&nbsp;返&nbsp;&nbsp;回&nbsp;&nbsp;" class="btn btn-default" />
	      </div>
	  </form>
	 </div>
	 <br/>
	 <table id="contentTable" class="table table-striped "><!-- table-bordered table-condensed -->
		<thead>
		    <tr>
		        <th>日期</th>
		        <th>渠道</th>
		        <th>商务</th>
		        <th>客户</th>
		        <th>状态</th>
		        <th>订单数</th>
		        <th>有效订单数</th>
		        <th width="140px">操作</th>
		    </tr>
		</thead>
	</table>
	
<script type="text/javascript">

	var vtime = "${vtime}";
	$('#vtime').val(vtime);
	
	function showPV(vtime,a){
		var span = $('#id'+vtime+a);
		span.text('正在获取...');
		if(a === null || a=== 'null'){
		  a='';
		}
		$.ajax({
	        type: "POST",
	        url: "${ctx}/count/getPV",
	        data: {'vtime':vtime,'a':a},
	        dataType: "text",
	        success: function(data,textStatus){
	        	//alert(data);
	        	span.html(data);
	        },
	        error : function() {    
	            alert("数据异常！");    
	        } 
	    });
	}

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
	        "pageLength": 30,
	        "ajax": {
	            "url": "${ctx}/count/getData",
	            "type": "post",
	            "data":function ( d ) {
	                d.date = new Date();
	                d.vtime = $('#vtime').val();
	                d.channel = $('#channel').val();
	            }
	        },
	        "columnDefs": [
							{
						    "targets": 0,
						    "data": "",
						    "render": function ( data, type, full, meta ) {
						    	if(data === null || data=== 'null'){
	                   	    		data='';
	                   			}
	                   	    	if(full.a === null || full.a=== 'null'){
	                   	    		full.a='';
	                   			}
	                   	      var link = "${ctx }/count/showDataForHour?vtime="+data+"&a="+full.a;
	                   	      return '<a href="'+link+'" >'+data+'</a>';
						    }
						  },
	                 	  {
	                   	    "targets": 7,
	                   	    "data": "",
	                   	    "render": function ( data, type, full, meta ) {
	                   	      var link = "'"+full.vtime+"','"+full.a+"'";
	                   	      var id = 'id'+full.vtime+full.a;
	                   	      return '<a href="javascript:showPV('+link+');" >查看PV</a>'+
	                   	             '&nbsp;&nbsp;&nbsp;<strong><span id="'+id+'"></span></strong>';
	                   	    }
	                   	  } ],
	        "columns": [
	                     { "data": "vtime"},
	                     { "data": "a"},
	                     { "data": "busy_name"},
	                     { "data": "customer"},
	                     { "data": "st"},
	                     { "data": "ord_cnt"},
	                     { "data": "eft_cnt"},
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
		var t = $('#vtime').val();
		if( t === null || t === ''){
			alert('请选择一个日期');
			return;
		}
		$("#contentTable").DataTable().ajax.reload(null,true);
	});
	
</script>
