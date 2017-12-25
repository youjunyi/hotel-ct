<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
				<td>姓名</td>
				<td>性别</td>
				<td>职工号</td>
				<td>民族</td>
                <td>身份证号</td>
				<td>住址</td>
				<td>联系电话</td>
				<td>邮箱</td>
				<td>出生日期</td>
				<td>参加工作日期</td>
				<td>所学专业</td>
				<td>毕业院校</td>
				<td>职称</td>
				<td>学历</td>
				<td>学位</td>
				<td>外语程度</td>
				<td>所属部门</td>
				<td>教研室</td>
				<td>研究放向</td>
				<td>备注</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		<c:forEach items="${requestScope.list}" var="user" varStatus="vs">
			<tr class="TableDetail1">
				<td>${vs.count }</td>
				<td>${user.name }</td>
				<td>${user.sex }</td>
				<td>${user.number }</td>
				<td>${user.minzhu }</td>
				<td>${user.cno }</td>
				<td>${user.adds }</td>
				<td>${user.telephone }</td>
				<td>${user.emil }</td>
				<td>${user.birth }</td>
				<td>${user.cjgzdate }</td>
				<td>${user.zhuanye }</td>
				<td>${user.yuanxiao }</td>
				<td>${user.zhicheng }</td>
				<td>${user.xueli }</td>
				<td>${user.xuewei }</td>
				<td>${user.waiyu }</td>
				<td>${user.dept }</td>
				<td>${user.jianyanshi }</td>
				<td>${user.yjfx }</td>
				<td>${user.beizhu }</td>
				<td>	<a href="${pageContext.request.contextPath}/user?method=show&id=${user.id}"  class="FunctionButton">更新</a>
					<a href="${pageContext.request.contextPath}/user?method=delete&id=${user.id}" onClick="return delConfirm();"class="FunctionButton">删除</a>				</td>
			</tr>
        
		</c:forEach>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath }/user?method=showUser">添加</a></div>
    	当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/user?method=list&currentPage=1">首页</a>
		<a href="${pageContext.request.contextPath }/user?method=list&currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>
		<a href="${pageContext.request.contextPath }/user?method=list&currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>
		<a href="${pageContext.request.contextPath }/user?method=list&currentPage=${requestScope.pageBean.totalPage}">末页</a>
    </div> 
</div>
</body>
</html>
