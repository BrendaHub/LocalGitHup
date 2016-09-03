<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>糖宝随访App，后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/Css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="/Css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="/Css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="header">
    <div class="dl-title">
    	<p>糖尿病随访系统</p>
    </div>
    <div class="dl-log">
    	欢迎您，<span class="dl-log-user">${user.username }</span>
    	<a href="/logonout"  title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">业务管理</div></li>	
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-user">系统管理</div></li>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
    </ul>
</div>
<script type="text/javascript" src="/Js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/Js/bui-min.js"></script>
<script type="text/javascript" src="/Js/main-min.js"></script>
<script type="text/javascript" src="/Js/config-min.js"></script>
<!-- 

      							//{id:'12',text:'机构管理',href:'Node/index.html'},
      							//{id:'3',text:'角色管理',href:'Role/index.html'},
      							//{id:'4',text:'用户管理',href:'User/index.html'},
      							//{id:'6',text:'菜单管理',href:'Menu/index.html'}
 -->
<script>
BUI.use('common/main',function(){
    var config = [
					{id:'7',homePage : '9',menu:[{text:'业务管理',items:[
               									{id:'9',text:'患者列表',href:'/HZXX/list'},
               									{id:'91',text:'血糖月数据',href:'/HZXX/tofenxi1/5'},
               									{id:'92',text:'血糖周数据',href:'/HZXX/toweekfenxi1/5'},
               									{id:'93',text:'血糖综合分析',href:'/HZXX/sysfx'},
               									{id:'94',text:'平均值',href:'/HZXX/sysavg'},
               									{id:'95',text:'中位值',href:'/HZXX/sysmed'}
               									]}
               									]},
      				{id:'1',homePage : '4',menu:[
      						{text:'系统管理',items:[
      			      				{id:'4',text:'用户管理',href:'/user/toUserList?pageNo=1&ff='+Math.random()},
								  {id:'11',text:'修改密码',href:'/user/tomodifypwd'},
								  {id:'12',text:'机构管理',href:'/user/toNodeList'},
	      							{id:'6',text:'菜单管理',href:'/user/toMenuList'}
      							]
      						}
      						]
      					}
      					
      				];
    new PageUtil.MainPage({
        modulesConfig : config
    });
});

</script>
<div style="text-align:center;">
</div>
</body>
</html>