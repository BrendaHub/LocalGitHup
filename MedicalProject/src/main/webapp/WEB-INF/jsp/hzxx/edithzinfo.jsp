<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>编辑患者信息</title>
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
<strong>编辑糖宝信息：</strong>
</div>
<input type="hidden" name="result_info" value="${info }"/>
<div class="alert alert-info alert-dismissable" id="alert_box" style="display:none;">

</div>
<form action="/HZXX/EditHZ" method="post">
	<input type="hidden" name="ID" value="${hzobj.ID }"/>
<table class="table table-bordered table-hover definewidth m10">
	
    <tr>
        <td width="20%" class="tableleft">监护人：</td>
        <td><input type="text" name="LXRNAME" maxlength="50" value="${hzobj.LXRNAME }"/></td>
    </tr>
    <tr>
        <td class="tableleft">与监护人关系：</td>
        <td><input type="text" name="GX" maxlength="11"  value="${hzobj.GX }"/></td>
    </tr> 
    <tr>
        <td class="tableleft">手机号码：</td>
        <td><input type="number" name="PHONE" maxlength="11"  value="${hzobj.PHONE }"/></td>
    </tr> 
    <tr>
        <td class="tableleft">糖宝名字：</td>
        <td><input type="text" name="HZNAME" maxlength="18"  value="${hzobj.HZNAME }"/></td>
    </tr>  
    <tr>
        <td class="tableleft">糖宝身份证：</td>
        <td><input type="text" name="SFZCODE" maxlength="18"  value="${hzobj.SFZCODE }"/></td>
    </tr>    
    <tr>
        <td class="tableleft">糖宝类型：</td>
        <td>
        	<font color="red"><b>${hzobj.NFMJBMC }</b></font>&nbsp;&nbsp;
            <input type="radio" name="NFMJBMC" id="type1" value="I型" checked required/>&nbsp;&nbsp;I型&nbsp;&nbsp;
            <input type="radio" name="NFMJBMC" id="type2" value="II型" required/>&nbsp;&nbsp;II型&nbsp;&nbsp;
            <input type="radio" name="NFMJBMC" id="type3" value="新生儿特殊" required/>&nbsp;&nbsp;新生儿特殊&nbsp;&nbsp;
            <input type="radio" name="NFMJBMC" id="type4" value="其他" required/>&nbsp;&nbsp;其他&nbsp;&nbsp;
        </td>
    </tr>
    <tr>
        <td class="tableleft">性别：</td>
        <td>
        	<font color="red"><b>${hzobj.SEX }</b></font>&nbsp;&nbsp;
            <input type="radio" name="SEX" id="type1" value="男" checked required/>&nbsp;&nbsp;男&nbsp;&nbsp;
            <input type="radio" name="SEX" id="type2" value="女" required/>&nbsp;&nbsp;女&nbsp;&nbsp;
            <input type="radio" name="SEX" id="type2" value="不详" required/>&nbsp;&nbsp;不详&nbsp;&nbsp;
        </td>
    </tr>
    <tr>
        <td class="tableleft">出生日期：</td>
        <td>
        <font color="red"><b>年龄：${hzobj.AGE }</b></font>&nbsp;&nbsp;<br/>
        <input type="date" name="tmp_birthday" value="${csrq}"/> 格式：yyyy/mm/dd</td>
    </tr> 
    <tr>
        <td class="tableleft">民族：</td>
        <td><input type="text" name="MZ" value="${hzobj.MZ }"/></td>
    </tr> 
    <tr>
        <td class="tableleft">确诊时间：</td>
        <td>
        <font color="red"><b>${NFMQZSJ} </b></font>&nbsp;&nbsp;<br/>
        <input type="date" name="NFMQZSJ" "/> 格式：yyyy/mm/dd</td>
    </tr> 
    <tr>
        <td class="tableleft">重置登录密码：</td>
        <td><input type="password" name="PASSWORD" maxlength="18"  value="${hzobj.PASSWORD }"/></td>
    </tr>  
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-success" type="button">&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;</button>&nbsp;&nbsp;<button type="button" class="btn btn-default" name="backid" id="backid">返回列表</button>
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
		var _result_info = $("#result_info").val();
		if(_result_info != undefined && _result_info != ''){
			$("#alert_box").css("display","block");
			$("#alert_box").html("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">×</button><h4>提示!</h4> "+_result_info);
		}
		
    });
</script>