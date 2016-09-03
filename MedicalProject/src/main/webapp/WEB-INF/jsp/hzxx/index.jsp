<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>  
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/Css/style.css" />
    <script type="text/javascript" src="/Js/jquery.js"></script>
    <!-- script type="text/javascript" src="/Js/jquery.sorted.js"></script> -->
    <script type="text/javascript" src="/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/Js/ckform.js"></script>
    <script type="text/javascript" src="/Js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }
        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
</head>
<body>
<%
String path=application.getRealPath(request.getRequestURI());
String realPath1 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
String ServiceName = request.getServerName();
String realPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
String _realPath = "http://api.doctor330.com" + request.getContextPath();
%>

<form class="form-inline definewidth m20" action="/HZXX/list" method="post" id="submitform"> 
	<input type="hidden" name="pageNo" id="pageNo" value="1"/> 
    患者姓名：
    <input type="text" name="keyworld" id="keyworld" class="abc input-default" placeholder="请输入患者姓名支持模糊检索" value="${keyworld }"  onkeydown='if(event.keyCode==13){toPage(1);}'>&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增患者</button>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th width="5%" style="text-align:center;">头像</th>
        <th style="text-align:center;">姓名</th>
        <th style="text-align:center;">性别</th>
        <th style="text-align:center;">年龄</th>
        <th style="text-align:center;">疾病类型</th>
        <th style="text-align:center;">查看详情</th>
    </tr>
    </thead>
    <c:if test="${fn:length(result) > 0}" var="countnumber">
    	<c:forEach items="${result }" var="h" varStatus="status">
	    	<tr>
	            <td style="text-align:center;">
	            <c:if test="${empty h.TEMP2}">
	            	<img src="/Images/touxiang.jpg" width="30px" height="30px"/>
	            </c:if>
	            <c:if test="${!empty h.TEMP2}">
	            	<img src="<%=_realPath %>${h.TEMP2 }" width="45px" height="45px"/>
	            </c:if>
	            </td>
	            <td style="text-align:center;">${h.HZNAME }</td>
	            <td style="text-align:center;">${h.SEX }</td>
	            <td style="text-align:center;">${h.AGE }</td>
	            <td style="text-align:center;">
		            <c:if test="${h.TEMP1 eq '1'}">
		            	I型
		            </c:if>
		            <c:if test="${h.TEMP1 eq '2'}">
		            	II型
		            </c:if>
		            <c:if test="${h.TEMP1 eq '3'}">
		            	新生儿特殊
		            </c:if> 
		            <c:if test="${h.TEMP1 eq '4'}">
		            	其他
		            </c:if>
	            </td>
	            <td style="text-align:center;">
	                 <a href="edit.html">查看详情</a>
	            </td>
	        </tr>
    	</c:forEach>
    </c:if>
    <c:if test="${!countnumber }">
    	<tr>
            <td colspan="6">暂无数据</td>
        </tr>
    </c:if>
</table>
<%@include file="../common/page.jsp" %>
</body>
</html>
<script>
    $(function () {
        
		$('#addnew').click(function(){
				window.location.href="/HZXX/toAddUser?f="+Math.random();
		 });
    });
	function del(id)
	{
		if(confirm("确定要删除吗？"))
		{
			var url = "index.html";
			window.location.href=url;
		}
	}
	//翻页函数
	function toPage(pageindex){
		console.log("当前页面为："+ pageindex);
		/* window.location.href="/HZXX/list?pageNo="+pageindex+"&f="+Math.random(); */
		$("#pageNo").val(pageindex);
		console.log("跳转的页码为："+ $("#pageNo").val());
		console.log("表单为： " + $("#submitform"));
		$(".form-inline").submit();
	}
</script>