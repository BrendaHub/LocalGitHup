<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>二维码下载糖宝随访App</title>   
        <style>
		html, body {
			background-color: #f2f2f2;
		}
		.panel {
        	padding: 20px;
        }
        .panel-header {
        	margin: 20px 0;
        	width: 100%;
			height: 40%;
        }
		.logo, .title{
			width: 100%;
			text-align: center;					
		}
		.logo img {
			width: 100px;
		}
		.title {
			margin-top: 10px;
		}
		.title img {
			width: 220px;
		}
		/**
		 * 登录区
		 */
		.panel-login {  			
  			padding: 8px;
  			background-color: #ffffff;
  			border: 1px solid #28bbe8;
  			border-radius: 10px;
  			-webkit-border-radius: 10px;
  			-webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
  			box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);			
			width:100%;
			margin:0 auto;
		}
		.row {
			width:95%;
			height: 80px;			
		}
		.singleInput {
			border-radius: 0;
			border: 1px solid transparent;
			box-shadow: none;    
		}
		.form-control:focus {
		    border:1px solid transparent;
		    box-shadow: none;
		    outline: 0 none;			
		}
        /**
         * 表单类
         */    
		.btnwrap-login{
			margin-top: 15px;
			width: 100%;
		}
        .btn-large {
		    border-radius: 5px;			    	         
        }
        .btnwrap-forgetpass{
        	width: 100%;
        	text-align: right;
        }
        /**
         * 页脚
         */  
        #footer{
     	
			height: 33px;
			line-height: 33px;					
			width: 100%;
			color:#d4d4d4;
			position:absolute;
            top:460px;
		}
		.btnwrap-reg {
			width: 100%;
			text-align: center;
		}       
    </style>
    </head>
    <body>
        <div id="mainWrap">
		<div class="panel">
			<div class="panel-header" style="height:150px">
				<div class="logo">
					<img src="/Images/logo_1.png">									
				</div>
				<div class="title">	
					<img src="/Images/logo_2.png">
				</div>
			</div>
			<div class="panel-login">
				<div class="row">

					<div  style="float:left;width:450px;padding-top:5px;font-size:14px;color:#28bbe8;">										
						 <p>》欢迎下载《糖宝随访》北京儿童医院系统。</p>
						 <p><font color="red">提示： 如果在微信里，点击右上角图标，“Open in Safari” 或 “用浏览器打开”</font></p>

					</div>
				</div>	
				
			</div>
			

		</div>		
		<div id="footer">
			<div class="btnwrap-reg">
				<div>─── 氪糖出品 ───</div>
			</div>
		</div>
	</div>
	<div id="wrongWrap">
		<div class="wrongWrapTitle"></div>
		<div class="wrongWrapDesc"></div>
	</div>	
	<div id="debug" class="debug"></div> 
    </body>
	<script type="text/javascript">
            /*
 *              * 智能机浏览器版本信息:
 *                           *
 *                                        */
            var browser = {
                versions: function() {
                    var u = navigator.userAgent, app = navigator.appVersion;
                    return {//移动终端浏览器版本信息
                        trident: u.indexOf('Trident') > -1, //IE内核
                        presto: u.indexOf('Presto') > -1, //opera内核
                        webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                        gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                        mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
                        ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                        android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                        iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
                        iPad: u.indexOf('iPad') > -1, //是否iPad
                        webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
                    };
                }(),
                language: (navigator.browserLanguage || navigator.language).toLowerCase()
            }
 
            if (browser.versions.ios || browser.versions.iPhone || browser.versions.iPad) {
                window.location="https://itunes.apple.com/us/app/tang-bao-sui-fang/id1134856708?mt=8";
            }
            else if (browser.versions.android) {
                window.location="http://downloadpkg.apicloud.com/app/download?path=http://7xspgz.com1.z0.glb.clouddn.com/c00e1511dd12dcc6ef11b53038529c3b_d";
            }
 
//            document.writeln("语言版本: " + browser.language);
//            //            document.writeln(" 是否为移动终端: " + browser.versions.mobile);
//            //            document.writeln(" ios终端: " + browser.versions.ios);
//            //            document.writeln(" android终端: " + browser.versions.android);
//            //            document.writeln(" 是否为iPhone: " + browser.versions.iPhone);
//            //            document.writeln(" 是否iPad: " + browser.versions.iPad);
//            //            document.writeln(navigator.userAgent);
//             
//              
//                      </script>
</html>
