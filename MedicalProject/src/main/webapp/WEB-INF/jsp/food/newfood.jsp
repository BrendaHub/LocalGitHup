<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>添加新的食材</title>
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
<strong>添加新的食材（单位（克））：</strong>
</div>
<form action="/tfood/AddFood" method="post">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">食材名称：</td>
        <td><input type="text" name="food_name" maxlength="25" required/></td>
    </tr>
    <tr>
        <td class="tableleft">重量（克）：</td>
        <td><input type="number" name="weight" maxlength="11" required/></td>
    </tr> 
    <tr>
        <td class="tableleft">热量：</td>
        <td><input type="number" name="hot" maxlength="11" required/></td>
    </tr> 
    <tr>
        <td class="tableleft">蛋白质：</td>
        <td><input type="number" name="protein" maxlength="11" required/></td>
    </tr>  
    <tr>
        <td class="tableleft">脂肪：</td>
        <td><input type="number" name="fat" maxlength="11" required/></td>
    </tr>  
    <tr>
        <td class="tableleft">糖量：</td>
        <td><input type="number" name="sugar" maxlength="11" required/></td>
    </tr>  
    <tr>
        <td class="tableleft">膳食纤维：</td>
        <td><input type="number" name="dietaryfiber" maxlength="11" required/></td>
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
				window.location.href="/tfood/tofoodlsit?f="+Math.random();
		 });

    });
</script>