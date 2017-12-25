﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String usernamr = request.getSession().getAttribute("username").toString();
	String id = request.getSession().getAttribute("id").toString();
	String state = request.getSession().getAttribute("state").toString();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/My97DatePicker/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">

	<script type="application/javascript">
		function tt() {
		    var password = $("#password").val();
			var newpassword = $("#newpassword").val();
			var newpasswordr = $("#newpasswordr").val();
			if(!password){
			    alert("请输旧入密码！")
				return false;
			}
			if(!newpassword){
                alert("请输密码！")
                return false;
			}
			if (newpassword!=newpasswordr){
                alert("两次密码不一致！")
                return false;
			}
        }
	</script>
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			
				
				
					<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif"/> 专家用户
				
			
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<c:if test="${msg != null }">
		<td> ${msg}</td>
	</c:if>
	<!-- 表单内容 -->
	<form  <c:if test="${state=='sysuser'}">action="${pageContext.request.contextPath }/sysuser?method=password&id=${id}"</c:if> <c:if test="${state=='user'}">action="${pageContext.request.contextPath }/user?method=password&id=${id}"</c:if> method="post"    onsubmit="return tt();">
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="${pageContext.request.contextPath }/sys/style/images/item_point.gif"> 用户信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
          <%--          <tr>
							<td width="80px">菜系</td>
							<td>
                            <select name="foodType_id" style="width:80px">
	                            <c:forEach items="${requestScope.foodtypes}" var="type">
			   						<option value="${type.id}">${type.typeName }</option>
			   					</c:forEach>
                            </select>
                            </td>
						</tr>--%>
						<tr>
							<td width="80px">旧密码</td>
							<td><input type="text" name="password" id="password" class="InputStyle" value=""/> *</td>
						</tr>
						<tr>
							<td>新密码</td>
							<td>
								<input type="text" name="newpassword" id="newpassword" class="InputStyle" value=""/>*
							</td>
						</tr>
                        <tr>
							<td>确认新密码</td>
							<td><input type="text" id="newpasswordr"   class="InputStyle" value=""/> *</td>
						</tr>
					

					
						
					</table>
				</div>
            </div>
        </div>
		
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">
            
				
				
					 <input type="submit"  value="确认" class="FunctionButtonInput">
				
			
            
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>
</body>
</html>