<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<%
String path=application.getRealPath(request.getRequestURI());
String realPath1 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
String ServiceName = request.getServerName();
String realPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
String _realPath = "http://api.doctor330.com" + request.getContextPath();
%>
<head>
    <title>患者详细信息</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/Css/style.css" />
    <script type="text/javascript" src="/Js/jquery.js"></script>
    <script type="text/javascript" src="/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/Js/ckform.js"></script>
    <script type="text/javascript" src="/Js/common.js"></script>
    <script src="/dist/Chart.bundle.min.js"></script>
    <style type="text/css">
       canvas{
	        -moz-user-select: none;
	        -webkit-user-select: none;
	        -ms-user-select: none;
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
			margin-left:70px;
			margin-bottom:20px;
			font-size:18px;
		}
		table th,td{
			text-align:center;
		}
    </style>
</head>
<body>
<input type="hidden" id="hzID_hidden" value="${result.ID }"/>
<div id="topdiv">
<button type="button" class="btn btn-success"></button>
	糖宝信息：
	<c:if test="${empty result.TEMP2}">
    	<img src="/Images/touxiang.jpg" width="30px" height="30px"/>
    </c:if>
    <c:if test="${!empty result.TEMP2}">
    	<img src="<%=_realPath %>${result.TEMP2 }" width="35px" height="35px"/>
    </c:if>
    ${result.HZNAME }, ${result.AGE } 
    <button type="button" class="btn btn-warning" onclick="gotoHzlist();">&nbsp;&nbsp;返回列表&nbsp;&nbsp;</button>
</div>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="alert alert-info alert-dismissable" id="alert_box" style="display:none;">
				 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				<h4>
					注意!
				</h4> <strong>Warning!</strong> Best check yo self, you're not looking too good. <a href="#" class="alert-link">alert link</a>
			</div>
			<div class="tabbable" id="tabs-449027">
				<ul class="nav nav-tabs">
					<li class="active">
						 <a href="#panel-289934" data-toggle="tab">基本信息</a>
					</li>
					<li>
						 <a href="#panel-335159" data-toggle="tab" id="loadhzxuetang">血糖数据</a>
					</li>
					<li>
						 <a href="#panel-335621" data-toggle="tab">血糖图表</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-289934">
						<!-- begin -->
							<table class="table table-hover table-bordered">
								<tbody>
									<tr class="error">
										<td>
											<strong>监护人：</strong>
										</td>
										<td>
											${result.LXRNAME }
										</td>
										<td>
											<strong>关系：</strong>
										</td>
										<td>
											${result.GX }
										</td>
									</tr>
									<tr>
										<td>
											<strong>姓名：</strong>
										</td>
										<td>
											${result.HZNAME }
										</td>
										<td>
											<strong>身份证号：</strong>
										</td>
										<td>
											${result.SFZCODE }
										</td>
									</tr>
									
									<tr class="info">
										<td>
											<strong>姓别：</strong>
										</td>
										<td>
											${result.SEX }
										</td>
										<td>
											<strong>手机号：</strong>
										</td>
										<td>
											${result.PHONE }
										</td>
									</tr>
									<tr>
										<td>
											<strong>民族：</strong>
										</td>
										<td>
											<c:if test="${result.MZ != 'undefined' }">
											${result.MZ }
											</c:if>
										</td>
										<td>
											<strong>生日：</strong>
										</td>
										<td>
											<fmt:formatDate value="${hzcsrq}" var="date" pattern="yyyy-MM-dd"/>
										</td>
									</tr>
									<tr class="info">
										<td>
											<strong>类型：</strong>
										</td>
										<td>
											${result.NFMJBMC }
										</td>
										<td>
											<b>确诊时间：</b>
										</td>
										<td>
											<fmt:formatDate value="${hzqcsj}" var="date" pattern="yyyy-MM-dd"/>
										</td>
									</tr>
									<tr>
										<td>
											<strong>地址：</strong>
										</td>
										<td colspan='3'>
											${result.SHENG }
											${result.SHI }
											${result.XIAN }
										</td>
									</tr>
								</tbody>
							</table>
							<button type="button" class="btn btn-success" onclick="toEditHzInfo(${result.ID});">编辑糖宝信息</button>
						<!-- end -->
					</div>
					<div class="tab-pane" id="panel-335159">
						<!-- begin -->
						<table class="table table-hover table-bordered">
							<tbody>
								<tr>
									<td>
										开始时间：
									</td>
									<td>
										<input type="date" class="form-control" id="inputbegindate" />
									</td>
									<td>
										结止时间：
									</td>
									<td>
										<input type="date" class="form-control" id="inputenddate" />
									</td>
									<td>
										<button type="button" class="btn btn-success" onCLick="loadxutangdata(${result.ID})">加载数据</button>
									</td>
									<td>
									<button type="button" class="btn btn-info">查看图表</button>
									</td>
								</tr>
							</tbody>
						</table>
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th>
										日期：
									</th>
									<th>
										凌晨
									</th>
									<th colspan="2">
										早餐
									</th>
									<th colspan="2">
										午餐
									</th>
									<th colspan="2">
										晚餐
									</th>
									<th>
										睡前
									</th>
									<th>
										平均值
									</th>
								</tr>
							</thead>
							<thead>
								<tr>
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
									<th>
										前
									</th>
									<th>
										后
									</th>
									<th>
										前
									</th>
									<th>
										后
									</th>
									<th>
										前
									</th>
									<th>
										后
									</th>
									<th>
										&nbsp;
									</th>
									<th>
										&nbsp;
									</th>
								</tr>
							</thead>
							<tbody id="_tdobyinner">
							</tbody>
						</table>
						<!-- end -->
					</div>
					<div class="tab-pane" id="panel-335621">
						<!-- begin -->
						<div style="width:90%;">
					        <canvas id="canvas"></canvas>
					    </div>
						<!-- end -->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>
	$(document).ready(function(){
		//初始化查询的开始和结止日期
		var _date = new Date();
		var _preDate = getPreMonth(_date.getFullYear()+"-"+(_date.getMonth()+1)+"-"+_date.getDate());
		$("#inputbegindate").val(_preDate);
		$("#inputenddate").val(_date.getFullYear()+"-"+(_date.getMonth()+1)+"-"+_date.getDate());
	   //这里就需要加载血糖数据出来 , 患者ID， 开始日期， 结止日期
	   loadxutangdata($("#hzID_hidden").val());
	   //初始化画图数据
	   initlineData();
	});

	//去编辑糖宝信息
	function  toEditHzInfo(hzid){
		window.location.href="/HZXX/toedithzinfo/"+hzid;
	}
    

	//患者ID ， 第一页， 共多少条
	/**
		015001   凌晨
		015002001	早餐前
		015002002	早餐后
		015003001	午餐前
		015003002	午餐后
		015004001	晚餐前
		015004001	晚餐后
		015005		睡前
		015006001	随机
		015006002	随机
		015006003	随机
		015006004	随机
		015006005	随机
	*/
	//定义X轴的日期数组
	var labelarray = new Array();
	var linedataarray = new Array();
	function loadxutangdata(_hzid){
		$.ajax({
    		type: "post",
    		cache:false,
    		url: "/HZXX/loadhzxuetangdata/"+_hzid,
    		data: {
    			begindate:	$("#inputbegindate").val(),
    			enddate: $("#inputenddate").val()
    		},
    		success: function (data) {
        		console.log(data);
        		if(data){
        			var innerhtml = "";
        			var _jsono = JSON.parse(data);
        			if(_jsono && _jsono.length > 0){
	        			var index = 1 ; 
	        			for(var o = 0; o < _jsono.length; o ++){  
	        				if(index%2 == 1){
	            				innerhtml += "<tr class='info'>"
	        				}else{
	            				innerhtml += "<tr>"
	        				}
	        				var tmpjson = JSON.parse(_jsono[o]);
	        				labelarray.push(tmpjson.time);
	        				linedataarray.push(tmpjson.avg.toFixed(2));
	        				if(tmpjson.time != undefined){
	        					innerhtml += "<td>"+tmpjson.time+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_lingcheng != undefined){
	        					innerhtml += "<td>"+tmpjson.t_lingcheng+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_czq != undefined){
	        					innerhtml += "<td>"+tmpjson.t_czq+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_czh != undefined){
	        					innerhtml += "<td>"+tmpjson.t_czh+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_zzq != undefined){
	        					innerhtml += "<td>"+tmpjson.t_zzq+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_zzh != undefined){
	        					innerhtml += "<td>"+tmpjson.t_zzh+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_wzq != undefined){
	        					innerhtml += "<td>"+tmpjson.t_wzq+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_wzh != undefined){
	        					innerhtml += "<td>"+tmpjson.t_wzh+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_sq != undefined){
	        					innerhtml += "<td>"+tmpjson.t_sq+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				/**
	        				if(tmpjson.t_rand1 != undefined){
	        					innerhtml += "<td>"+tmpjson.t_rand1+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_rand2 != undefined){
	        					innerhtml += "<td>"+tmpjson.t_rand2+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_rand3 != undefined){
	        					innerhtml += "<td>"+tmpjson.t_rand3+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_rand4 != undefined){
	        					innerhtml += "<td>"+tmpjson.t_rand4+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				if(tmpjson.t_rand5 != undefined){
	        					innerhtml += "<td>"+tmpjson.t_rand5+"</td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}*/
	        				if(tmpjson.avg != undefined){
	        					innerhtml += "<td><font color='green'>"+tmpjson.avg.toFixed(2)+"</font></td>";
	        				}else{
	        					innerhtml += "<td>--</td>";
	        				}
	        				innerhtml += "</tr>";
	        				index++;
	        			}
	        			$("#_tdobyinner").html(innerhtml);
        			}else{
        				$("#_tdobyinner").html("<tr><td colspan='15'>暂无数据</td></tr>");
        			}
        		}else{
        			$("#_tdobyinner").html("<tr><td colspan='15'>暂无数据</td></tr>");
        		}
    		}
    	});
	}
	/**
     * 获取上一个月
     *
     * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
     */
    function getPreMonth(date) {
        var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        var month = arr[1]; //获取当前日期的月份
        var day = arr[2]; //获取当前日期的日
        var days = new Date(year, month, 0);
        days = days.getDate(); //获取当前日期中月的天数
        var year2 = year;
        var month2 = parseInt(month) - 1;
        if (month2 == 0) {
            year2 = parseInt(year2) - 1;
            month2 = 12;
        }
        var day2 = day;
        var days2 = new Date(year2, month2, 0);
        days2 = days2.getDate();
        if (day2 > days2) {
            day2 = days2;
        }
        if (month2 < 10) {
            month2 = '0' + month2;
        }
        var t2 = year2 + '-' + month2 + '-' + day2;
        return t2;
    }
    
    /**
     * 获取下一个月
     *
     * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
     */        
    function getNextMonth(date) {
        var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        var month = arr[1]; //获取当前日期的月份
        var day = arr[2]; //获取当前日期的日
        var days = new Date(year, month, 0);
        days = days.getDate(); //获取当前日期中的月的天数
        var year2 = year;
        var month2 = parseInt(month) + 1;
        if (month2 == 13) {
            year2 = parseInt(year2) + 1;
            month2 = 1;
        }
        var day2 = day;
        var days2 = new Date(year2, month2, 0);
        days2 = days2.getDate();
        if (day2 > days2) {
            day2 = days2;
        }
        if (month2 < 10) {
            month2 = '0' + month2;
        }
    
        var t2 = year2 + '-' + month2 + '-' + day2;
        return t2;
    }
    function gotoHzlist(){
    	window.location.href = "/HZXX/list";
    }
    
    //画血糖走势图
    var config = {
            type: 'line',
            data: {
            	 labels: [],
                 datasets: []
            },
            options: { 
            	scaleFontSize : 72,
            	scaleShowGridLines : false,
                scaleSteps : 15,
                responsive: true,
                title:{
                    display:true,
                    text:'血糖平均值走势'
                },
                tooltips: {
                    mode: 'label',
                    callbacks: {
                       
                    }
                },
                scales: {
                    xAxes: [{
                        display: true,
                        scaleLabel: {
                            show: true,
                            labelString: 'Month'
                        },
	                    ticks: {
	                    }
                    }],
                    yAxes: [{
                        display: true,
                        scaleLabel: {
                            show: true,
                            labelString: 'Value'
                        },
                        ticks: {
                            suggestedMin: 3,
                            suggestedMax: 11,
                            //max: 3,
                            //min: 15,
                            //stepSize: 3
                        }
                    }]
                }
            }
        };
    var randomScalingFactor = function() {
        return Math.round(Math.random() * 100);
        //return 0;
    };
    var randomColorFactor = function() {
        return Math.round(Math.random() * 255);
    };
    var randomColor = function(opacity) {
        //return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',' + (opacity || '.3') + ')';
        return 'rgba(255, 10, 10 ,' + (opacity || '.3') + ')';
    };
    function initlineData(){
    	//初始化画图数据
 	   config.data = {
 				labels: labelarray,
                 datasets: [{
                     label: "日血糖平均值",
                     data: linedataarray,
                     fill: false,
                 }]
             };
 		   $.each(config.data.datasets, function(i, dataset) {
 		        dataset.borderColor = randomColor(0.6);
 		        dataset.backgroundColor = randomColor(0.2);
 		        dataset.pointBorderColor = randomColor(0.3);
 		        dataset.pointBackgroundColor = randomColor(0.5);
 		        dataset.pointBorderWidth = 1;
 		    });
             // Update the chart
             window.myLine.update();
    }
    window.onload = function() {
        var ctx = document.getElementById("canvas").getContext("2d");
        window.myLine = new Chart(ctx, config);
    };
</script>
</html>