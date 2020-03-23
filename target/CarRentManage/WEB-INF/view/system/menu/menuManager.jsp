<%--
  Created by IntelliJ IDEA.
  User: Shinelon
  Date: 2020/3/23
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <html>
    <head>
    <title>菜单管理</title>
    </head>

    <%--如果使用frameset 包含页面 主页面不能有body--%>
    <frameset cols="230,*" border="1">
    <frame src="${ctx}/sys/toMenuLeft.action" name="left">
    <frame src="${ctx}/sys/toMenuRight.action" name="right">
    </frameset>


    </html>