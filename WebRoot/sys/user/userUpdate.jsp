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
	<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/My97DatePicker/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
	<script type="text/javascript">

        $(function () {
           
            $("#byyx").click(function () {
                var url = "${pageContext.request.contextPath }/user?method=ajax"
                var dm = $("#dm").val();
                var chapterid = "${user.chapterid}";
                var classname = $("#classname").val();
                $.ajax({
                    type:'post',
                    url:url,
                    data:{"dm":dm,"chapterid":chapterid,"classname":classname},
                    cache:false,
                    success:function(obj){
                        $("#jg").val(obj);
                    }
                })
            })
        })
	</script>
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif"/> 修改用户
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->
	<form action="${pageContext.request.contextPath }/user?method=update" method="post" enctype="multipart/form-data">
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="${pageContext.request.contextPath }/sys/style/images/item_point.gif"> 用户信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
              
						<input type="hidden" name="id" value="${user.id}">
						<input type="hidden" name="path" value="${user.path}">
						<input type="hidden" name="chapterid" value="${user.chapterid}">
						<tr>
							<td width="80px">名称</td>
							<td><input type="text" name="name" class="InputStyle" value="${user.name}"/></td>
						</tr>
						<tr>
							<td width="80px">类名</td>
							<td><input type="text" name="classname" id="classname" class="InputStyle" readonly value="${user.classname}"/></td>
						</tr>

						<tr class="dm">
							<td width="80px">代码</td>
							<td><textarea rows="50" name="dm" cols="110" id="dm">${dm}</textarea></td>
						</tr>
						<tr class="dm">
							<td width="80px">运行结果</td>
							<td><textarea rows="20" name="jg" cols="100" id="jg"></textarea></td>
						</tr>

					</table>
				</div>
            </div>
        </div>
		<!-- 表单操作 -->
		<div id="InputDetailBar">
			<input type="submit" value="修改" class="FunctionButtonInput">
			<input type="button" value="编译并运行" id="byyx" class="FunctionButtonInput dmd">
			<a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>
</body>
</html>
