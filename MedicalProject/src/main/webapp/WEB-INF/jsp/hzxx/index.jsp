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
    <script type="text/javascript" src="/Js/jquery.sorted.js"></script>
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
<form class="form-inline definewidth m20" action="index.html" method="get">  
    患者姓名：
    <input type="text" name="rolename" id="rolename"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增患者</button>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>身份证号</th>
        <th>监护手机</th>
        <th>出生日期</th>
        <th>管理操作</th>
    </tr>
    </thead>
    <c:if test="${fn:length(result) > 0}" var="countnumber">
    	<c:forEach items="${result }" var="h" varStatus="status">
	    	<tr>
	            <td>${status.index + 1}</td>
	            <td>${h.HZNAME }</td>
	            <td>${h.SFZCODE }</td>
	            <td>${h.PHONE }</td>
	            <td>${h.CSRQ }</td>
	            <td
	                 <a href="edit.html">编辑</a>
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
<%@include file="../common/p.jsp" %>
</body>
</html>
<script>
    $(function () {
        
		$('#addnew').click(function(){
				window.location.href="add.html";
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
	function toPage(){
		
	}
</script>