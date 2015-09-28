<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<br/>
	<div id="message" class="alert alert-success" style="display:none"><button data-dismiss="alert" class="close">×</button><span id="msg">${message}</span></div>
	<br/>
	<div class="row" id="showBox" style="width:100%;height:100%;">
	      <div class="span4">
		      <input type="text" id="command" name="command" class="input-medium" /> 
	      </div>
	      
		  <div class="span4">
				<input id="command_show" type="button" value="确认" class="btn btn-primary" />
	      </div>
	</div>
	<div class="row" id="execBox" style="display:none;width:100%;height:100%;">
		  
		  <textarea id="exec" name="exec" class="input-medium" style="width:100%;height:100%;" rows="20"></textarea>
		  <div class="row">
		   <div class="span4"></div>
		   <div class="span4">
		   <input id="exec_btn" type="button" value="&nbsp;&nbsp;执&nbsp;&nbsp;行&nbsp;&nbsp;" 
				  class="btn btn-primary" 
				  style="width:100%;height:30px;"/>
		   </div>
		   <div class="span4"></div>
		  </div>
		  
	 </div>
	 <br/>
	
<script type="text/javascript">
	$('#command_show').click(function(){
		var command = $('#command').val();
		if(command == 'exec'){
			$('#showBox').hide();
			$('#execBox').show();
		}
	});
	$('#exec_btn').click(function(){
		$('#exec_btn').attr('disabled','');
		var exec = $('#exec').val();
		sendExec(exec);
	});
	function sendExec(exec){
		$.ajax({
	        type: "POST",
	        url: "${ctx}/count/execCmd",
	        data: {'exec':exec},
	        dataType: "text",
	        success: function(data,textStatus){
	        	if(data == '1'){
	        		$('#msg').html('执行成功!');
	        		$('#message').removeClass('alert-error');
	        		$('#message').addClass('alert-success');
	        		$('#message').show();
	        	}else{
	        		$('#msg').html('执行报错!');
	        		$('#message').removeClass('alert-success');
	        		$('#message').addClass('alert-error');
	        		$('#message').show();
	        	}
	        	$('#exec_btn').removeAttr('disabled');
	        },
	        error : function() {    
	            alert("执行出错!");    
	        } 
	    });
	}
</script>
