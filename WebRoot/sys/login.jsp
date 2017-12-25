<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/12/8
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath }/sys/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath }/sys/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/sys/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/sys/assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/sys/assets/css/app.css">
    <script src="${pageContext.request.contextPath }/sys/assets/js/jquery.min.js"></script>
</head>
<body data-type="login">
<script src="${pageContext.request.contextPath }/sys/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 风格切换 -->
    <div class="tpl-skiner">
        <div class="tpl-skiner-toggle am-icon-cog">
        </div>
        <div class="tpl-skiner-content">
            <div class="tpl-skiner-content-title">
                选择主题
            </div>
            <div class="tpl-skiner-content-bar">
                <span class="skiner-color skiner-white" data-color="theme-white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black"></span>
            </div>
        </div>
    </div>

    <div class="tpl-login">
        <div class="tpl-login-content">
            <c:if test="${msg != null }">
            <div class="am-alert" data-am-alert>
                <button type="button" class="am-close">&times;</button>
                <p>${msg}</p>
            </div>
            </c:if>
            <div class="tpl-login-logo">

            </div>
            <div style="color: #00a23f">
                管理员登录
            </div>

            <form class="am-form tpl-form-line-form" action="${pageContext.request.contextPath }/sysuser?method=login" method="post">
                <div class="am-form-group">
                    <input type="text"  name="username" class="tpl-form-input" placeholder="请输入帐号">

                </div>

                <div class="am-form-group">
                    <input type="password" name="password" class="tpl-form-input"  placeholder="请输入密码">
                </div>
                <div class="am-form-group">

                    <button type="submit" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath }/sys/assets/js/amazeui.min.js"></script>
<script src="${pageContext.request.contextPath }/sys/assets/js/app.js"></script>

</body>
</html>
