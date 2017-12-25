<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	
<title></title>



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
		<form action="${pageContext.request.contextPath }/topic" method="post">
			<input type="hidden" name="method" value="topictj">
			部门：
			<select name="dept" style="width:80px">
				<option value="" selected>请选择</option>
				<option value="信息科学与电气工程学院">信息科学与电气工程学院</option>
				<option value="土木学院">土木学院</option>
				<option value="机械学院">机械学院</option>
				<option value="汽车学院">汽车学院</option>
				<option value="航空学院">航空学院</option>
				<option value="经管学院">经管学院</option>
				<option value="轨道学院">轨道学院</option>
				<option value="理学院">理学院</option>
				<option value="交通与物流工程学院">交通与物流工程学院</option>
			</select>
			教研室：
			<select name="jianyanshi" style="width:80px">
				<option value="" selected >请选择</option>
				<option value="计算机科学与技术">计算机科学与技术</option>
				<option value="信息管理与信息系统">信息管理与信息系统</option>
				<option value="电气工程及其自动化">电气工程及其自动化</option>
				<option value="电子信息工程">电子信息工程</option>
			</select>
			教师：
			<input type="text" name="username" title="教师名称" >

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
				<td>课题名称</td>
				<td>项目编号</td>
				<td>申请人</td>
				<td>申报经费</td>
                <td>申报时间</td>
				<td>申报单位</td>
				<td>拟定完成时间（日期）</td>
				<td>课题类别</td>
				<td>课题级别</td>
				<td>审核人</td>
				<td>申报材料</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		<c:forEach items="${requestScope.list}" var="topic" varStatus="vs">
			<tr class="TableDetail1">
				<td>${vs.count }</td>
				<td>${topic.name }</td>
				<td>${topic.number }</td>
				<td>${topic.username }</td>
				<td>${topic.sbjf }</td>
				<td>${topic.sbsj }</td>
				<td>${topic.sndw }</td>
				<td>${topic.ndwcsj }</td>
				<td>${topic.ktlb }</td>
				<td>${topic.ktjb }</td>
				<td>${topic.sysName }</td>
				<td><a href="${pageContext.request.contextPath }/${topic.wenjinurl }">${topic.wenjinurl }</a></td>
			</tr>
        
		</c:forEach>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<%--<div id="TableTail" align="center">
    	当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/topic?method=topictj&currentPage=1">首页</a>
		<a href="${pageContext.request.contextPath }/topic?method=topictj&currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>
		<a href="${pageContext.request.contextPath }/topic?method=topictj&currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>
		<a href="${pageContext.request.contextPath }/topic?method=topictj&currentPage=${requestScope.pageBean.totalPage}">末页</a>
    </div> --%>
</div>
</body>
</html>
