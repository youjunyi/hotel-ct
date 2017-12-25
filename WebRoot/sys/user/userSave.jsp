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
	<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/My97DatePicker/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			
				
				
					<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif"/> 添加用户
				
			
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->
	<form action="${pageContext.request.contextPath }/user?method=add" method="post" enctype="multipart/form-data">
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
							<td width="80px">姓名</td>
							<td><input type="text" name="name" class="InputStyle" value=""/> *</td>
						</tr>
						<tr>
							<td>性别</td>
							<td>
								<select name="sex" style="width:80px">
									<option value="男">男</option>
								<option value="女">女</option>
							</select>
							</td>
						</tr>
                        <tr>
							<td>职工号</td>
							<td><input type="text" name="number" class="InputStyle" value=""/> *</td>
						</tr>
			  <tr>
				  <td>民族</td>
				  <td><input type="text" name="minzhu" class="InputStyle" value=""/> *</td>
			  </tr>
			  <tr>
				  <td>身份证号</td>
				  <td><input type="text" name="cno" class="InputStyle" value=""/> *</td>
			  </tr>
			  <tr>
				  <td>住址</td>
				  <td><input type="text" name="adds" class="InputStyle" value=""/> *</td>
			  </tr>
			  <tr>
				  <td>联系电话</td>
				  <td><input type="text" name="telephone" class="InputStyle" value=""/> *</td>
			  </tr><tr>
			  <td>邮箱</td>
			  <td><input type="text" name="emil" class="InputStyle" value=""/> *</td>
		  </tr><tr>
			  <td>出生日期</td>
			  <td><input type="text"   onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" name="birth" class="InputStyle Wdate" value=""/> *</td>
		  </tr><tr>
			  <td>参加工作日期</td>
			  <td><input type="text"   onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" name="cjgzdate" class="InputStyle Wdate" value=""/> *</td>
		  </tr><tr>
			  <td>所学专业</td>
			  <td><input type="text" name="zhuanye" class="InputStyle" value=""/> *</td>
		  </tr><tr>
			  <td>毕业院校</td>
			  <td><input type="text" name="yuanxiao" class="InputStyle" value=""/> *</td>
		  </tr>
			  <tr>
				  <td>职称</td>
				  <td>
					  <select name="zhicheng" style="width:80px">
					  		<option value="教授">教授</option>
					  		<option value="副教授">副教授</option>
						  <option value="讲师">讲师</option>
						  <option value="助教">助教</option>
				  </select>
				  </td>
			  </tr>
			  <tr>
				  <td>学历</td>
				  <td><select name="xueli" style="width:80px">
					  <option value="专科">专科</option>
					  <option value="本科">本科</option>
					  <option value="硕士研究生">硕士研究生</option>
					  <option value="博士研究生">博士研究生</option>
				  </select></td>
			  </tr>
			  <tr>
				  <td>学位</td>
				  <td><select name="xuewei" style="width:80px">
					  <option value="学士">学士</option>
					  <option value="硕士">硕士</option>
					  <option value="博士">博士</option>
				  </select></td>
			  </tr><tr>
			  <td>外语程度</td>
			  <td><input type="text" name="waiyu" class="InputStyle" value=""/> *</td>
		  </tr><tr>
			  <td>所属部门</td>
			  <td>
				  <select name="dept" style="width:80px">
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
			  </td>
		  </tr><tr>
			  <td>教研室</td>
			  <td>
				  <select name="jianyanshi" style="width:80px">
				  <option value="计算机科学与技术">计算机科学与技术</option>
				  <option value="信息管理与信息系统">信息管理与信息系统</option>
				  <option value="电气工程及其自动化">电气工程及其自动化</option>
				  <option value="电子信息工程">电子信息工程</option>
			  </select>
			  </td>
		  </tr><tr>
			  <td>研究放向</td>
			  <td><input type="text" name="yjfx" class="InputStyle" value=""/> *</td>
		  </tr>
						<tr>
							<td>备注</td>
							<td><textarea name="beizhu" class="TextareaStyle"></textarea></td>
						</tr>
						
					</table>
				</div>
            </div>
        </div>
		
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">
            
				
				
					 <input type="submit" value="添加" class="FunctionButtonInput">
				
			
            
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>
</body>
</html>
