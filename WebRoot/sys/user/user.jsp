﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif"/> 用户列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="${pageContext.request.contextPath }/user" method="post">
			<input type="hidden" name="method" value="search">
			<input type="text" name="keyword" title="请输入姓名" >
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td></td>
				<td>作业名称</td>
				<td>类名</td>
				<td>文件</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		<c:forEach items="${requestScope.list}" var="user" varStatus="vs">
			<tr class="TableDetail1">
				<td>${vs.count }</td>
				<td>${user.name}</td>
				<td>${user.classname}</td>
				<td>${user.path}</td>
				<td>	<a href="${pageContext.request.contextPath}/user?method=show&id=${user.id}&chapterid=${chapterid}"  class="FunctionButton">更新</a>
					<a href="${pageContext.request.contextPath}/user?method=delete&id=${user.id}" onClick="return delConfirm();"class="FunctionButton">删除</a></td>
			</tr>
        
		</c:forEach>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath }/user?method=showUser&chapterid=${chapterid}">添加</a></div>
    	当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/user?method=list&currentPage=1&chapterid=${chapterid}">首页</a>
		<a href="${pageContext.request.contextPath }/user?method=list&currentPage=${requestScope.pageBean.currentPage-1}&chapterid=${chapterid}">上一页 </a>
		<a href="${pageContext.request.contextPath }/user?method=list&currentPage=${requestScope.pageBean.currentPage+1}&chapterid=${chapterid}">下一页 </a>
		<a href="${pageContext.request.contextPath }/user?method=list&currentPage=${requestScope.pageBean.totalPage}&chapterid=${chapterid}">末页</a>
    </div> 
</div>
</body>
</html>
