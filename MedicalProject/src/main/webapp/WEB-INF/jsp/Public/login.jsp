<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/Css/login.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	<script src="/Js/jquery-1.8.1.min.js"></script>
	<script src="/Js/jquery.cookie.js"></script>
    <title>医堂客登录</title>
    <style>
    	h5{
    		color:red;
    	}
    </style>
</head>
<body>
    <header>
		<table width="100%">
            <td align="left"><img src="/Images/login/tnblogo.png"/></td>
        </table>
    </header>
    <div class="_content">
        <table class="_top">
            <td></td>
        </table>
		<form class="form-signin" method="post" action="/logon">
        <div class="_bottom">
            <div class="log_left"><img src="/Images/login/left.png"/></div>
            <div class="log_right">
                <div class="log_top">
                    	用户登录<span>UserLogin</span>
                </div>
                <div class="row">
                    <div >
                        <img src="/Images/login/user.png" style="width:20px; margin-left:10px;margin-top:-8px;">
                    </div>
                    <div >
                        <input id="username" name="username" style="outline: none; border:0px; height:30px; font-size:18px; background-color:#ECF5FA; margin-left:8px;" placeholder="在此输入登录账号" >
                    </div>
                </div>
                <div class="row">
                    <div >
                        <img src="/Images/login/lock.png" style="width:18px; margin-left:12px;margin-top:-8px;">
                    </div>
                    <div >
                        <input id="userPass" type="password" name="password" style="outline: none; border:0px; height:30px; font-size:18px; background-color:#ECF5FA; margin-left:8px; " placeholder="在此输入密码" >
                    </div>
                </div>
                <div class="rowvc">
                    <div>
                        <img src="/Images/login/lock.png" style="width:18px; margin-left:12px;margin-top:-8px;">
                    </div>
                    <div>
                        <input id="userPass" name="verifycode" style="outline: none; border:0px; height:30px; font-size:18px; background-color:#ECF5FA;margin-top:5px; " placeholder="验证码" >
                    </div>
                    <img src="/verifycode" id="_verifyCode" width="80px" height="30px" onclick="changeVerifyCode();"/>
                </div>
                <div class="log_btm">
                    <input class="_log" id="_log" type="button" value="登录"/>
                    <div>
                        <input type="checkbox" id="checkbox_a1" class="chk_1" />
                        <label for="checkbox_a1"></label>
                        <span class="_remember"><label for="checkbox_a1">记住账号</label></span>
                        <span class="_forget">忘记密码 ?</span>
                    </div>
                </div>
                <h5>${result._msg }</h5>
            </div>
        </div>
        </form>
    </div>
    <footer>版权所有  2016</footer>
</body>
</html>
<script>
   
    function changeVerifyCode(){
		$("#_verifyCode").attr("src", $("#_verifyCode").attr("src")+"?ff="+Math.random());
	}
    function formsubmit(){
    	if($('#checkbox_a1').is(':checked')){//记住账号
    		console.log("记住账号选 中了....")
    		$.cookie("logo_user_cookie", $("#username").val() ,{expire:7,path:"/"});
    	}
    	$(".form-signin").submit();
    }
   $(function () {
	   var usr_cookie = $.cookie("logo_user_cookie");
	   if(usr_cookie != undefined){
		   $('#checkbox_a1').attr("checked",true);
	   }else{
		   $('#checkbox_a1').attr("checked",false);
	   }
    	
       $("#_log").click(function(){
    	   formsubmit();
       });
       
       $("._forget").click(function(){
    	   
       });
    
    });
</script>