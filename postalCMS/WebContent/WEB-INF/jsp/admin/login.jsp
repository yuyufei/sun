<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en" class="no-js">

    <head>
    <jsp:include page="/WEB-INF/jsp/path.jsp"></jsp:include>
       <%--  <%@ include file="./WEB-INF/jsp/path.jsp"%> --%>
        <meta charset="utf-8">
        <title>CMSLogin</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'> 
        <link rel="stylesheet" href="${demoPath}static/assets/css/reset.css">
        <link rel="stylesheet" href="${demoPath}static/assets/css/supersized.css">
        <link rel="stylesheet" href="${demoPath}static/assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
     <script type="text/javascript">
	window.onload = function() {
		//...
	}
	$(function() {
		$("#submitForm").bind('click', function() {
			$.messager.progress();
			$('#ff').form('submit', {
			    url : '${demoPath}admin/login.html?t='+Math.random()+'&userAgent='+navigator.userAgent,
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	
					}
					return isValid;	
				},
				success : function(data) {
					$.messager.progress('close');
					var result=eval('('+data+')');
					if(result.success){
						window.location.href='${demoPath}admin/index.html';
						return;
					}
					if(result.state==1){
						//error,question,info,warning
						$.messager.alert('操作提示', result.msg, 'info');
						return;
					}
					if(result.state==2){
						$.messager.alert('操作提示', result.msg, 'info');
					    var url = '${demoPath}admin/verifyCode.html?t=' + Math.random();
			            $('#verifyCode').attr('src', url);
			            return;
					}
				}
			});
		});
		$("#clearForm").bind('click', function() {
			$('#ff').form('clear');
		});
		$("#verifyClick").bind('click', function() {
			var url = '${demoPath}admin/verifyCode.html?t=' + Math.random();
			$('#verifyCode').attr('src', url);
		});
		$("#rollBack").bind('click',function(){
			$.ajax({
				url:'${demoPath}admin/rollBack.html',
				dataType :'json',
			    beforeSend: function(){  
			        $.messager.progress() 
			    },
			    complete: function(){  
			        $.messager.progress('close'); 
			    },
			    success : function(data) { 
			    	$.messager.alert("操作提示", data.msg);
			    }
			});
		});
	});
</script>
    </head>

    <body>

        <div class="page-container">
            <h1>基建工程</h1>
            <form action="" method="post">
                <input type="text" name="username" class="username" placeholder="Username">
                <input type="password" name="password" class="password" placeholder="Password">
                <button type="submit">Sign me in</button>
                <div class="error"><span>+</span></div>
            </form>
        </div>
        <div style="margin-top: 15px;">
					<div style="width: 194px;" align="left">
						<label for="name">验证码：</label> 
						<input class="easyui-validatebox" type="text" name="verifyCode" style="width:60px;" value=""/> 
						<span style="margin-left: 2px;" /> 
						<a style="text-align: center;" id="verifyClick" title="点击 刷新?" href="javascript:void(0);"> 
						<img style="width:65px;height:22px;" align="absmiddle" id="verifyCode" src="${demoPath}admin/verifyCode.html" /> </a>
					</div>
		</div>
        <div  align="center">版权所有 @ <a  href="https://mail.qq.com/">fly</a></div>

        <!-- Javascript -->
        <%-- <script src="${demoPath}static/assets/js/jquery-1.8.2.min.js"></script> --%>
        <script src="${demoPath}static/assets/js/supersized.3.2.7.min.js"></script>
        <script src="${demoPath}static/assets/js/supersized-init.js"></script>
        <script src="${demoPath}static/assets/js/scripts.js"></script>
        <script type="text/javascript" src="${demoPath}static/js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="${demoPath}static/js/easyui/jquery.easyui.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${demoPath}static/js/easyui/themes/bootstrap/easyui.css" />
        <link rel="stylesheet" type="text/css" href="${demoPath}static/js/easyui/themes/icon.css" />
    </body>

</html>

