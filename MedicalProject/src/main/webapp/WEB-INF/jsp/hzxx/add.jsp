<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>医生添加新患者</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
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
		#topdiv{
			margin-top:20px;
			margin-left:30px;
			margin-bottom:20px;
			font-size:24px;
		}
	
    </style>
</head>
<body>
<div id="topdiv">
<button type="button" class="btn btn-success"></button>
<strong>添加新患者：</strong>
</div>
<form action="/HZXX/AddHZ" method="post">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">小朋友姓名：</td>
        <td><input type="text" name="HZNAME" maxlength="25" required/></td>
    </tr>
    <tr>
        <td class="tableleft">家长手机号：</td>
        <td><input type="number" name="PHONE" maxlength="11" required/></td>
    </tr> 
    <tr>
        <td class="tableleft">家长身份证号：</td>
        <td><input type="text" name="SFZCODE" maxlength="18" required/></td>
    </tr>   
    <tr>
        <td class="tableleft">类型选择：</td>
        <td>
            <input type="radio" name="TEMP1" id="type1" value="1" checked required/>&nbsp;&nbsp;I型&nbsp;&nbsp;
            <input type="radio" name="TEMP1" id="type2" value="2" required/>&nbsp;&nbsp;II型&nbsp;&nbsp;
            <input type="radio" name="TEMP1" id="type3" value="3" required/>&nbsp;&nbsp;新生儿特殊&nbsp;&nbsp;
            <input type="radio" name="TEMP1" id="type4" value="4" required/>&nbsp;&nbsp;其他&nbsp;&nbsp;
        </td>
    </tr>
    <tr>
        <td class="tableleft">随访日期：</td>
        <td><input type="date" name="TEMP3" required/></td>
    </tr>  
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="/HZXX/list?f="+Math.random();
		 });

    });
</script>